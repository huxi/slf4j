package org.slf4j.n.helpers;

import org.slf4j.Marker;
import org.slf4j.n.Level;
import org.slf4j.n.Logger;
import org.slf4j.n.messages.ParameterizedMessage;
import org.slf4j.n.messages.SimpleMessage;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * This class implements the org.slf4j.Logger and org.slf4j.spi.LocationAwareLogger interfaces
 * by providing a wrapper over an org.slf4j.n.Logger.
 * <p/>
 * It is used if a binding implements org.slf4j.n natively. An instance of this class is returned by
 * org.slf4j.n.Logger.getOldLogger(), wrapping org.slf4j.n.Logger.this.
 * <p/>
 * The ILoggerFactory returned by org.slf4j.impl.StaticLoggerBinder.getLoggerFactory() would simply
 * return org.slf4j.n.LoggerFactory.getLogger(..).getOldLogger().
 *
 * @author J&ouml;rn Huxhorn
 */
@SuppressWarnings({"ThrowableResultOfMethodCallIgnored"})
public class OldLoggerWrappingNew
    implements org.slf4j.spi.LocationAwareLogger, Serializable {
  private static final long serialVersionUID = 7394226401333702742L;
  private static final String FQCN = OldLoggerWrappingNew.class.getName();

  private String loggerName;
  private transient Logger logger;
  private transient org.slf4j.n.spi.LocationAwareLogger locationAwareLogger;

  // just for deserialization
  protected OldLoggerWrappingNew() {
  }

  public OldLoggerWrappingNew(Logger logger) {
    this.loggerName = logger.getName();
    this.logger = logger;
    if (this.logger instanceof org.slf4j.n.spi.LocationAwareLogger) {
      locationAwareLogger = (org.slf4j.n.spi.LocationAwareLogger) logger;
    }
  }

  public String getName() {
    return loggerName;
  }


  // ##### TRACE #####
  public boolean isTraceEnabled() {
    return logger.isEnabled(Level.TRACE, null);
  }

  public boolean isTraceEnabled(Marker marker) {
    return logger.isEnabled(Level.TRACE, marker);
  }

  public void trace(String msg) {
    if (!logger.isEnabled(Level.TRACE)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.TRACE, null, new SimpleMessage(msg), null);
    }
    else {
      locationAwareLogger.log(FQCN, Level.TRACE, null, msg, null);
    }
  }

  public void trace(String format, Object arg) {
    if (!logger.isEnabled(Level.TRACE)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg);
    if (locationAwareLogger == null) {
      logger.log(Level.TRACE, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.TRACE, null, message, message.getThrowable());
    }
  }

  public void trace(String format, Object arg1, Object arg2) {
    if (!logger.isEnabled(Level.TRACE)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg1, arg2);
    if (locationAwareLogger == null) {
      logger.log(Level.TRACE, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.TRACE, null, message, message.getThrowable());
    }
  }

  public void trace(String format, Object[] argArray) {
    if (!logger.isEnabled(Level.TRACE)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, argArray);
    if (locationAwareLogger == null) {
      logger.log(Level.TRACE, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.TRACE, null, message, message.getThrowable());
    }
  }

  public void trace(String msg, Throwable t) {
    if (!logger.isEnabled(Level.TRACE)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.TRACE, null, new SimpleMessage(msg), t);
    }
    else {
      locationAwareLogger.log(FQCN, Level.TRACE, null, msg, t);
    }
  }

  public void trace(Marker marker, String msg) {
    if (!logger.isEnabled(Level.TRACE, marker)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.TRACE, marker, new SimpleMessage(msg), null);
    }
    else {
      locationAwareLogger.log(FQCN, Level.TRACE, marker, msg, null);
    }
  }

  public void trace(Marker marker, String format, Object arg) {
    if (!logger.isEnabled(Level.TRACE, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg);
    if (locationAwareLogger == null) {
      logger.log(Level.TRACE, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.TRACE, marker, message, message.getThrowable());
    }
  }

  public void trace(Marker marker, String format, Object arg1, Object arg2) {
    if (!logger.isEnabled(Level.TRACE, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg1, arg2);
    if (locationAwareLogger == null) {
      logger.log(Level.TRACE, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.TRACE, marker, message, message.getThrowable());
    }
  }

  public void trace(Marker marker, String format, Object[] argArray) {
    if (!logger.isEnabled(Level.TRACE, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, argArray);
    if (locationAwareLogger == null) {
      logger.log(Level.TRACE, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.TRACE, marker, message, message.getThrowable());
    }
  }

  public void trace(Marker marker, String msg, Throwable t) {
    if (!logger.isEnabled(Level.TRACE, marker)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.TRACE, marker, new SimpleMessage(msg), t);
    }
    else {
      locationAwareLogger.log(FQCN, Level.TRACE, marker, msg, t);
    }
  }

  // ##### DEBUG #####
  public boolean isDebugEnabled() {
    return logger.isEnabled(Level.DEBUG, null);
  }

  public boolean isDebugEnabled(Marker marker) {
    return logger.isEnabled(Level.DEBUG, marker);
  }

  public void debug(String msg) {
    if (!logger.isEnabled(Level.DEBUG)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.DEBUG, null, new SimpleMessage(msg), null);
    }
    else {
      locationAwareLogger.log(FQCN, Level.DEBUG, null, msg, null);
    }
  }

  public void debug(String format, Object arg) {
    if (!logger.isEnabled(Level.DEBUG)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg);
    if (locationAwareLogger == null) {
      logger.log(Level.DEBUG, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.DEBUG, null, message, message.getThrowable());
    }
  }

  public void debug(String format, Object arg1, Object arg2) {
    if (!logger.isEnabled(Level.DEBUG)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg1, arg2);
    if (locationAwareLogger == null) {
      logger.log(Level.DEBUG, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.DEBUG, null, message, message.getThrowable());
    }
  }

  public void debug(String format, Object[] argArray) {
    if (!logger.isEnabled(Level.DEBUG)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, argArray);
    if (locationAwareLogger == null) {
      logger.log(Level.DEBUG, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.DEBUG, null, message, message.getThrowable());
    }
  }

  public void debug(String msg, Throwable t) {
    if (!logger.isEnabled(Level.DEBUG)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.DEBUG, null, new SimpleMessage(msg), t);
    }
    else {
      locationAwareLogger.log(FQCN, Level.DEBUG, null, msg, t);
    }
  }

  public void debug(Marker marker, String msg) {
    if (!logger.isEnabled(Level.DEBUG, marker)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.DEBUG, marker, new SimpleMessage(msg), null);
    }
    else {
      locationAwareLogger.log(FQCN, Level.DEBUG, marker, msg, null);
    }
  }

  public void debug(Marker marker, String format, Object arg) {
    if (!logger.isEnabled(Level.DEBUG, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg);
    if (locationAwareLogger == null) {
      logger.log(Level.DEBUG, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.DEBUG, marker, message, message.getThrowable());
    }
  }

  public void debug(Marker marker, String format, Object arg1, Object arg2) {
    if (!logger.isEnabled(Level.DEBUG, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg1, arg2);
    if (locationAwareLogger == null) {
      logger.log(Level.DEBUG, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.DEBUG, marker, message, message.getThrowable());
    }
  }

  public void debug(Marker marker, String format, Object[] argArray) {
    if (!logger.isEnabled(Level.DEBUG, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, argArray);
    if (locationAwareLogger == null) {
      logger.log(Level.DEBUG, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.DEBUG, marker, message, message.getThrowable());
    }
  }

  public void debug(Marker marker, String msg, Throwable t) {
    if (!logger.isEnabled(Level.DEBUG, marker)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.DEBUG, marker, new SimpleMessage(msg), t);
    }
    else {
      locationAwareLogger.log(FQCN, Level.DEBUG, marker, msg, t);
    }
  }


  // ##### INFO #####
  public boolean isInfoEnabled() {
    return logger.isEnabled(Level.INFO, null);
  }

  public boolean isInfoEnabled(Marker marker) {
    return logger.isEnabled(Level.INFO, marker);
  }

  public void info(String msg) {
    if (!logger.isEnabled(Level.INFO)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.INFO, null, new SimpleMessage(msg), null);
    }
    else {
      locationAwareLogger.log(FQCN, Level.INFO, null, msg, null);
    }
  }

  public void info(String format, Object arg) {
    if (!logger.isEnabled(Level.INFO)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg);
    if (locationAwareLogger == null) {
      logger.log(Level.INFO, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.INFO, null, message, message.getThrowable());
    }
  }

  public void info(String format, Object arg1, Object arg2) {
    if (!logger.isEnabled(Level.INFO)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg1, arg2);
    if (locationAwareLogger == null) {
      logger.log(Level.INFO, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.INFO, null, message, message.getThrowable());
    }
  }

  public void info(String format, Object[] argArray) {
    if (!logger.isEnabled(Level.INFO)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, argArray);
    if (locationAwareLogger == null) {
      logger.log(Level.INFO, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.INFO, null, message, message.getThrowable());
    }
  }

  public void info(String msg, Throwable t) {
    if (!logger.isEnabled(Level.INFO)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.INFO, null, new SimpleMessage(msg), t);
    }
    else {
      locationAwareLogger.log(FQCN, Level.INFO, null, msg, t);
    }
  }

  public void info(Marker marker, String msg) {
    if (!logger.isEnabled(Level.INFO, marker)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.INFO, marker, new SimpleMessage(msg), null);
    }
    else {
      locationAwareLogger.log(FQCN, Level.INFO, marker, msg, null);
    }
  }

  public void info(Marker marker, String format, Object arg) {
    if (!logger.isEnabled(Level.INFO, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg);
    if (locationAwareLogger == null) {
      logger.log(Level.INFO, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.INFO, marker, message, message.getThrowable());
    }
  }

  public void info(Marker marker, String format, Object arg1, Object arg2) {
    if (!logger.isEnabled(Level.INFO, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg1, arg2);
    if (locationAwareLogger == null) {
      logger.log(Level.INFO, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.INFO, marker, message, message.getThrowable());
    }
  }

  public void info(Marker marker, String format, Object[] argArray) {
    if (!logger.isEnabled(Level.INFO, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, argArray);
    if (locationAwareLogger == null) {
      logger.log(Level.INFO, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.INFO, marker, message, message.getThrowable());
    }
  }

  public void info(Marker marker, String msg, Throwable t) {
    if (!logger.isEnabled(Level.INFO, marker)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.INFO, marker, new SimpleMessage(msg), t);
    }
    else {
      locationAwareLogger.log(FQCN, Level.INFO, marker, msg, t);
    }
  }

  // ##### WARN #####
  public boolean isWarnEnabled() {
    return logger.isEnabled(Level.WARN, null);
  }

  public boolean isWarnEnabled(Marker marker) {
    return logger.isEnabled(Level.WARN, marker);
  }

  public void warn(String msg) {
    if (!logger.isEnabled(Level.WARN)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.WARN, null, new SimpleMessage(msg), null);
    }
    else {
      locationAwareLogger.log(FQCN, Level.WARN, null, msg, null);
    }
  }

  public void warn(String format, Object arg) {
    if (!logger.isEnabled(Level.WARN)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg);
    if (locationAwareLogger == null) {
      logger.log(Level.WARN, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.WARN, null, message, message.getThrowable());
    }
  }

  public void warn(String format, Object arg1, Object arg2) {
    if (!logger.isEnabled(Level.WARN)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg1, arg2);
    if (locationAwareLogger == null) {
      logger.log(Level.WARN, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.WARN, null, message, message.getThrowable());
    }
  }

  public void warn(String format, Object[] argArray) {
    if (!logger.isEnabled(Level.WARN)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, argArray);
    if (locationAwareLogger == null) {
      logger.log(Level.WARN, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.WARN, null, message, message.getThrowable());
    }
  }

  public void warn(String msg, Throwable t) {
    if (!logger.isEnabled(Level.WARN)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.WARN, null, new SimpleMessage(msg), t);
    }
    else {
      locationAwareLogger.log(FQCN, Level.WARN, null, msg, t);
    }
  }

  public void warn(Marker marker, String msg) {
    if (!logger.isEnabled(Level.WARN, marker)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.WARN, marker, new SimpleMessage(msg), null);
    }
    else {
      locationAwareLogger.log(FQCN, Level.WARN, marker, msg, null);
    }
  }

  public void warn(Marker marker, String format, Object arg) {
    if (!logger.isEnabled(Level.WARN, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg);
    if (locationAwareLogger == null) {
      logger.log(Level.WARN, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.WARN, marker, message, message.getThrowable());
    }
  }

  public void warn(Marker marker, String format, Object arg1, Object arg2) {
    if (!logger.isEnabled(Level.WARN, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg1, arg2);
    if (locationAwareLogger == null) {
      logger.log(Level.WARN, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.WARN, marker, message, message.getThrowable());
    }
  }

  public void warn(Marker marker, String format, Object[] argArray) {
    if (!logger.isEnabled(Level.WARN, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, argArray);
    if (locationAwareLogger == null) {
      logger.log(Level.WARN, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.WARN, marker, message, message.getThrowable());
    }
  }

  public void warn(Marker marker, String msg, Throwable t) {
    if (!logger.isEnabled(Level.WARN, marker)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.WARN, marker, new SimpleMessage(msg), t);
    }
    else {
      locationAwareLogger.log(FQCN, Level.WARN, marker, msg, t);
    }
  }

  // ##### ERROR #####
  public boolean isErrorEnabled() {
    return logger.isEnabled(Level.ERROR, null);
  }

  public boolean isErrorEnabled(Marker marker) {
    return logger.isEnabled(Level.ERROR, marker);
  }

  public void error(String msg) {
    if (!logger.isEnabled(Level.ERROR)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.ERROR, null, new SimpleMessage(msg), null);
    }
    else {
      locationAwareLogger.log(FQCN, Level.ERROR, null, msg, null);
    }
  }

  public void error(String format, Object arg) {
    if (!logger.isEnabled(Level.ERROR)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg);
    if (locationAwareLogger == null) {
      logger.log(Level.ERROR, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.ERROR, null, message, message.getThrowable());
    }
  }

  public void error(String format, Object arg1, Object arg2) {
    if (!logger.isEnabled(Level.ERROR)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg1, arg2);
    if (locationAwareLogger == null) {
      logger.log(Level.ERROR, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.ERROR, null, message, message.getThrowable());
    }
  }

  public void error(String format, Object[] argArray) {
    if (!logger.isEnabled(Level.ERROR)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, argArray);
    if (locationAwareLogger == null) {
      logger.log(Level.ERROR, null, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.ERROR, null, message, message.getThrowable());
    }
  }

  public void error(String msg, Throwable t) {
    if (!logger.isEnabled(Level.ERROR)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.ERROR, null, new SimpleMessage(msg), t);
    }
    else {
      locationAwareLogger.log(FQCN, Level.ERROR, null, msg, t);
    }
  }

  public void error(Marker marker, String msg) {
    if (!logger.isEnabled(Level.ERROR, marker)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.ERROR, marker, new SimpleMessage(msg), null);
    }
    else {
      locationAwareLogger.log(FQCN, Level.ERROR, marker, msg, null);
    }
  }

  public void error(Marker marker, String format, Object arg) {
    if (!logger.isEnabled(Level.ERROR, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg);
    if (locationAwareLogger == null) {
      logger.log(Level.ERROR, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.ERROR, marker, message, message.getThrowable());
    }
  }

  public void error(Marker marker, String format, Object arg1, Object arg2) {
    if (!logger.isEnabled(Level.ERROR, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, arg1, arg2);
    if (locationAwareLogger == null) {
      logger.log(Level.ERROR, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.ERROR, marker, message, message.getThrowable());
    }
  }

  public void error(Marker marker, String format, Object[] argArray) {
    if (!logger.isEnabled(Level.ERROR, marker)) return;

    ParameterizedMessage message = ParameterizedMessage.create(format, argArray);
    if (locationAwareLogger == null) {
      logger.log(Level.ERROR, marker, message, message.getThrowable());
    }
    else {
      locationAwareLogger.log(FQCN, Level.ERROR, marker, message, message.getThrowable());
    }
  }

  public void error(Marker marker, String msg, Throwable t) {
    if (!logger.isEnabled(Level.ERROR, marker)) return;

    if (locationAwareLogger == null) {
      logger.log(Level.ERROR, marker, new SimpleMessage(msg), t);
    }
    else {
      locationAwareLogger.log(FQCN, Level.ERROR, marker, msg, t);
    }
  }

  private Object readResolve()
      throws ObjectStreamException {
    return org.slf4j.LoggerFactory.getLogger(loggerName);
  }

  public void log(Marker marker, String fqcn, int level, String message, Throwable t) {
    if (locationAwareLogger == null) {
      switch (level) {
        case org.slf4j.spi.LocationAwareLogger.TRACE_INT:
          logger.log(Level.TRACE, marker, message, t);
          break;
        case org.slf4j.spi.LocationAwareLogger.DEBUG_INT:
          logger.log(Level.DEBUG, marker, message, t);
          break;
        case org.slf4j.spi.LocationAwareLogger.INFO_INT:
          logger.log(Level.INFO, marker, message, t);
          break;
        case org.slf4j.spi.LocationAwareLogger.WARN_INT:
          logger.log(Level.WARN, marker, message, t);
          break;
        case org.slf4j.spi.LocationAwareLogger.ERROR_INT:
          logger.log(Level.ERROR, marker, message, t);
          break;
      }
    }
    else {
      switch (level) {
        case org.slf4j.spi.LocationAwareLogger.TRACE_INT:
          locationAwareLogger.log(FQCN, Level.TRACE, marker, message, t);
          break;
        case org.slf4j.spi.LocationAwareLogger.DEBUG_INT:
          locationAwareLogger.log(FQCN, Level.DEBUG, marker, message, t);
          break;
        case org.slf4j.spi.LocationAwareLogger.INFO_INT:
          locationAwareLogger.log(FQCN, Level.INFO, marker, message, t);
          break;
        case org.slf4j.spi.LocationAwareLogger.WARN_INT:
          locationAwareLogger.log(FQCN, Level.WARN, marker, message, t);
          break;
        case org.slf4j.spi.LocationAwareLogger.ERROR_INT:
          locationAwareLogger.log(FQCN, Level.ERROR, marker, message, t);
          break;
      }
    }
  }
}
