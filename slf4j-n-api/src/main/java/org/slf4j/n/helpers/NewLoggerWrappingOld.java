package org.slf4j.n.helpers;

import org.slf4j.Marker;
import org.slf4j.Logger;
import org.slf4j.n.Threshold;
import org.slf4j.n.Level;
import org.slf4j.n.Message;
import org.slf4j.n.LoggerFactory;
import org.slf4j.n.messages.ParameterizedMessage;
import org.slf4j.n.spi.LocationAwareLogger;

import java.io.ObjectStreamException;

/**
 * This class implements the org.slf4j.n.Logger and org.slf4j.n.spi.LocationAwareLogger interfaces
 * by providing a wrapper over an org.slf4j.Logger.
 *
 * @author J&ouml;rn Huxhorn
 */
@SuppressWarnings({"ThrowableResultOfMethodCallIgnored"})
public class NewLoggerWrappingOld
    implements LocationAwareLogger
{
  private static final long serialVersionUID = 4801490744284446815L;

  private static final String FQCN = NewLoggerWrappingOld.class.getName();

  private final String loggerName;
  private transient org.slf4j.Logger logger;
  private transient org.slf4j.spi.LocationAwareLogger locationAwareLogger;

  public NewLoggerWrappingOld(org.slf4j.Logger logger)
  {
    this.logger = logger;
    if(this.logger instanceof org.slf4j.spi.LocationAwareLogger)
    {
      this.locationAwareLogger = (org.slf4j.spi.LocationAwareLogger) this.logger;
    }
    this.loggerName = logger.getName();
  }

  public String getName()
  {
    return loggerName;
  }

  public Logger getOldLogger()
  {
    return logger;
  }

  /**
   * @return the Threshold of this Logger.
   */
  public Threshold getThreshold()
  {
    if(logger.isErrorEnabled())
    {
      return Threshold.ERROR;
    }
    if(logger.isWarnEnabled())
    {
      return Threshold.WARN;
    }
    if(logger.isInfoEnabled())
    {
      return Threshold.INFO;
    }
    if(logger.isDebugEnabled())
    {
      return Threshold.DEBUG;
    }
    if(logger.isTraceEnabled())
    {
      return Threshold.TRACE;
    }
    return Threshold.OFF;
  }

  // generic logging methods
  public boolean isLoggingEnabled(Level level)
  {
    switch(level)
    {
      case TRACE:
        return logger.isTraceEnabled();
      case DEBUG:
        return logger.isDebugEnabled();
      case INFO:
        return logger.isInfoEnabled();
      case WARN:
        return logger.isWarnEnabled();
      case ERROR:
        return logger.isErrorEnabled();
    }

    return false;
  }

  public boolean isLoggingEnabled(Level level, Marker marker)
  {
    switch(level)
    {
      case TRACE:
        return logger.isTraceEnabled(marker);
      case DEBUG:
        return logger.isDebugEnabled(marker);
      case INFO:
        return logger.isInfoEnabled(marker);
      case WARN:
        return logger.isWarnEnabled(marker);
      case ERROR:
        return logger.isErrorEnabled(marker);
    }

    return false;
  }

  public void log(Level level, String messagePattern, Object... args)
  {
    if(!isLoggingEnabled(level))
    {
      return;
    }
    ParameterizedMessage message = ParameterizedMessage.create(messagePattern, args);

    if(locationAwareLogger == null)
    {
      switch(level)
      {
        case TRACE:
          logger.trace(message.getFormattedMessage(), message.getThrowable());
          break;
        case DEBUG:
          logger.debug(message.getFormattedMessage(), message.getThrowable());
          break;
        case INFO:
          logger.info(message.getFormattedMessage(), message.getThrowable());
          break;
        case WARN:
          logger.warn(message.getFormattedMessage(), message.getThrowable());
          break;
        case ERROR:
          logger.error(message.getFormattedMessage(), message.getThrowable());
          break;
      }
    }
    else
    {
      switch(level)
      {
        case TRACE:
          locationAwareLogger.log(null, FQCN, org.slf4j.spi.LocationAwareLogger.TRACE_INT, message.getFormattedMessage(), message.getThrowable());
          break;
        case DEBUG:
          locationAwareLogger.log(null, FQCN, org.slf4j.spi.LocationAwareLogger.DEBUG_INT, message.getFormattedMessage(), message.getThrowable());
          break;
        case INFO:
          locationAwareLogger.log(null, FQCN, org.slf4j.spi.LocationAwareLogger.INFO_INT, message.getFormattedMessage(), message.getThrowable());
          break;
        case WARN:
          locationAwareLogger.log(null, FQCN, org.slf4j.spi.LocationAwareLogger.WARN_INT, message.getFormattedMessage(), message.getThrowable());
          break;
        case ERROR:
          locationAwareLogger.log(null, FQCN, org.slf4j.spi.LocationAwareLogger.ERROR_INT, message.getFormattedMessage(), message.getThrowable());
          break;
      }
    }
  }

  public void log(Level level, Marker marker, String messagePattern, Object... args)
  {
    if(!isLoggingEnabled(level))
    {
      return;
    }
    ParameterizedMessage message = ParameterizedMessage.create(messagePattern, args);

    if(locationAwareLogger == null)
    {
      switch(level)
      {
        case TRACE:
          logger.trace(marker, message.getFormattedMessage(), message.getThrowable());
          break;
        case DEBUG:
          logger.debug(marker, message.getFormattedMessage(), message.getThrowable());
          break;
        case INFO:
          logger.info(marker, message.getFormattedMessage(), message.getThrowable());
          break;
        case WARN:
          logger.warn(marker, message.getFormattedMessage(), message.getThrowable());
          break;
        case ERROR:
          logger.error(marker, message.getFormattedMessage(), message.getThrowable());
          break;
      }
    }
    else
    {
      switch(level)
      {
        case TRACE:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.TRACE_INT, message.getFormattedMessage(), message.getThrowable());
          break;
        case DEBUG:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.DEBUG_INT, message.getFormattedMessage(), message.getThrowable());
          break;
        case INFO:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.INFO_INT, message.getFormattedMessage(), message.getThrowable());
          break;
        case WARN:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.WARN_INT, message.getFormattedMessage(), message.getThrowable());
          break;
        case ERROR:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.ERROR_INT, message.getFormattedMessage(), message.getThrowable());
          break;
      }
    }
  }

  public void log(Level level, Message message)
  {
    if(isLoggingEnabled(level))
    {
      if(message instanceof ParameterizedMessage)
      {
        log(level, message.getFormattedMessage(), ((ParameterizedMessage)message).getThrowable());
      }
      else
      {
        log(level, message.getFormattedMessage());
      }
    }
  }

  public void log(Level level, Message message, Throwable throwable)
  {
    if(isLoggingEnabled(level))
    {
      log(level, message.getFormattedMessage(), throwable);
    }
  }


  public void log(Level level, Marker marker, Message message)
  {
    if (isLoggingEnabled(level, marker))
    {
      if(message instanceof ParameterizedMessage)
      {
        log(level, marker, message.getFormattedMessage(), ((ParameterizedMessage)message).getThrowable());
      }
      else
      {
        log(level, marker, message.getFormattedMessage());
      }
    }

  }

  public void log(Level level, Marker marker, Message message, Throwable throwable)
  {
    if (isLoggingEnabled(level, marker))
    {
      log(level, marker, message.getFormattedMessage(), throwable);
    }
  }

  // ##### TRACE #####
  public boolean isTraceEnabled()
  {
    return isLoggingEnabled(Level.TRACE);
  }

  public boolean isTraceEnabled(Marker marker)
  {
    return isLoggingEnabled(Level.TRACE, marker);
  }

  public void trace(String messagePattern, Object... args)
  {
    log(Level.TRACE, messagePattern, args);
  }

  public void trace(Marker marker, String messagePattern, Object... args)
  {
    log(Level.TRACE, marker, messagePattern, args);
  }

  public void trace(Message message)
  {
    log(Level.TRACE, message);
  }

  public void trace(Message message, Throwable throwable)
  {
    log(Level.TRACE, message, throwable);
  }

  public void trace(Marker marker, Message message)
  {
    log(Level.TRACE, marker, message);
  }

  public void trace(Marker marker, Message message, Throwable throwable)
  {
    log(Level.TRACE, marker, message, throwable);
  }

  // ##### DEBUG #####
  public boolean isDebugEnabled()
  {
    return isLoggingEnabled(Level.DEBUG);
  }

  public boolean isDebugEnabled(Marker marker)
  {
    return isLoggingEnabled(Level.DEBUG, marker);
  }

  public void debug(String messagePattern, Object... args)
  {
    log(Level.DEBUG, messagePattern, args);
  }

  public void debug(Marker marker, String messagePattern, Object... args)
  {
    log(Level.DEBUG, marker, messagePattern, args);
  }

  public void debug(Message message)
  {
    log(Level.DEBUG, message);
  }

  public void debug(Message message, Throwable throwable)
  {
    log(Level.DEBUG, message, throwable);
  }

  public void debug(Marker marker, Message message)
  {
    log(Level.DEBUG, marker, message);
  }

  public void debug(Marker marker, Message message, Throwable throwable)
  {
    log(Level.DEBUG, marker, message, throwable);
  }

  // ##### INFO #####
  public boolean isInfoEnabled()
  {
    return isLoggingEnabled(Level.INFO);
  }

  public boolean isInfoEnabled(Marker marker)
  {
    return isLoggingEnabled(Level.INFO, marker);
  }

  public void info(String messagePattern, Object... args)
  {
    log(Level.INFO, messagePattern, args);
  }

  public void info(Marker marker, String messagePattern, Object... args)
  {
    log(Level.INFO, marker, messagePattern, args);
  }

  public void info(Message message)
  {
    log(Level.INFO, message);
  }

  public void info(Message message, Throwable throwable)
  {
    log(Level.INFO, message, throwable);
  }

  public void info(Marker marker, Message message)
  {
    log(Level.INFO, marker, message);
  }

  public void info(Marker marker, Message message, Throwable throwable)
  {
    log(Level.INFO, marker, message, throwable);
  }

  // ##### WARN #####
  public boolean isWarnEnabled()
  {
    return isLoggingEnabled(Level.WARN);
  }

  public boolean isWarnEnabled(Marker marker)
  {
    return isLoggingEnabled(Level.WARN, marker);
  }

  public void warn(String messagePattern, Object... args)
  {
    log(Level.WARN, messagePattern, args);
  }

  public void warn(Marker marker, String messagePattern, Object... args)
  {
    log(Level.WARN, marker, messagePattern, args);
  }

  public void warn(Message message)
  {
    log(Level.WARN, message);
  }

  public void warn(Message message, Throwable throwable)
  {
    log(Level.WARN, message, throwable);
  }

  public void warn(Marker marker, Message message)
  {
    log(Level.WARN, marker, message);
  }

  public void warn(Marker marker, Message message, Throwable throwable)
  {
    log(Level.WARN, marker, message, throwable);
  }

  // ##### ERROR #####
  public boolean isErrorEnabled()
  {
    return isLoggingEnabled(Level.ERROR);
  }

  public boolean isErrorEnabled(Marker marker)
  {
    return isLoggingEnabled(Level.ERROR, marker);
  }

  public void error(String messagePattern, Object... args)
  {
    log(Level.ERROR, messagePattern, args);
  }

  public void error(Marker marker, String messagePattern, Object... args)
  {
    log(Level.ERROR, marker, messagePattern, args);
  }

  public void error(Message message)
  {
    log(Level.ERROR, message);
  }

  public void error(Message message, Throwable throwable)
  {
    log(Level.ERROR, message, throwable);
  }

  public void error(Marker marker, Message message)
  {
    log(Level.ERROR, marker, message);
  }

  public void error(Marker marker, Message message, Throwable throwable)
  {
    log(Level.ERROR, marker, message, throwable);
  }

  private Object readResolve()
    		throws ObjectStreamException
  {
    return LoggerFactory.getLogger(loggerName);
  }

  public void log(String fqcn, Level level, Marker marker, Message message, Throwable throwable)
  {
    if(locationAwareLogger == null)
    {
      switch(level)
      {
        case TRACE:
          logger.trace(marker, message.getFormattedMessage(), throwable);
          break;
        case DEBUG:
          logger.debug(marker, message.getFormattedMessage(), throwable);
          break;
        case INFO:
          logger.info(marker, message.getFormattedMessage(), throwable);
          break;
        case WARN:
          logger.warn(marker, message.getFormattedMessage(), throwable);
          break;
        case ERROR:
          logger.error(marker, message.getFormattedMessage(), throwable);
          break;
      }
    }
    else
    {
      switch(level)
      {
        case TRACE:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.TRACE_INT, message.getFormattedMessage(), throwable);
          break;
        case DEBUG:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.DEBUG_INT, message.getFormattedMessage(), throwable);
          break;
        case INFO:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.INFO_INT, message.getFormattedMessage(), throwable);
          break;
        case WARN:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.WARN_INT, message.getFormattedMessage(), throwable);
          break;
        case ERROR:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.ERROR_INT, message.getFormattedMessage(), throwable);
          break;
      }
    }
  }

  public void log(String fqcn, Level level, Marker marker, String message, Throwable throwable)
  {
    if(locationAwareLogger == null)
    {
      switch(level)
      {
        case TRACE:
          logger.trace(marker, message, throwable);
          break;
        case DEBUG:
          logger.debug(marker, message, throwable);
          break;
        case INFO:
          logger.info(marker, message, throwable);
          break;
        case WARN:
          logger.warn(marker, message, throwable);
          break;
        case ERROR:
          logger.error(marker, message, throwable);
          break;
      }
    }
    else
    {
      switch(level)
      {
        case TRACE:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.TRACE_INT, message, throwable);
          break;
        case DEBUG:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.DEBUG_INT, message, throwable);
          break;
        case INFO:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.INFO_INT, message, throwable);
          break;
        case WARN:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.WARN_INT, message, throwable);
          break;
        case ERROR:
          locationAwareLogger.log(marker, FQCN, org.slf4j.spi.LocationAwareLogger.ERROR_INT, message, throwable);
          break;
      }
    }
  }
}
