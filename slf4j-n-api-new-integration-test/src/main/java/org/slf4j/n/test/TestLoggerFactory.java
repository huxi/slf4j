package org.slf4j.n.test;

import org.slf4j.n.ILoggerFactory;
import org.slf4j.n.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TestLoggerFactory
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
    result = new TestLogger(name);
    Logger retrieved = loggers.putIfAbsent(name, result);
    if (retrieved != null) {
      return retrieved;
    }
    return result;
  }
}
