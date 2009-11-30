package org.slf4j.n;

import org.slf4j.helpers.Util;
import org.slf4j.n.helpers.SubstituteLoggerFactory;
import org.slf4j.n.helpers.FallbackLoggerFactory;

import java.util.List;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.ArrayList;
import java.net.URL;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

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

  private enum State
  {
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
   *
   * <p>
   * It is assumed that qualifiers after the 3rd digit have no impact on
   * compatibility. Thus, 1.5.7-SNAPSHOT, 1.5.7.RC0 are compatible with 1.5.7.
   */
  static private final String[] API_COMPATIBILITY_LIST = new String[] {
      "1.5.5", "1.5.6", "1.5.7", "1.5.8", "1.5.9", "1.5.10" };
  private static String requestedApiVersion;

  // private constructor prevents instantiation
  private LoggerFactory() {
  }

  /**
   * Force LoggerFactory to consider itself uninitialized.
   *
   * <p>
   * This method is intended to be called by classes (in the same package) for
   * testing purposes. This method is internal. It can be modified, renamed or
   * removed at any time without notice.
   *
   * <p>
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
    } catch (NoClassDefFoundError ncde) {
      // this shouldn't happen. Fallback is used in that case.
      INITIALIZATION_STATE = State.FAILED_INITIALIZATION;
      String msg = ncde.getMessage();
      if (msg != null && msg.indexOf("org/slf4j/n/impl/StaticLoggerBinder") != -1) {
        Util
            .reportFailure("Failed to load class \"org.slf4j.n.impl.StaticLoggerBinder\".");
        Util.reportFailure("See " + NO_STATICLOGGERBINDER_URL
            + " for further details.");

      }
      throw ncde;
    } catch (Exception e) {
      INITIALIZATION_STATE = State.FAILED_INITIALIZATION;
      // we should never get here
      Util.reportFailure("Failed to instantiate logger ["
          + loggerFactoryClassName + "]", e);
    }
  }

  private static void initLoggerFactory()
  {
    loggerFactory = null;
    loggerFactoryClassName = null;
    requestedApiVersion = null;
    try
    {
      Class clazz=Class.forName("org.slf4j.n.StaticLoggerBinding");
      Method getSingletonMethod=clazz.getMethod("getSingleton");
      Method getLoggerFactoryMethod = clazz.getMethod("getLoggerFactory");
      Method getLoggerFactoryClassMethod = clazz.getMethod("getLoggerFactoryClassStr");

      Object result=getSingletonMethod.invoke(null);
      if(result != null)
      {
        loggerFactoryClassName = (String) getLoggerFactoryClassMethod.invoke(result);
        loggerFactory = (ILoggerFactory) getLoggerFactoryMethod.invoke(result);
      }

      Field field = clazz.getDeclaredField("REQUESTED_API_VERSION");
      requestedApiVersion = (String) field.get(null);
    }
    catch(Throwable t)
    {
      // ignore all exceptions...
      // if anything fails we'll drop back to SLF4J fallback.
      t.printStackTrace(); // for now
    }
    if(loggerFactory == null)
    {
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
    for (Object aLoggerNameList : loggerNameList)
    {
      String loggerName = (String) aLoggerNameList;
      Util.reportFailure(loggerName);
    }
  }

  private static void versionSanityCheck() {
    if(requestedApiVersion != null)
    {
      try {
        boolean match = false;
        for (String current : API_COMPATIBILITY_LIST)
        {
          if (requestedApiVersion.startsWith(current))
          {
            match = true;
          }
        }
        if (!match) {
          Util.reportFailure("The requested version " + requestedApiVersion
              + " by your slf4j binding is not compatible with "
              + Arrays.asList(API_COMPATIBILITY_LIST).toString());
          Util.reportFailure("See " + VERSION_MISMATCH + " for further details.");
        }
      } catch (java.lang.NoSuchFieldError nsfe) {
        // given our large user base and SLF4J's commitment to backward
        // compatibility, we cannot cry here. Only for implementations
        // which willingly declare a REQUESTED_API_VERSION field do we
        // emit compatibility warnings.
      } catch (Throwable e) {
        // we should never reach here
        Util.reportFailure(
            "Unexpected problem occured during version sanity check", e);
      }
    }
  }

  // We need to use the name of the StaticLoggerBinder class, we can't reference
  // the class itseld.
  private static String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";

  private static void singleImplementationSanityCheck() {
    try {
      ClassLoader loggerFactoryClassLoader = LoggerFactory.class
          .getClassLoader();
      if (loggerFactoryClassLoader == null) {
        // see http://bugzilla.slf4j.org/show_bug.cgi?id=146
        return; // better than a null pointer exception
      }
      Enumeration paths = loggerFactoryClassLoader
          .getResources(STATIC_LOGGER_BINDER_PATH);
      List<URL> implementationList = new ArrayList<URL>();
      while (paths.hasMoreElements()) {
        URL path = (URL) paths.nextElement();
        implementationList.add(path);
      }
      if (implementationList.size() > 1) {
        Util.reportFailure("Class path contains multiple SLF4J bindings.");
        for (URL current : implementationList)
        {
          Util.reportFailure("Found binding in [" + current
              + "]");
        }
        Util.reportFailure("See " + MULTIPLE_BINDINGS_URL
            + " for an explanation.");
      }
    } catch (IOException ioe) {
      Util.reportFailure("Error getting resources from path", ioe);
    }
  }

//  private static StaticLoggerBinder getSingleton() {
//    if (GET_SINGLETON_METHOD == GET_SINGLETON_EXISTS) {
//      return StaticLoggerBinder.getSingleton();
//    }
//
//    try {
//      StaticLoggerBinder singleton = StaticLoggerBinder.getSingleton();
//      GET_SINGLETON_METHOD = GET_SINGLETON_EXISTS;
//      return singleton;
//    } catch (NoSuchMethodError nsme) {
//      GET_SINGLETON_METHOD = GET_SINGLETON_INEXISTENT;
//      return StaticLoggerBinder.SINGLETON;
//    }
//  }

  /**
   * Return a logger named according to the name parameter using the statically
   * bound {@link org.slf4j.ILoggerFactory} instance.
   *
   * @param name
   *          The name of the logger.
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
   * @param clazz
   *          the returned logger will be named after clazz
   * @return logger
   */
  public static Logger getLogger(Class clazz) {
    return getLogger(clazz.getName());
  }

  /**
   * Return the {@link ILoggerFactory} instance in use.
   *
   * <p>
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
