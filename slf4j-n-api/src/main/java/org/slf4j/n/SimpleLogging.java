package org.slf4j.n;

import org.slf4j.Marker;
import org.slf4j.core.Message;
import org.slf4j.n.helpers.NOPLogger;

/**
 * This class might be a very bad idea...
 *
 * It's basically the result of the discussion at http://bugzilla.slf4j.org/show_bug.cgi?id=163.
 *
 * As Ceki pointed out correctly at http://bugzilla.slf4j.org/show_bug.cgi?id=163#c2 there is no guarantee
 * that Throwable.getStackTrace() returns a non-empty array.
 *
 * No method in this class will do anything in that case. isEnabled() will always return false and log methods will act
 * like no-ops.
 *
 * The question are:
 * <ul>
 * <li>Is this behavior acceptable? Which JVMs are not returning a call stack?
 * Is it possible to enable/disable stacktraces in those JVMs?
 * Is it ok to simply accept that logging won't be available in case of no stacktrace?</li>
 * <li>How bad is the performance impact of "new Throwable().getStackTrace()"?</li>
 * <li>Would this be a worthwhile addition anyway? Would it be enough to educate the user about the restrictions above?</li>
 * </ul>
 *
 * @see Throwable#getStackTrace()
 */
public class SimpleLogging {

  private static final int CALLSTACK_OFFSET=3;

  /**
   * Returns either the correct Logger for the calling class or a NOPLogger if Throwable.getStackTrace()
   * doesn't return anything.
   * @return the Logger for the calling class or a NOPLogger if it can't be resolved.
   * @see Throwable#getStackTrace()
   */
  public static Logger getCallingLogger()
  {
    Logger result=resolveLogger(CALLSTACK_OFFSET);
    if(result == null)
    {
      result=NOPLogger.NOP_LOGGER;
    }
    return result;
  }

  public static boolean isEnabled(Level level, Marker marker)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    return logger != null && logger.isEnabled(level, marker);
  }

  public static boolean isEnabled(Level level)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    return logger != null && logger.isEnabled(level);
  }


  public static boolean isTraceEnabled(Marker marker)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    return logger != null && logger.isTraceEnabled(marker);
  }

  public static boolean isTraceEnabled()
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    return logger != null && logger.isTraceEnabled();
  }

  public static boolean isDebugEnabled(Marker marker)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    return logger != null && logger.isDebugEnabled(marker);
  }

  public static boolean isDebugEnabled()
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    return logger != null && logger.isDebugEnabled();
  }

  public static boolean isInfoEnabled(Marker marker)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    return logger != null && logger.isInfoEnabled(marker);
  }

  public static boolean isInfoEnabled()
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    return logger != null && logger.isInfoEnabled();
  }

  public static boolean isWarnEnabled(Marker marker)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    return logger != null && logger.isWarnEnabled(marker);
  }

  public static boolean isWarnEnabled()
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    return logger != null && logger.isWarnEnabled();
  }

  public static boolean isErrorEnabled(Marker marker)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    return logger != null && logger.isErrorEnabled(marker);
  }

  public static boolean isErrorEnabled()
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    return logger != null && logger.isErrorEnabled();
  }

  // LOG
  public static void log(Level level, String messagePattern, Object... args)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.log(level, messagePattern, args);
  }

  public static void log(Level level, Marker marker, String messagePattern, Object... args)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.log(level, marker, messagePattern, args);
  }

  public static void log(Level level, Message message)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.log(level, message);
  }

  public static void log(Level level, Message message, Throwable throwable)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.log(level, message, throwable);
  }

  public static void log(Level level, Marker marker, Message message)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.log(level, marker, message);
  }

  public static void log(Level level, Marker marker, Message message, Throwable throwable)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.log(level, marker, message, throwable);
  }


  // TRACE
  public static void trace(String messagePattern, Object... args)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.trace(messagePattern, args);
  }

  public static void trace(Marker marker, String messagePattern, Object... args)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.trace(marker, messagePattern, args);
  }

  public static void trace(Message message)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.trace(message);
  }

  public static void trace(Message message, Throwable throwable)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.trace(message, throwable);
  }

  public static void trace(Marker marker, Message message)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.trace(marker, message);
  }
  
  public static void trace(Marker marker, Message message, Throwable throwable)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.trace(marker, message, throwable);
  }

  // DEBUG
  public static void debug(String messagePattern, Object... args)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.debug(messagePattern, args);
  }

  public static void debug(Marker marker, String messagePattern, Object... args)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.debug(marker, messagePattern, args);
  }

  public static void debug(Message message)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.debug(message);
  }

  public static void debug(Message message, Throwable throwable)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.debug(message, throwable);
  }

  public static void debug(Marker marker, Message message)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.debug(marker, message);
  }

  public static void debug(Marker marker, Message message, Throwable throwable)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.debug(marker, message, throwable);
  }

  // INFO
  public static void info(String messagePattern, Object... args)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.info(messagePattern, args);
  }

  public static void info(Marker marker, String messagePattern, Object... args)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.info(marker, messagePattern, args);
  }

  public static void info(Message message)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.info(message);
  }

  public static void info(Message message, Throwable throwable)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.info(message, throwable);
  }

  public static void info(Marker marker, Message message)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.info(marker, message);
  }

  public static void info(Marker marker, Message message, Throwable throwable)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.info(marker, message, throwable);
  }

  // WARN
  public static void warn(String messagePattern, Object... args)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.warn(messagePattern, args);
  }

  public static void warn(Marker marker, String messagePattern, Object... args)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.warn(marker, messagePattern, args);
  }

  public static void warn(Message message)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.warn(message);
  }

  public static void warn(Message message, Throwable throwable)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.warn(message, throwable);
  }

  public static void warn(Marker marker, Message message)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.warn(marker, message);
  }

  public static void warn(Marker marker, Message message, Throwable throwable)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.warn(marker, message, throwable);
  }


  // ERROR
  public static void error(String messagePattern, Object... args)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.error(messagePattern, args);
  }

  public static void error(Marker marker, String messagePattern, Object... args)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.error(marker, messagePattern, args);
  }

  public static void error(Message message)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.error(message);
  }

  public static void error(Message message, Throwable throwable)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.error(message, throwable);
  }

  public static void error(Marker marker, Message message)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.error(marker, message);
  }

  public static void error(Marker marker, Message message, Throwable throwable)
  {
    Logger logger=resolveLogger(CALLSTACK_OFFSET);
    if(logger == null)
    {
      return;
    }
    logger.error(marker, message, throwable);
  }

  private static Logger resolveLogger(int index)
  {
    String className=resolveCallerClass(index);
    //System.out.println("Logger for "+className);
    if(className == null)
    {
      return null;
    }
    return LoggerFactory.getLogger(className);
  }

  static String resolveCallerClass(int index)
  {
    @SuppressWarnings({"ThrowableInstanceNeverThrown"})
    StackTraceElement[] ste = new Throwable().getStackTrace();
    //for(StackTraceElement current:ste)
    //{
    //  System.out.println(current);
    //}
    if(index < ste.length && index >=0)
    {
      return ste[index].getClassName();
    }
    return null;
  }

}
