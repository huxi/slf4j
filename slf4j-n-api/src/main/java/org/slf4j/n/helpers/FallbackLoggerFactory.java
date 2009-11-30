package org.slf4j.n.helpers;

import org.slf4j.n.ILoggerFactory;
import org.slf4j.n.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * This is the ILoggerFactory implementation that is used if no
 * {@link org.slf4j.n.impl.StaticLoggerBinder} is found.
 * <p/>
 * In that case, Loggers wrapping org.slf4j.Logger are used (see {@link NewLoggerWrappingOld}).
 *
 * @author J&ouml;rn Huxhorn
 */
public class FallbackLoggerFactory
    implements ILoggerFactory {
  private final ConcurrentMap<String, Logger> loggers = new ConcurrentHashMap<String, Logger>();

  public Logger getLogger(String name) {
    if (Logger.ROOT_LOGGER_NAME.equalsIgnoreCase(name)) {
      name = Logger.ROOT_LOGGER_NAME;
    }
    Logger result = loggers.get(name);
    if (result != null) {
      return result;
    }
    return loggers.putIfAbsent(name, new NewLoggerWrappingOld(org.slf4j.LoggerFactory.getLogger(name)));
  }
}
