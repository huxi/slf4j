package org.slf4j.n.helpers;

import org.slf4j.n.Threshold;
import org.slf4j.n.Level;
import org.slf4j.core.Message;
import org.slf4j.n.LoggerFactory;
import org.slf4j.n.messages.ParameterizedMessage;
import org.slf4j.Marker;
import org.slf4j.Logger;

import java.io.ObjectStreamException;


/**
 * This would be the base-class for JDK1.5-based bindings, i.e. Logback.
 *
 * Extending classes should additionally implement org.slf4j.n.spi.LocationAwareLogger
 * if appropriate.
 *
 * @author J&ouml;rn Huxhorn
 */
@SuppressWarnings({"ThrowableResultOfMethodCallIgnored"})
public abstract class AbstractLogger
    implements org.slf4j.n.Logger
{
  private static final long serialVersionUID = -8862257536998615351L;

  protected transient org.slf4j.Logger oldLogger;

  // In extending class: private static final String FQCN = ExtendingClass.class.getName();

  private String loggerName;

  // just for deserialization
  protected AbstractLogger()
  {
  }

  public AbstractLogger(String loggerName)
  {
    this.loggerName=loggerName;
  }

  public String getName()
  {
    return loggerName;
  }

  public abstract Threshold getThreshold();

  public Logger getOldLogger()
  {
    if(oldLogger == null)
    {
      oldLogger = new OldLoggerWrappingNew(this);
    }
    return oldLogger;
  }

// ##### generic #####

  public boolean isEnabled(Level level)
  {
    return getThreshold().passes(level);
  }

  /**
   * @param level the Level
   * @param marker the Marker, may be null.
   * @return true, if logging at the given Level is enabled for the given Marker.
   */
  public abstract boolean isEnabled(Level level, Marker marker);


  public void log(Level level, String messagePattern, Object... args)
  {
    if(!isEnabled(level)) return;
    ParameterizedMessage message = ParameterizedMessage.create(messagePattern, args);
    log(level, null, message, message.getThrowable());
  }

  public void log(Level level, Marker marker, String messagePattern, Object... args)
  {
    if(!isEnabled(level, marker)) return;
    ParameterizedMessage message = ParameterizedMessage.create(messagePattern, args);
    log(level, marker, message, message.getThrowable());
  }

  public void log(Level level, Message message)
  {
    if(!isEnabled(level)) return;
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
    if(!isEnabled(level)) return;
    log(level, null, message, throwable);
  }

  public void log(Level level, Marker marker, Message message)
  {
    if(!isEnabled(level)) return;
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
  // in extending class:
  // {
  //   log(FQCN, level, marker, message, throwable);
  // }

  //public abstract void log(String fqcn, Level level, Marker marker, Message message, Throwable throwable);
  // In extending class:
  // {
  //   [Actual logging implementation.]
  // }


  // ##### TRACE #####
  public boolean isTraceEnabled()
  {
    return isEnabled(Level.TRACE, null);
  }

  public boolean isTraceEnabled(Marker marker)
  {
    return isEnabled(Level.TRACE, marker);
  }

  public void trace(String format, Object[] argArray)
  {
    if(!isEnabled(Level.TRACE)) return;

    log(Level.TRACE, null, ParameterizedMessage.create(format, argArray));
  }

  public void trace(Marker marker, String format, Object[] argArray)
  {
    if(!isEnabled(Level.TRACE, marker)) return;

    log(Level.TRACE, marker, ParameterizedMessage.create(format, argArray));
  }

  public void trace(Message message)
  {
    log(Level.TRACE, null, message);
  }

  public void trace(Message message, Throwable throwable)
  {
    log(Level.TRACE, null, message, throwable);
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
    return isEnabled(Level.DEBUG, null);
  }

  public boolean isDebugEnabled(Marker marker)
  {
    return isEnabled(Level.DEBUG, marker);
  }

  public void debug(String format, Object[] argArray)
  {
    if(!isEnabled(Level.DEBUG)) return;

    log(Level.DEBUG, null, ParameterizedMessage.create(format, argArray));
  }

  public void debug(Marker marker, String format, Object[] argArray)
  {
    if(!isEnabled(Level.DEBUG, marker)) return;

    log(Level.DEBUG, marker, ParameterizedMessage.create(format, argArray));
  }

  public void debug(Message message)
  {
    log(Level.DEBUG, null, message);
  }

  public void debug(Message message, Throwable throwable)
  {
    log(Level.DEBUG, null, message, throwable);
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
    return isEnabled(Level.INFO, null);
  }

  public boolean isInfoEnabled(Marker marker)
  {
    return isEnabled(Level.INFO, marker);
  }

  public void info(String format, Object[] argArray)
  {
    if(!isEnabled(Level.INFO)) return;

    log(Level.INFO, null, ParameterizedMessage.create(format, argArray));
  }

  public void info(Marker marker, String format, Object[] argArray)
  {
    if(!isEnabled(Level.INFO, marker)) return;

    log(Level.INFO, marker, ParameterizedMessage.create(format, argArray));
  }

  public void info(Message message)
  {
    log(Level.INFO, null, message);
  }

  public void info(Message message, Throwable throwable)
  {
    log(Level.INFO, null, message, throwable);
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
    return isEnabled(Level.WARN, null);
  }

  public boolean isWarnEnabled(Marker marker)
  {
    return isEnabled(Level.WARN, marker);
  }

  public void warn(String format, Object[] argArray)
  {
    if(!isEnabled(Level.WARN)) return;

    log(Level.WARN, null, ParameterizedMessage.create(format, argArray));
  }

  public void warn(Marker marker, String format, Object[] argArray)
  {
    if(!isEnabled(Level.WARN, marker)) return;

    log(Level.WARN, marker, ParameterizedMessage.create(format, argArray));
  }

  public void warn(Message message)
  {
    log(Level.WARN, null, message);
  }

  public void warn(Message message, Throwable throwable)
  {
    log(Level.WARN, null, message, throwable);
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
    return isEnabled(Level.ERROR, null);
  }

  public boolean isErrorEnabled(Marker marker)
  {
    return isEnabled(Level.ERROR, marker);
  }

  public void error(String format, Object[] argArray)
  {
    if(!isEnabled(Level.ERROR)) return;

    log(Level.ERROR, null, ParameterizedMessage.create(format, argArray));
  }

  public void error(Marker marker, String format, Object[] argArray)
  {
    if(!isEnabled(Level.ERROR, marker)) return;

    log(Level.ERROR, marker, ParameterizedMessage.create(format, argArray));
  }

  public void error(Message message)
  {
    log(Level.ERROR, null, message);
  }

  public void error(Message message, Throwable throwable)
  {
    log(Level.ERROR, null, message, throwable);
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

}
