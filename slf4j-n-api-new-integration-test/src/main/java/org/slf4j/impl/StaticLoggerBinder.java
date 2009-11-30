package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.n.helpers.UsingNewLoggerFactory;

public class StaticLoggerBinder {
  /**
   * The unique instance of this class.
   *
   * @deprecated Please use the {@link #getSingleton()} method instead of
   *             accessing this field directly. In future versions, this field
   *             will become private.
   */
  public static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
  private ILoggerFactory loggerFactory;

  /**
   * Return the singleton of this class.
   *
   * @return the StaticLoggerBinder singleton
   */
  public static StaticLoggerBinder getSingleton() {
    return SINGLETON;
  }

  /**
   * Declare the version of the SLF4J API this implementation is compiled against.
   * The value of this field is usually modified with each release.
   */
  // to avoid constant folding by the compiler, this field must *not* be final
  public static String REQUESTED_API_VERSION = "1.5.10";  // !final

  private StaticLoggerBinder() {
  }

  public synchronized ILoggerFactory getLoggerFactory() {
    if (loggerFactory != null) {
      return loggerFactory;
    }
    loggerFactory = new UsingNewLoggerFactory();
    return loggerFactory;
  }

  public String getLoggerFactoryClassStr() {
    return "org.slf4j.n.helpers.UsingNewLoggerFactory";
  }

}
