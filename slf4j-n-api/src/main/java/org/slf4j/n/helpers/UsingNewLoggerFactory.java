package org.slf4j.n.helpers;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.n.LoggerFactory;

/**
 * ILoggerFactory implementation that retrieves an org.slf4j.n.Logger
 * to resolve the org.slf4j.Logger using getOldLogger().
 * <p/>
 * This class would be returned by org.slf4j.impl.StaticLoggerBinder
 * if the binding implements org.slf4j.n directly.
 *
 * @author J&ouml;rn Huxhorn
 */
public class UsingNewLoggerFactory
    implements ILoggerFactory {

  /**
   * @param name the name of the Logger to return
   * @return org.slf4j.n.LoggerFactory.getLogger(name).getOldLogger();
   */
  public Logger getLogger(String name) {
    return LoggerFactory.getLogger(name).getOldLogger();
  }
}
