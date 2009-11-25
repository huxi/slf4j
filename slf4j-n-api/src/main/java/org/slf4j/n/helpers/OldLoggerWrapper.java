package org.slf4j.n.helpers;

import org.slf4j.n.Logger;
import org.slf4j.n.Level;
import org.slf4j.n.messages.SimpleMessage;
import org.slf4j.n.messages.ParameterizedMessage;
import org.slf4j.Marker;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class OldLoggerWrapper
  implements org.slf4j.spi.LocationAwareLogger, Serializable
{
    private static final String FQCN = OldLoggerWrapper.class.getName();

    private String loggerName;
    private transient Logger logger;
    private transient org.slf4j.n.spi.LocationAwareLogger locationAwareLogger;

    // just for deserialization
    protected OldLoggerWrapper()
    {
    }

    public OldLoggerWrapper(Logger logger)
    {
      this.loggerName = logger.getName();
      this.logger = logger;
      if(this.logger instanceof org.slf4j.n.spi.LocationAwareLogger)
      {
        locationAwareLogger = (org.slf4j.n.spi.LocationAwareLogger) logger;
      }
    }

    public String getName()
    {
      return loggerName;
    }


    // ##### TRACE #####
    public boolean isTraceEnabled()
    {
      return logger.isLoggingEnabled(Level.TRACE, null);
    }

    public boolean isTraceEnabled(Marker marker)
    {
      return logger.isLoggingEnabled(Level.TRACE, marker);
    }

    public void trace(String msg)
    {
      if(!logger.isLoggingEnabled(Level.TRACE)) return;

      logger.log(Level.TRACE, null, new SimpleMessage(msg), null);
    }

    public void trace(String format, Object arg)
    {
      if(!logger.isLoggingEnabled(Level.TRACE)) return;

      logger.log(Level.TRACE, null, ParameterizedMessage.create(format, arg));
    }

    public void trace(String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.TRACE)) return;

      logger.log(Level.TRACE, null, ParameterizedMessage.create(format, arg1, arg2));
    }

    public void trace(String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.TRACE)) return;

      logger.log(Level.TRACE, null, ParameterizedMessage.create(format, argArray));
    }

    public void trace(String msg, Throwable t)
    {
      if(!logger.isLoggingEnabled(Level.TRACE)) return;

      logger.log(Level.TRACE, null, new SimpleMessage(msg), t);
    }

    public void trace(Marker marker, String msg)
    {
      if(!logger.isLoggingEnabled(Level.TRACE, marker)) return;

      logger.log(Level.TRACE, marker, new SimpleMessage(msg), null);
    }

    public void trace(Marker marker, String format, Object arg)
    {
      if(!logger.isLoggingEnabled(Level.TRACE, marker)) return;

      logger.log(Level.TRACE, marker, ParameterizedMessage.create(format, arg));
    }

    public void trace(Marker marker, String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.TRACE, marker)) return;

      logger.log(Level.TRACE, marker, ParameterizedMessage.create(format, arg1, arg2));
    }

    public void trace(Marker marker, String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.TRACE, marker)) return;

      logger.log(Level.TRACE, marker, ParameterizedMessage.create(format, argArray));
    }

    public void trace(Marker marker, String msg, Throwable t)
    {
      if(!logger.isLoggingEnabled(Level.TRACE, marker)) return;

      logger.log(Level.TRACE, marker, new SimpleMessage(msg), t);
    }

    // ##### DEBUG #####
    public boolean isDebugEnabled()
    {
      return logger.isLoggingEnabled(Level.DEBUG, null);
    }

    public boolean isDebugEnabled(Marker marker)
    {
      return logger.isLoggingEnabled(Level.DEBUG, marker);
    }

    public void debug(String msg)
    {
      if(!logger.isLoggingEnabled(Level.DEBUG)) return;

      logger.log(Level.DEBUG, null, new SimpleMessage(msg), null);
    }

    public void debug(String format, Object arg)
    {
      if(!logger.isLoggingEnabled(Level.DEBUG)) return;

      logger.log(Level.DEBUG, null, ParameterizedMessage.create(format, arg));
    }

    public void debug(String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.DEBUG)) return;

      logger.log(Level.DEBUG, null, ParameterizedMessage.create(format, arg1, arg2));
    }

    public void debug(String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.DEBUG)) return;

      logger.log(Level.DEBUG, null, ParameterizedMessage.create(format, argArray));
    }

    public void debug(String msg, Throwable t)
    {
      if(!logger.isLoggingEnabled(Level.DEBUG)) return;

      logger.log(Level.DEBUG, null, new SimpleMessage(msg), t);
    }

    public void debug(Marker marker, String msg)
    {
      if(!logger.isLoggingEnabled(Level.DEBUG, marker)) return;

      logger.log(Level.DEBUG, marker, new SimpleMessage(msg), null);
    }

    public void debug(Marker marker, String format, Object arg)
    {
      if(!logger.isLoggingEnabled(Level.DEBUG, marker)) return;

      logger.log(Level.DEBUG, marker, ParameterizedMessage.create(format, arg));
    }

    public void debug(Marker marker, String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.DEBUG, marker)) return;

      logger.log(Level.DEBUG, marker, ParameterizedMessage.create(format, arg1, arg2));
    }

    public void debug(Marker marker, String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.DEBUG, marker)) return;

      logger.log(Level.DEBUG, marker, ParameterizedMessage.create(format, argArray));
    }

    public void debug(Marker marker, String msg, Throwable t)
    {
      if(!logger.isLoggingEnabled(Level.DEBUG, marker)) return;

      logger.log(Level.DEBUG, marker, new SimpleMessage(msg), t);
    }

    // ##### INFO #####
    public boolean isInfoEnabled()
    {
      return logger.isLoggingEnabled(Level.INFO, null);
    }

    public boolean isInfoEnabled(Marker marker)
    {
      return logger.isLoggingEnabled(Level.INFO, marker);
    }

    public void info(String msg)
    {
      if(!logger.isLoggingEnabled(Level.INFO)) return;

      logger.log(Level.INFO, null, new SimpleMessage(msg), null);
    }

    public void info(String format, Object arg)
    {
      if(!logger.isLoggingEnabled(Level.INFO)) return;

      logger.log(Level.INFO, null, ParameterizedMessage.create(format, arg));
    }

    public void info(String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.INFO)) return;

      logger.log(Level.INFO, null, ParameterizedMessage.create(format, arg1, arg2));
    }

    public void info(String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.INFO)) return;

      logger.log(Level.INFO, null, ParameterizedMessage.create(format, argArray));
    }

    public void info(String msg, Throwable t)
    {
      if(!logger.isLoggingEnabled(Level.INFO)) return;

      logger.log(Level.INFO, null, new SimpleMessage(msg), t);
    }

    public void info(Marker marker, String msg)
    {
      if(!logger.isLoggingEnabled(Level.INFO, marker)) return;

      logger.log(Level.INFO, marker, new SimpleMessage(msg), null);
    }

    public void info(Marker marker, String format, Object arg)
    {
      if(!logger.isLoggingEnabled(Level.INFO, marker)) return;

      logger.log(Level.INFO, marker, ParameterizedMessage.create(format, arg));
    }

    public void info(Marker marker, String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.INFO, marker)) return;

      logger.log(Level.INFO, marker, ParameterizedMessage.create(format, arg1, arg2));
    }

    public void info(Marker marker, String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.INFO, marker)) return;

      logger.log(Level.INFO, marker, ParameterizedMessage.create(format, argArray));
    }

    public void info(Marker marker, String msg, Throwable t)
    {
      if(!logger.isLoggingEnabled(Level.INFO, marker)) return;

      logger.log(Level.INFO, marker, new SimpleMessage(msg), t);
    }

    // ##### WARN #####
    public boolean isWarnEnabled()
    {
      return logger.isLoggingEnabled(Level.WARN, null);
    }

    public boolean isWarnEnabled(Marker marker)
    {
      return logger.isLoggingEnabled(Level.WARN, marker);
    }

    public void warn(String msg)
    {
      if(!logger.isLoggingEnabled(Level.WARN)) return;

      logger.log(Level.WARN, null, new SimpleMessage(msg), null);
    }

    public void warn(String format, Object arg)
    {
      if(!logger.isLoggingEnabled(Level.WARN)) return;

      logger.log(Level.WARN, null, ParameterizedMessage.create(format, arg));
    }

    public void warn(String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.WARN)) return;

      logger.log(Level.WARN, null, ParameterizedMessage.create(format, arg1, arg2));
    }

    public void warn(String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.WARN)) return;

      logger.log(Level.WARN, null, ParameterizedMessage.create(format, argArray));
    }

    public void warn(String msg, Throwable t)
    {
      if(!logger.isLoggingEnabled(Level.WARN)) return;

      logger.log(Level.WARN, null, new SimpleMessage(msg), t);
    }

    public void warn(Marker marker, String msg)
    {
      if(!logger.isLoggingEnabled(Level.WARN, marker)) return;

      logger.log(Level.WARN, marker, new SimpleMessage(msg), null);
    }

    public void warn(Marker marker, String format, Object arg)
    {
      if(!logger.isLoggingEnabled(Level.WARN, marker)) return;

      logger.log(Level.WARN, marker, ParameterizedMessage.create(format, arg));
    }

    public void warn(Marker marker, String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.WARN, marker)) return;

      logger.log(Level.WARN, marker, ParameterizedMessage.create(format, arg1, arg2));
    }

    public void warn(Marker marker, String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.WARN, marker)) return;

      logger.log(Level.WARN, marker, ParameterizedMessage.create(format, argArray));
    }

    public void warn(Marker marker, String msg, Throwable t)
    {
      if(!logger.isLoggingEnabled(Level.INFO, marker)) return;

      logger.log(Level.WARN, marker, new SimpleMessage(msg), t);
    }

    // ##### ERROR #####
    public boolean isErrorEnabled()
    {
      return logger.isLoggingEnabled(Level.ERROR, null);
    }

    public boolean isErrorEnabled(Marker marker)
    {
      return logger.isLoggingEnabled(Level.ERROR, marker);
    }

    public void error(String msg)
    {
      if(!logger.isLoggingEnabled(Level.ERROR)) return;

      logger.log(Level.ERROR, null, new SimpleMessage(msg), null);
    }

    public void error(String format, Object arg)
    {
      if(!logger.isLoggingEnabled(Level.ERROR)) return;

      logger.log(Level.ERROR, null, ParameterizedMessage.create(format, arg));
    }

    public void error(String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.ERROR)) return;

      logger.log(Level.ERROR, null, ParameterizedMessage.create(format, arg1, arg2));
    }

    public void error(String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.ERROR)) return;

      logger.log(Level.ERROR, null, ParameterizedMessage.create(format, argArray));
    }

    public void error(String msg, Throwable t)
    {
      if(!logger.isLoggingEnabled(Level.ERROR)) return;

      logger.log(Level.ERROR, null, new SimpleMessage(msg), t);
    }

    public void error(Marker marker, String msg)
    {
      if(!logger.isLoggingEnabled(Level.ERROR, marker)) return;

      logger.log(Level.ERROR, marker, new SimpleMessage(msg), null);
    }

    public void error(Marker marker, String format, Object arg)
    {
      if(!logger.isLoggingEnabled(Level.ERROR, marker)) return;

      logger.log(Level.ERROR, marker, ParameterizedMessage.create(format, arg));
    }

    public void error(Marker marker, String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.ERROR, marker)) return;

      logger.log(Level.ERROR, marker, ParameterizedMessage.create(format, arg1, arg2));
    }

    public void error(Marker marker, String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.ERROR, marker)) return;

      logger.log(Level.ERROR, marker, ParameterizedMessage.create(format, argArray));
    }

    public void error(Marker marker, String msg, Throwable t)
    {
      if(!logger.isLoggingEnabled(Level.ERROR, marker)) return;

      logger.log(Level.ERROR, marker, new SimpleMessage(msg), t);
    }

    private Object readResolve()
          throws ObjectStreamException
    {
      return org.slf4j.LoggerFactory.getLogger(loggerName);
    }

  // TODO: Implement and update above method calls, too!

  public void log(Marker marker, String fqcn, int level, String message, Throwable t)
  {
    if(locationAwareLogger == null)
    {
      switch(level)
      {
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
    else
    {
      switch(level)
      {
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
