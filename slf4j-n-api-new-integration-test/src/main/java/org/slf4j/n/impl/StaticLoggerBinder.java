package org.slf4j.n.impl;

import org.slf4j.n.ILoggerFactory;

public class StaticLoggerBinder {
  private static StaticLoggerBinder SINGLETON;
  private ILoggerFactory loggerFactory;

  /**
   * Return the singleton of this class.
   *
   * @return the StaticLoggerBinder singleton
   */
  public static synchronized StaticLoggerBinder getSingleton() {
    if (SINGLETON == null) {
      SINGLETON = new StaticLoggerBinder();
    }
    return SINGLETON;
  }

  /**
   * Declare the version of the SLF4J API this implementation is compiled against.
   * The value of this field is usually modified with each release.
   *
   * @return the API version this implementation is compiled against.
   */
  public static String getRequestedApiVersion() {
    return "1.5.10";
  }

  private StaticLoggerBinder() {
  }

  // this is executed very rarely so synchronized is absolutely ok
  public synchronized ILoggerFactory getLoggerFactory() {
    if (loggerFactory != null) {
      return loggerFactory;
    }
    try {
      loggerFactory = (ILoggerFactory) Class.forName(getLoggerFactoryClassName()).newInstance();
    }
    catch (Throwable e) {
      throw new RuntimeException("Couldn't create LoggerFactory '" + getLoggerFactoryClassName() + "'!", e);
    }
    return loggerFactory;
  }

  public String getLoggerFactoryClassName() {
    return "org.slf4j.n.test.TestLoggerFactory";
  }
}
