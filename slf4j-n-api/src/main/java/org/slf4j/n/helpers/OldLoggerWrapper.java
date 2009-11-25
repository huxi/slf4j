package org.slf4j.n.helpers;

import org.slf4j.n.Logger;
import org.slf4j.n.Level;
import org.slf4j.Marker;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class OldLoggerWrapper
  implements org.slf4j.Logger, Serializable
{
    private String loggerName;
    private transient Logger logger;

    // just for deserialization
    protected OldLoggerWrapper()
    {
    }

    public OldLoggerWrapper(Logger logger)
    {
      this.loggerName = logger.getName();
      this.logger = logger;
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

      logger.log(Level.TRACE, null, new ParameterizedMessage(format, arg), null);
    }

    public void trace(String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.TRACE)) return;

      logger.log(Level.TRACE, null, new ParameterizedMessage(format, arg1, arg2), null);
    }

    public void trace(String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.TRACE)) return;

      logger.log(Level.TRACE, null, new ParameterizedMessage(format, argArray), null);
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

      logger.log(Level.TRACE, marker, new ParameterizedMessage(format, arg), null);
    }

    public void trace(Marker marker, String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.TRACE, marker)) return;

      logger.log(Level.TRACE, marker, new ParameterizedMessage(format, arg1, arg2), null);
    }

    public void trace(Marker marker, String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.TRACE, marker)) return;

      logger.log(Level.TRACE, marker, new ParameterizedMessage(format, argArray), null);
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

      logger.log(Level.DEBUG, null, new ParameterizedMessage(format, arg), null);
    }

    public void debug(String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.DEBUG)) return;

      logger.log(Level.DEBUG, null, new ParameterizedMessage(format, arg1, arg2), null);
    }

    public void debug(String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.DEBUG)) return;

      logger.log(Level.DEBUG, null, new ParameterizedMessage(format, argArray), null);
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

      logger.log(Level.DEBUG, marker, new ParameterizedMessage(format, arg), null);
    }

    public void debug(Marker marker, String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.DEBUG, marker)) return;

      logger.log(Level.DEBUG, marker, new ParameterizedMessage(format, arg1, arg2), null);
    }

    public void debug(Marker marker, String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.DEBUG, marker)) return;

      logger.log(Level.DEBUG, marker, new ParameterizedMessage(format, argArray), null);
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

      logger.log(Level.INFO, null, new ParameterizedMessage(format, arg), null);
    }

    public void info(String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.INFO)) return;

      logger.log(Level.INFO, null, new ParameterizedMessage(format, arg1, arg2), null);
    }

    public void info(String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.INFO)) return;

      logger.log(Level.INFO, null, new ParameterizedMessage(format, argArray), null);
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

      logger.log(Level.INFO, marker, new ParameterizedMessage(format, arg), null);
    }

    public void info(Marker marker, String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.INFO, marker)) return;

      logger.log(Level.INFO, marker, new ParameterizedMessage(format, arg1, arg2), null);
    }

    public void info(Marker marker, String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.INFO, marker)) return;

      logger.log(Level.INFO, marker, new ParameterizedMessage(format, argArray), null);
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

      logger.log(Level.WARN, null, new ParameterizedMessage(format, arg), null);
    }

    public void warn(String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.WARN)) return;

      logger.log(Level.WARN, null, new ParameterizedMessage(format, arg1, arg2), null);
    }

    public void warn(String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.WARN)) return;

      logger.log(Level.WARN, null, new ParameterizedMessage(format, argArray), null);
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

      logger.log(Level.WARN, marker, new ParameterizedMessage(format, arg), null);
    }

    public void warn(Marker marker, String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.WARN, marker)) return;

      logger.log(Level.WARN, marker, new ParameterizedMessage(format, arg1, arg2), null);
    }

    public void warn(Marker marker, String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.WARN, marker)) return;

      logger.log(Level.WARN, marker, new ParameterizedMessage(format, argArray), null);
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

      logger.log(Level.ERROR, null, new ParameterizedMessage(format, arg), null);
    }

    public void error(String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.ERROR)) return;

      logger.log(Level.ERROR, null, new ParameterizedMessage(format, arg1, arg2), null);
    }

    public void error(String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.ERROR)) return;

      logger.log(Level.ERROR, null, new ParameterizedMessage(format, argArray), null);
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

      logger.log(Level.ERROR, marker, new ParameterizedMessage(format, arg), null);
    }

    public void error(Marker marker, String format, Object arg1, Object arg2)
    {
      if(!logger.isLoggingEnabled(Level.ERROR, marker)) return;

      logger.log(Level.ERROR, marker, new ParameterizedMessage(format, arg1, arg2), null);
    }

    public void error(Marker marker, String format, Object[] argArray)
    {
      if(!logger.isLoggingEnabled(Level.ERROR, marker)) return;

      logger.log(Level.ERROR, marker, new ParameterizedMessage(format, argArray), null);
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

  }
