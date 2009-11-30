package org.slf4j.n;

import org.slf4j.helpers.Util;
import org.slf4j.n.helpers.FallbackLoggerFactory;
import org.slf4j.n.helpers.SubstituteLoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public final class LoggerFactory {

  /*
  This class must handle the following use-cases:
  1.) The binding is an slf4j-binding.
      There is no implementation of n.ILoggerFactory available
      In this case, this class must return a wrapped slf4j.Logger
  2.) The binding is an slf4j-n-binding.
      In this case, this class returns a native implementation of an slf4j.n.Logger.
      org.slf4j.impl.StaticLoggerBinder.getLoggerFactory() would return an implementation that
      would call org.slf4j.n.LoggerFactory.getLogger("name").getOldLogger();
   */


  private static final String NO_STATICLOGGERBINDER_URL = "http://www.slf4j.org/codes.html#StaticLoggerBinder";
  private static final String MULTIPLE_BINDINGS_URL = "http://www.slf4j.org/codes.html#multiple_bindings";
  private static final String VERSION_MISMATCH = "http://www.slf4j.org/codes.html#version_mismatch";
  private static final String SUBSTITUTE_LOGGER_URL = "http://www.slf4j.org/codes.html#substituteLogger";

  private static final String UNSUCCESSFUL_INIT_URL = "http://www.slf4j.org/codes.html#unsuccessfulInit";
  private static final String UNSUCCESSFUL_INIT_MSG = "org.slf4j.LoggerFactory could not be successfully initialized. See also "
      + UNSUCCESSFUL_INIT_URL;
  private static String loggerFactoryClassName;

  // StaticLoggerBinder definitions below
  private static final String STATIC_LOGGER_BINDER_CLASS_NAME = "org.slf4j.n.impl.StaticLoggerBinder";
  private static final String STATIC_LOGGER_BINDER_BASE = STATIC_LOGGER_BINDER_CLASS_NAME.replace('.', '/');
  private static final String STATIC_LOGGER_BINDER_CLASS_FILE = STATIC_LOGGER_BINDER_BASE + ".class";
  private static final String STATIC_LOGGER_BINDER_GET_SINGLETON_METHOD = "getSingleton";
  private static final String STATIC_LOGGER_BINDER_GET_LOGGER_FACTORY_METHOD = "getLoggerFactory";
  private static final String STATIC_LOGGER_BINDER_GET_LOGGER_FACTORY_CLASS_NAME_METHOD = "getLoggerFactoryClassName";
  private static final String STATIC_LOGGER_BINDER_GET_REQUESTED_API_VERSION_METHOD = "getRequestedApiVersion";

  private enum State {
    UNINITIALIZED,
    ONGOING_INITIALIZATION,
    FAILED_INITIALIZATION,
    SUCCESSFUL_INITILIZATION
  }

  private static State INITIALIZATION_STATE = State.UNINITIALIZED;

  private static SubstituteLoggerFactory TEMP_FACTORY = new SubstituteLoggerFactory();
  private static ILoggerFactory loggerFactory;

  /**
   * It is LoggerFactory's responsibility to track version changes and manage
   * the compatibility list.
   * <p/>
   * <p/>
   * It is assumed that qualifiers after the 3rd digit have no impact on
   * compatibility. Thus, 1.5.7-SNAPSHOT, 1.5.7.RC0 are compatible with 1.5.7.
   */
  private static final String[] API_COMPATIBILITY_LIST = new String[]{
      "1.5.5", "1.5.6", "1.5.7", "1.5.8", "1.5.9", "1.5.10"};
  private static String requestedApiVersion;

  // private constructor prevents instantiation
  private LoggerFactory() {
  }

  /**
   * Force LoggerFactory to consider itself uninitialized.
   * <p/>
   * <p/>
   * This method is intended to be called by classes (in the same package) for
   * testing purposes. This method is internal. It can be modified, renamed or
   * removed at any time without notice.
   * <p/>
   * <p/>
   * You are strongly discouraged from calling this method in production code.
   */
  static void reset() {
    INITIALIZATION_STATE = State.UNINITIALIZED;
    TEMP_FACTORY = new SubstituteLoggerFactory();
  }

  private static void performInitialization() {
    bind();
    versionSanityCheck();
    singleImplementationSanityCheck();

  }

  private static void bind() {
    try {
      // the next line does the binding
      initLoggerFactory();
      INITIALIZATION_STATE = State.SUCCESSFUL_INITILIZATION;
      emitSubstituteLoggerWarning();
    }
    catch (NoClassDefFoundError ncde) {
      // this shouldn't happen. Fallback is used in that case.
      INITIALIZATION_STATE = State.FAILED_INITIALIZATION;
      String msg = ncde.getMessage();
      if (msg != null && msg.indexOf(STATIC_LOGGER_BINDER_BASE) != -1) {
        Util
            .reportFailure("Failed to load class \"" + STATIC_LOGGER_BINDER_CLASS_NAME + "\".");
        Util.reportFailure("See " + NO_STATICLOGGERBINDER_URL
            + " for further details.");

      }
      throw ncde;
    }
    catch (Exception e) {
      INITIALIZATION_STATE = State.FAILED_INITIALIZATION;
      // we should never get here
      Util.reportFailure("Failed to instantiate logger ["
          + loggerFactoryClassName + "]", e);
    }
  }

  private static void initLoggerFactory() {
    loggerFactory = null;
    loggerFactoryClassName = null;
    requestedApiVersion = null;
    try {
      Class clazz = Class.forName(STATIC_LOGGER_BINDER_CLASS_NAME);
      Method getRequestedApiVersionMethod = clazz.getMethod(STATIC_LOGGER_BINDER_GET_REQUESTED_API_VERSION_METHOD);
      requestedApiVersion = (String) getRequestedApiVersionMethod.invoke(null);

      Method getSingletonMethod = clazz.getMethod(STATIC_LOGGER_BINDER_GET_SINGLETON_METHOD);
      Method getLoggerFactoryMethod = clazz.getMethod(STATIC_LOGGER_BINDER_GET_LOGGER_FACTORY_METHOD);
      Method getLoggerFactoryClassNameMethod = clazz.getMethod(STATIC_LOGGER_BINDER_GET_LOGGER_FACTORY_CLASS_NAME_METHOD);

      Object result = getSingletonMethod.invoke(null);
      if (result != null) {
        loggerFactoryClassName = (String) getLoggerFactoryClassNameMethod.invoke(result);
        loggerFactory = (ILoggerFactory) getLoggerFactoryMethod.invoke(result);
      }
    }
    catch (Throwable t) {
      // ignore all exceptions...
      // if anything fails we'll drop back to SLF4J fallback.
      t.printStackTrace(); // for now
    }
    if (loggerFactory == null) {
      // use fallback that wraps old SLF4J
      loggerFactory = new FallbackLoggerFactory();
      loggerFactoryClassName = loggerFactory.getClass().getName();
    }
  }

  private static void emitSubstituteLoggerWarning() {
    List<String> loggerNameList = TEMP_FACTORY.getLoggerNameList();
    if (loggerNameList.size() == 0) {
      return;
    }
    Util
        .reportFailure("The following loggers will not work becasue they were created");
    Util
        .reportFailure("during the default configuration phase of the underlying logging system.");
    Util.reportFailure("See also " + SUBSTITUTE_LOGGER_URL);
    for (Object aLoggerNameList : loggerNameList) {
      String loggerName = (String) aLoggerNameList;
      Util.reportFailure(loggerName);
    }
  }

  private static void versionSanityCheck() {
    if (requestedApiVersion != null) {
      try {
        boolean match = false;
        for (String current : API_COMPATIBILITY_LIST) {
          if (requestedApiVersion.startsWith(current)) {
            match = true;
          }
        }
        if (!match) {
          Util.reportFailure("The requested version " + requestedApiVersion
              + " by your slf4j binding is not compatible with "
              + Arrays.asList(API_COMPATIBILITY_LIST).toString());
          Util.reportFailure("See " + VERSION_MISMATCH + " for further details.");
        }
      }
      catch (java.lang.NoSuchFieldError nsfe) {
        // given our large user base and SLF4J's commitment to backward
        // compatibility, we cannot cry here. Only for implementations
        // which willingly declare a REQUESTED_API_VERSION field do we
        // emit compatibility warnings.
      }
      catch (Throwable e) {
        // we should never reach here
        Util.reportFailure(
            "Unexpected problem occured during version sanity check", e);
      }
    }
  }


  private static void singleImplementationSanityCheck() {
    try {
      ClassLoader loggerFactoryClassLoader = LoggerFactory.class
          .getClassLoader();
      if (loggerFactoryClassLoader == null) {
        // see http://bugzilla.slf4j.org/show_bug.cgi?id=146
        return; // better than a null pointer exception
      }
      Enumeration paths = loggerFactoryClassLoader
          .getResources(STATIC_LOGGER_BINDER_CLASS_FILE);
      List<URL> implementationList = new ArrayList<URL>();
      while (paths.hasMoreElements()) {
        URL path = (URL) paths.nextElement();
        implementationList.add(path);
      }
      if (implementationList.size() > 1) {
        Util.reportFailure("Class path contains multiple SLF4J bindings.");
        for (URL current : implementationList) {
          Util.reportFailure("Found binding in [" + current
              + "]");
        }
        Util.reportFailure("See " + MULTIPLE_BINDINGS_URL
            + " for an explanation.");
      }
    }
    catch (IOException ioe) {
      Util.reportFailure("Error getting resources from path", ioe);
    }
  }

  /**
   * Return a logger named according to the name parameter using the statically
   * bound {@link org.slf4j.ILoggerFactory} instance.
   *
   * @param name The name of the logger.
   * @return logger
   */
  public static Logger getLogger(String name) {
    ILoggerFactory iLoggerFactory = getILoggerFactory();
    return iLoggerFactory.getLogger(name);
  }

  /**
   * Return a logger named corresponding to the class passed as parameter, using
   * the statically bound {@link ILoggerFactory} instance.
   *
   * @param clazz the returned logger will be named after clazz
   * @return logger
   */
  public static Logger getLogger(Class clazz) {
    return getLogger(clazz.getName());
  }

  /**
   * Return the {@link ILoggerFactory} instance in use.
   * <p/>
   * <p/>
   * ILoggerFactory instance is bound with this class at compile time.
   *
   * @return the ILoggerFactory instance in use
   */
  public static ILoggerFactory getILoggerFactory() {
    if (INITIALIZATION_STATE == State.UNINITIALIZED) {
      INITIALIZATION_STATE = State.ONGOING_INITIALIZATION;
      performInitialization();

    }
    switch (INITIALIZATION_STATE) {
      case SUCCESSFUL_INITILIZATION:
        return loggerFactory;
      case FAILED_INITIALIZATION:
        throw new IllegalStateException(UNSUCCESSFUL_INIT_MSG);
      case ONGOING_INITIALIZATION:
        // support re-entrant behavior.
        // See also http://bugzilla.slf4j.org/show_bug.cgi?id=106
        return TEMP_FACTORY;
    }
    throw new IllegalStateException("Unreachable code");
  }
}
