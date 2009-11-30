package org.slf4j.n;

import org.slf4j.Marker;
import org.slf4j.core.BasicLogger;
import org.slf4j.core.Message;

import java.io.Serializable;

public interface Logger
    extends BasicLogger<Level>, Serializable {
  String ROOT_LOGGER_NAME = org.slf4j.Logger.ROOT_LOGGER_NAME;

  String getName();

  org.slf4j.Logger getOldLogger();

  /**
   * @return the Threshold of this Logger.
   */
  Threshold getThreshold();

  // generic logging methods
  /**
   * @param level  the Level
   * @param marker the Marker, may be null.
   * @return true, if logging at the given Level is enabled for the given Marker.
   */
  boolean isEnabled(Level level, Marker marker);

  void log(Level level, String messagePattern, Object... args);

  void log(Level level, Marker marker, String messagePattern, Object... args);

  void log(Level level, Message message);

  void log(Level level, Message message, Throwable throwable);

  void log(Level level, Marker marker, Message message);

  void log(Level level, Marker marker, Message message, Throwable throwable);

  // ##### TRACE #####

  /**
   * Shortcut for isEnabled(Level.TRACE).
   *
   * @return true, if logging at Level.TRACE is enabled.
   * @see BasicLogger#isEnabled(Enum)
   */
  boolean isTraceEnabled();

  /**
   * Shortcut for isLoggingEnabled(Level.TRACE, marker).
   *
   * @param marker the marker
   * @return true, if logging at Level.TRACE is enabled for the given Marker.
   * @see #isEnabled(Level, org.slf4j.Marker)
   */
  boolean isTraceEnabled(Marker marker);

  /**
   * Shortcut for log(Level.TRACE, messagePattern, args);
   *
   * @param messagePattern the message pattern
   * @param args           the arguments, if any
   * @see #log(Level, String, Object[])
   */
  void trace(String messagePattern, Object... args);

  /**
   * Shortcut for log(Level.TRACE, marker, messagePattern, args);
   *
   * @param marker         the marker
   * @param messagePattern the message pattern
   * @param args           the arguments, if any
   * @see #log(Level, Marker, String, Object[])
   */
  void trace(Marker marker, String messagePattern, Object... args);

  /**
   * Shortcut for log(Level.TRACE, message);
   *
   * @param message the message object
   * @see #log(Level, Message)
   */
  void trace(Message message);

  /**
   * Shortcut for log(Level.TRACE, message, Throwable);
   *
   * @param message   the message object
   * @param throwable the Throwable, if any
   * @see #log(Level, Message, Throwable)
   */
  void trace(Message message, Throwable throwable);

  /**
   * Shortcut for log(Level.TRACE, marker, message);
   *
   * @param marker  the marker
   * @param message the message object
   * @see #log(Level, Marker, Message)
   */
  void trace(Marker marker, Message message);

  /**
   * Shortcut for log(Level.TRACE, marker, message, Throwable);
   *
   * @param marker    the marker
   * @param message   the message object
   * @param throwable the Throwable, if any
   * @see #log(Level, Marker, Message, Throwable)
   */
  void trace(Marker marker, Message message, Throwable throwable);

  // ##### DEBUG #####

  /**
   * Shortcut for isEnabled(Level.DEBUG).
   *
   * @return true, if logging at Level.DEBUG is enabled.
   * @see BasicLogger#isEnabled(Enum)
   */
  boolean isDebugEnabled();

  /**
   * Shortcut for isLoggingEnabled(Level.DEBUG, marker).
   *
   * @param marker the marker
   * @return true, if logging at Level.DEBUG is enabled for the given Marker.
   * @see #isEnabled(Level, org.slf4j.Marker)
   */
  boolean isDebugEnabled(Marker marker);

  /**
   * Shortcut for log(Level.DEBUG, messagePattern, args);
   *
   * @param messagePattern the message pattern
   * @param args           the arguments, if any
   * @see #log(Level, String, Object[])
   */
  void debug(String messagePattern, Object... args);

  /**
   * Shortcut for log(Level.DEBUG, marker, messagePattern, args);
   *
   * @param marker         the marker
   * @param messagePattern the message pattern
   * @param args           the arguments, if any
   * @see #log(Level, Marker, String, Object[])
   */
  void debug(Marker marker, String messagePattern, Object... args);

  /**
   * Shortcut for log(Level.DEBUG, message);
   *
   * @param message the message object
   * @see #log(Level, Message)
   */
  void debug(Message message);

  /**
   * Shortcut for log(Level.DEBUG, message, Throwable);
   *
   * @param message   the message object
   * @param throwable the Throwable, if any
   * @see #log(Level, Message, Throwable)
   */
  void debug(Message message, Throwable throwable);

  /**
   * Shortcut for log(Level.DEBUG, marker, message);
   *
   * @param marker  the marker
   * @param message the message object
   * @see #log(Level, Marker, Message)
   */
  void debug(Marker marker, Message message);

  /**
   * Shortcut for log(Level.DEBUG, marker, message, Throwable);
   *
   * @param marker    the marker
   * @param message   the message object
   * @param throwable the Throwable, if any
   * @see #log(Level, Marker, Message, Throwable)
   */
  void debug(Marker marker, Message message, Throwable throwable);

  // ##### INFO #####

  /**
   * Shortcut for isEnabled(Level.INFO).
   *
   * @return true, if logging at Level.INFO is enabled.
   * @see BasicLogger#isEnabled(Enum)
   */
  boolean isInfoEnabled();

  /**
   * Shortcut for isLoggingEnabled(Level.INFO, marker).
   *
   * @param marker the marker
   * @return true, if logging at Level.DEBUG is enabled for the given Marker.
   * @see #isEnabled(Level, org.slf4j.Marker)
   */
  boolean isInfoEnabled(Marker marker);

  /**
   * Shortcut for log(Level.INFO, messagePattern, args);
   *
   * @param messagePattern the message pattern
   * @param args           the arguments, if any
   * @see #log(Level, String, Object[])
   */
  void info(String messagePattern, Object... args);

  /**
   * Shortcut for log(Level.INFO, marker, messagePattern, args);
   *
   * @param marker         the marker
   * @param messagePattern the message pattern
   * @param args           the arguments, if any
   * @see #log(Level, Marker, String, Object[])
   */
  void info(Marker marker, String messagePattern, Object... args);

  /**
   * Shortcut for log(Level.INFO, message);
   *
   * @param message the message object
   * @see #log(Level, Message)
   */
  void info(Message message);

  /**
   * Shortcut for log(Level.INFO, message, Throwable);
   *
   * @param message   the message object
   * @param throwable the Throwable, if any
   * @see #log(Level, Message, Throwable)
   */
  void info(Message message, Throwable throwable);

  /**
   * Shortcut for log(Level.INFO, marker, message);
   *
   * @param marker  the marker
   * @param message the message object
   * @see #log(Level, Marker, Message)
   */
  void info(Marker marker, Message message);

  /**
   * Shortcut for log(Level.INFO, marker, message, Throwable);
   *
   * @param marker    the marker
   * @param message   the message object
   * @param throwable the Throwable, if any
   * @see #log(Level, Marker, Message, Throwable)
   */
  void info(Marker marker, Message message, Throwable throwable);

  // ##### WARN #####

  /**
   * Shortcut for isEnabled(Level.WARN).
   *
   * @return true, if logging at Level.WARN is enabled.
   * @see BasicLogger#isEnabled(Enum)
   */
  boolean isWarnEnabled();

  /**
   * Shortcut for isLoggingEnabled(Level.WARN, marker).
   *
   * @param marker the marker
   * @return true, if logging at Level.DEBUG is enabled for the given Marker.
   * @see #isEnabled(Level, org.slf4j.Marker)
   */
  boolean isWarnEnabled(Marker marker);

  /**
   * Shortcut for log(Level.WARN, messagePattern, args);
   *
   * @param messagePattern the message pattern
   * @param args           the arguments, if any
   * @see #log(Level, String, Object[])
   */
  void warn(String messagePattern, Object... args);

  /**
   * Shortcut for log(Level.WARN, marker, messagePattern, args);
   *
   * @param marker         the marker
   * @param messagePattern the message pattern
   * @param args           the arguments, if any
   * @see #log(Level, Marker, String, Object[])
   */
  void warn(Marker marker, String messagePattern, Object... args);

  /**
   * Shortcut for log(Level.WARN, message);
   *
   * @param message the message object
   * @see #log(Level, Message)
   */
  void warn(Message message);

  /**
   * Shortcut for log(Level.WARN, message, Throwable);
   *
   * @param message   the message object
   * @param throwable the Throwable, if any
   * @see #log(Level, Message, Throwable)
   */
  void warn(Message message, Throwable throwable);

  /**
   * Shortcut for log(Level.WARN, marker, message);
   *
   * @param marker  the marker
   * @param message the message object
   * @see #log(Level, Marker, Message)
   */
  void warn(Marker marker, Message message);

  /**
   * Shortcut for log(Level.WARN, marker, message, Throwable);
   *
   * @param marker    the marker
   * @param message   the message object
   * @param throwable the Throwable, if any
   * @see #log(Level, Marker, Message, Throwable)
   */
  void warn(Marker marker, Message message, Throwable throwable);

  // ##### ERROR #####

  /**
   * Shortcut for isEnabled(Level.ERROR).
   *
   * @return true, if logging at Level.ERROR is enabled.
   * @see BasicLogger#isEnabled(Enum)
   */
  boolean isErrorEnabled();

  /**
   * Shortcut for isLoggingEnabled(Level.ERROR, marker).
   *
   * @param marker the marker
   * @return true, if logging at Level.DEBUG is enabled for the given Marker.
   * @see #isEnabled(Level, org.slf4j.Marker)
   */
  boolean isErrorEnabled(Marker marker);

  /**
   * Shortcut for log(Level.ERROR, messagePattern, args);
   *
   * @param messagePattern the message pattern
   * @param args           the arguments, if any
   * @see #log(Level, String, Object[])
   */
  void error(String messagePattern, Object... args);

  /**
   * Shortcut for log(Level.ERROR, marker, messagePattern, args);
   *
   * @param marker         the marker
   * @param messagePattern the message pattern
   * @param args           the arguments, if any
   * @see #log(Level, Marker, String, Object[])
   */
  void error(Marker marker, String messagePattern, Object... args);

  /**
   * Shortcut for log(Level.ERROR, message);
   *
   * @param message the message object
   * @see #log(Level, org.slf4j.core.Message)
   */
  void error(Message message);

  /**
   * Shortcut for log(Level.ERROR, message, Throwable);
   *
   * @param message   the message object
   * @param throwable the Throwable, if any
   * @see #log(Level, Message, Throwable)
   */
  void error(Message message, Throwable throwable);

  /**
   * Shortcut for log(Level.ERROR, marker, message);
   *
   * @param marker  the marker
   * @param message the message object
   * @see #log(Level, Marker, Message)
   */
  void error(Marker marker, Message message);

  /**
   * Shortcut for log(Level.ERROR, marker, message, Throwable);
   *
   * @param marker    the marker
   * @param message   the message object
   * @param throwable the Throwable, if any
   * @see #log(Level, Marker, Message, Throwable)
   */
  void error(Marker marker, Message message, Throwable throwable);

}
