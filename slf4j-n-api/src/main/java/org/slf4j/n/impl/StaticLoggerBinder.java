package org.slf4j.n.impl;

import org.slf4j.n.ILoggerFactory;

/**
 * The binding of {@link org.slf4j.n.LoggerFactory} class with an actual instance of
 * {@link ILoggerFactory} is performed using information returned by this class.
 *
 * This class is meant to provide a dummy StaticLoggerBinder to the slf4j-n-api module.
 *
 * Since it's not referenced directly anymore it's not really needed to compile the API.
 *
 * @author Ceki G&uuml;lc&uuml;, J&ouml;rn Huxhorn
 */public class StaticLoggerBinder
{
  private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

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
    throw new UnsupportedOperationException("This code should have never made it into the jar");
  }

  public ILoggerFactory getLoggerFactory() {
    throw new UnsupportedOperationException("This code should never make it into the jar");
  }

  public String getLoggerFactoryClassStr() {
    throw new UnsupportedOperationException("This code should never make it into the jar");
  }
}
