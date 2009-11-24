package org.slf4j.n.helper;

import org.slf4j.n.Threshold;
import org.slf4j.n.Level;
import org.slf4j.n.Message;
import org.slf4j.n.LoggerFactory;
import org.slf4j.Marker;

import java.io.ObjectStreamException;
import java.io.InvalidClassException;


/**
 * This would be the base-class for JDK1.5-based bindings, i.e. Logback.
 */
@SuppressWarnings({"ThrowableResultOfMethodCallIgnored"})
public abstract class AbstractLogger
    implements org.slf4j.Logger, org.slf4j.n.Logger
{
  private static final long serialVersionUID = -8862257536998615351L;

  private String loggerName;

  protected AbstractLogger()
  {
    this(null);
  }

  public AbstractLogger(String loggerName)
  {
    setName(loggerName);
  }

  protected void setName(String loggerName)
  {
    this.loggerName = loggerName;
  }

  public String getName()
  {
    return loggerName;
  }

  public abstract Threshold getThreshold();


  // ##### generic #####

  public boolean isLoggingEnabled(Level level)
  {
    return getThreshold().passes(level);
  }

  /**
   * @param level the Level
   * @param marker the Marker, may be null.
   * @return true, if logging at the given Level is enabled for the given Marker.
   */
  public abstract boolean isLoggingEnabled(Level level, Marker marker);


  public void log(Level level, String messagePattern, Object... args)
  {
    if(!isLoggingEnabled(level)) return;
    ParameterizedMessage message = new ParameterizedMessage(messagePattern, args);
    log(level, null, message, message.getThrowable());
  }

  public void log(Level level, Marker marker, String messagePattern, Object... args)
  {
    if(!isLoggingEnabled(level, marker)) return;
    ParameterizedMessage message = new ParameterizedMessage(messagePattern, args);
    log(level, marker, message, message.getThrowable());
  }

  public void log(Level level, Message message)
  {
    if(!isLoggingEnabled(level)) return;
    if(message instanceof ParameterizedMessage)
    {
      log(level, null, message, ((ParameterizedMessage)message).getThrowable());
    }
    else
    {
      log(level, null, message, null);
    }
  }

  public void log(Level level, Message message, Throwable throwable)
  {
    if(!isLoggingEnabled(level)) return;
    log(level, null, message, throwable);
  }

  public void log(Level level, Marker marker, Message message)
  {
    if(!isLoggingEnabled(level)) return;
    if(message instanceof ParameterizedMessage)
    {
      log(level, marker, message, ((ParameterizedMessage)message).getThrowable());
    }
    else
    {
      log(level, marker, message, null);
    }
  }

  /**
   *
   * @param level the Level
   * @param marker the Marker, may be null
   * @param message the Message
   * @param throwable the Throwable, may be null
   */
  public abstract void log(Level level, Marker marker, Message message, Throwable throwable);

  // ##### TRACE #####
  public boolean isTraceEnabled()
  {
    return isLoggingEnabled(Level.TRACE, null);
  }

  public boolean isTraceEnabled(Marker marker)
  {
    return isLoggingEnabled(Level.TRACE, marker);
  }

  public void trace(String msg)
  {
    if(!isLoggingEnabled(Level.TRACE)) return;

    log(Level.TRACE, null, new SimpleMessage(msg), null);
  }

  public void trace(String format, Object arg)
  {
    if(!isLoggingEnabled(Level.TRACE)) return;

    log(Level.TRACE, null, new ParameterizedMessage(format, arg), null);
  }

  public void trace(String format, Object arg1, Object arg2)
  {
    if(!isLoggingEnabled(Level.TRACE)) return;

    log(Level.TRACE, null, new ParameterizedMessage(format, arg1, arg2), null);
  }

  public void trace(String format, Object[] argArray)
  {
    if(!isLoggingEnabled(Level.TRACE)) return;

    log(Level.TRACE, null, new ParameterizedMessage(format, argArray), null);
  }

  public void trace(String msg, Throwable t)
  {
    if(!isLoggingEnabled(Level.TRACE)) return;

    log(Level.TRACE, null, new SimpleMessage(msg), t);
  }

  public void trace(Marker marker, String msg)
  {
    if(!isLoggingEnabled(Level.TRACE, marker)) return;

    log(Level.TRACE, marker, new SimpleMessage(msg), null);
  }

  public void trace(Marker marker, String format, Object arg)
  {
    if(!isLoggingEnabled(Level.TRACE, marker)) return;

    log(Level.TRACE, marker, new ParameterizedMessage(format, arg), null);
  }

  public void trace(Marker marker, String format, Object arg1, Object arg2)
  {
    if(!isLoggingEnabled(Level.TRACE, marker)) return;

    log(Level.TRACE, marker, new ParameterizedMessage(format, arg1, arg2), null);
  }

  public void trace(Marker marker, String format, Object[] argArray)
  {
    if(!isLoggingEnabled(Level.TRACE, marker)) return;

    log(Level.TRACE, marker, new ParameterizedMessage(format, argArray), null);
  }

  public void trace(Message message)
  {
    log(Level.TRACE, null, message);
  }

  public void trace(Message message, Throwable throwable)
  {
    log(Level.TRACE, null, message, throwable);
  }

  public void trace(Marker marker, String msg, Throwable t)
  {
    if(!isLoggingEnabled(Level.TRACE, marker)) return;

    log(Level.TRACE, marker, new SimpleMessage(msg), t);
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
    return isLoggingEnabled(Level.DEBUG, null);
  }

  public boolean isDebugEnabled(Marker marker)
  {
    return isLoggingEnabled(Level.DEBUG, marker);
  }

  public void debug(String msg)
  {
    if(!isLoggingEnabled(Level.DEBUG)) return;

    log(Level.DEBUG, null, new SimpleMessage(msg), null);
  }

  public void debug(String format, Object arg)
  {
    if(!isLoggingEnabled(Level.DEBUG)) return;

    log(Level.DEBUG, null, new ParameterizedMessage(format, arg), null);
  }

  public void debug(String format, Object arg1, Object arg2)
  {
    if(!isLoggingEnabled(Level.DEBUG)) return;

    log(Level.DEBUG, null, new ParameterizedMessage(format, arg1, arg2), null);
  }

  public void debug(String format, Object[] argArray)
  {
    if(!isLoggingEnabled(Level.DEBUG)) return;

    log(Level.DEBUG, null, new ParameterizedMessage(format, argArray), null);
  }

  public void debug(String msg, Throwable t)
  {
    if(!isLoggingEnabled(Level.DEBUG)) return;

    log(Level.DEBUG, null, new SimpleMessage(msg), t);
  }

  public void debug(Marker marker, String msg)
  {
    if(!isLoggingEnabled(Level.DEBUG, marker)) return;

    log(Level.DEBUG, marker, new SimpleMessage(msg), null);
  }

  public void debug(Marker marker, String format, Object arg)
  {
    if(!isLoggingEnabled(Level.DEBUG, marker)) return;

    log(Level.DEBUG, marker, new ParameterizedMessage(format, arg), null);
  }

  public void debug(Marker marker, String format, Object arg1, Object arg2)
  {
    if(!isLoggingEnabled(Level.DEBUG, marker)) return;

    log(Level.DEBUG, marker, new ParameterizedMessage(format, arg1, arg2), null);
  }

  public void debug(Marker marker, String format, Object[] argArray)
  {
    if(!isLoggingEnabled(Level.DEBUG, marker)) return;

    log(Level.DEBUG, marker, new ParameterizedMessage(format, argArray), null);
  }

  public void debug(Message message)
  {
    log(Level.DEBUG, null, message);
  }

  public void debug(Message message, Throwable throwable)
  {
    log(Level.DEBUG, null, message, throwable);
  }

  public void debug(Marker marker, String msg, Throwable t)
  {
    if(!isLoggingEnabled(Level.DEBUG, marker)) return;

    log(Level.DEBUG, marker, new SimpleMessage(msg), t);
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
    return isLoggingEnabled(Level.INFO, null);
  }

  public boolean isInfoEnabled(Marker marker)
  {
    return isLoggingEnabled(Level.INFO, marker);
  }

  public void info(String msg)
  {
    if(!isLoggingEnabled(Level.INFO)) return;

    log(Level.INFO, null, new SimpleMessage(msg), null);
  }

  public void info(String format, Object arg)
  {
    if(!isLoggingEnabled(Level.INFO)) return;

    log(Level.INFO, null, new ParameterizedMessage(format, arg), null);
  }

  public void info(String format, Object arg1, Object arg2)
  {
    if(!isLoggingEnabled(Level.INFO)) return;

    log(Level.INFO, null, new ParameterizedMessage(format, arg1, arg2), null);
  }

  public void info(String format, Object[] argArray)
  {
    if(!isLoggingEnabled(Level.INFO)) return;

    log(Level.INFO, null, new ParameterizedMessage(format, argArray), null);
  }

  public void info(String msg, Throwable t)
  {
    if(!isLoggingEnabled(Level.INFO)) return;

    log(Level.INFO, null, new SimpleMessage(msg), t);
  }

  public void info(Marker marker, String msg)
  {
    if(!isLoggingEnabled(Level.INFO, marker)) return;

    log(Level.INFO, marker, new SimpleMessage(msg), null);
  }

  public void info(Marker marker, String format, Object arg)
  {
    if(!isLoggingEnabled(Level.INFO, marker)) return;

    log(Level.INFO, marker, new ParameterizedMessage(format, arg), null);
  }

  public void info(Marker marker, String format, Object arg1, Object arg2)
  {
    if(!isLoggingEnabled(Level.INFO, marker)) return;

    log(Level.INFO, marker, new ParameterizedMessage(format, arg1, arg2), null);
  }

  public void info(Marker marker, String format, Object[] argArray)
  {
    if(!isLoggingEnabled(Level.INFO, marker)) return;

    log(Level.INFO, marker, new ParameterizedMessage(format, argArray), null);
  }

  public void info(Message message)
  {
    log(Level.INFO, null, message);
  }

  public void info(Message message, Throwable throwable)
  {
    log(Level.INFO, null, message, throwable);
  }

  public void info(Marker marker, String msg, Throwable t)
  {
    if(!isLoggingEnabled(Level.INFO, marker)) return;

    log(Level.INFO, marker, new SimpleMessage(msg), t);
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
    return isLoggingEnabled(Level.WARN, null);
  }

  public boolean isWarnEnabled(Marker marker)
  {
    return isLoggingEnabled(Level.WARN, marker);
  }

  public void warn(String msg)
  {
    if(!isLoggingEnabled(Level.WARN)) return;

    log(Level.WARN, null, new SimpleMessage(msg), null);
  }

  public void warn(String format, Object arg)
  {
    if(!isLoggingEnabled(Level.WARN)) return;

    log(Level.WARN, null, new ParameterizedMessage(format, arg), null);
  }

  public void warn(String format, Object arg1, Object arg2)
  {
    if(!isLoggingEnabled(Level.WARN)) return;

    log(Level.WARN, null, new ParameterizedMessage(format, arg1, arg2), null);
  }

  public void warn(String format, Object[] argArray)
  {
    if(!isLoggingEnabled(Level.WARN)) return;

    log(Level.WARN, null, new ParameterizedMessage(format, argArray), null);
  }

  public void warn(String msg, Throwable t)
  {
    if(!isLoggingEnabled(Level.WARN)) return;

    log(Level.WARN, null, new SimpleMessage(msg), t);
  }

  public void warn(Marker marker, String msg)
  {
    if(!isLoggingEnabled(Level.WARN, marker)) return;

    log(Level.WARN, marker, new SimpleMessage(msg), null);
  }

  public void warn(Marker marker, String format, Object arg)
  {
    if(!isLoggingEnabled(Level.WARN, marker)) return;

    log(Level.WARN, marker, new ParameterizedMessage(format, arg), null);
  }

  public void warn(Marker marker, String format, Object arg1, Object arg2)
  {
    if(!isLoggingEnabled(Level.WARN, marker)) return;

    log(Level.WARN, marker, new ParameterizedMessage(format, arg1, arg2), null);
  }

  public void warn(Marker marker, String format, Object[] argArray)
  {
    if(!isLoggingEnabled(Level.WARN, marker)) return;

    log(Level.WARN, marker, new ParameterizedMessage(format, argArray), null);
  }

  public void warn(Message message)
  {
    log(Level.WARN, null, message);
  }

  public void warn(Message message, Throwable throwable)
  {
    log(Level.WARN, null, message, throwable);
  }

  public void warn(Marker marker, String msg, Throwable t)
  {
    if(!isLoggingEnabled(Level.INFO, marker)) return;

    log(Level.WARN, marker, new SimpleMessage(msg), t);
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
    return isLoggingEnabled(Level.ERROR, null);
  }

  public boolean isErrorEnabled(Marker marker)
  {
    return isLoggingEnabled(Level.ERROR, marker);
  }

  public void error(String msg)
  {
    if(!isLoggingEnabled(Level.ERROR)) return;

    log(Level.ERROR, null, new SimpleMessage(msg), null);
  }

  public void error(String format, Object arg)
  {
    if(!isLoggingEnabled(Level.ERROR)) return;

    log(Level.ERROR, null, new ParameterizedMessage(format, arg), null);
  }

  public void error(String format, Object arg1, Object arg2)
  {
    if(!isLoggingEnabled(Level.ERROR)) return;

    log(Level.ERROR, null, new ParameterizedMessage(format, arg1, arg2), null);
  }

  public void error(String format, Object[] argArray)
  {
    if(!isLoggingEnabled(Level.ERROR)) return;

    log(Level.ERROR, null, new ParameterizedMessage(format, argArray), null);
  }

  public void error(String msg, Throwable t)
  {
    if(!isLoggingEnabled(Level.ERROR)) return;

    log(Level.ERROR, null, new SimpleMessage(msg), t);
  }

  public void error(Marker marker, String msg)
  {
    if(!isLoggingEnabled(Level.ERROR, marker)) return;

    log(Level.ERROR, marker, new SimpleMessage(msg), null);
  }

  public void error(Marker marker, String format, Object arg)
  {
    if(!isLoggingEnabled(Level.ERROR, marker)) return;

    log(Level.ERROR, marker, new ParameterizedMessage(format, arg), null);
  }

  public void error(Marker marker, String format, Object arg1, Object arg2)
  {
    if(!isLoggingEnabled(Level.ERROR, marker)) return;

    log(Level.ERROR, marker, new ParameterizedMessage(format, arg1, arg2), null);
  }

  public void error(Marker marker, String format, Object[] argArray)
  {
    if(!isLoggingEnabled(Level.ERROR, marker)) return;

    log(Level.ERROR, marker, new ParameterizedMessage(format, argArray), null);
  }

  public void error(Message message)
  {
    log(Level.ERROR, null, message);
  }

  public void error(Message message, Throwable throwable)
  {
    log(Level.ERROR, null, message, throwable);
  }

  public void error(Marker marker, String msg, Throwable t)
  {
    if(!isLoggingEnabled(Level.ERROR, marker)) return;

    log(Level.ERROR, marker, new SimpleMessage(msg), t);
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
    // I'm not 100% sure if this will work out.
    // It is supposed to handle serialization in the abstract base class already.
    Object result = LoggerFactory.getLogger(loggerName);
    if(result instanceof AbstractLogger)
    {
      return result;
    }
    throw new InvalidClassException(result.getClass().getName(), "LoggerFactory did not return an AbstractLogger!");
  }

}
