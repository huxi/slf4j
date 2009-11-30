package org.slf4j.n.impl;

import org.slf4j.n.ILoggerFactory;

/**
 * The binding of {@link org.slf4j.n.LoggerFactory} class with an actual instance of
 * {@link ILoggerFactory} is performed using information returned by this class.
 * <p/>
 * This class is meant to provide a dummy StaticLoggerBinder to the slf4j-n-api module.
 * <p/>
 * Since it's not referenced directly anymore it's not really needed to compile the API.
 *
 * @author Ceki G&uuml;lc&uuml;, J&ouml;rn Huxhorn
 */
public class StaticLoggerBinder {
  private static StaticLoggerBinder SINGLETON;

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
    throw new UnsupportedOperationException("This code should have never made it into the jar");
  }

  public ILoggerFactory getLoggerFactory() {
    throw new UnsupportedOperationException("This code should never make it into the jar");
  }

  public String getLoggerFactoryClassName() {
    throw new UnsupportedOperationException("This code should never make it into the jar");
  }
}
