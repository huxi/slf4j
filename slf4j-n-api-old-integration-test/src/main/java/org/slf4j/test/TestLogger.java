package org.slf4j.test;

import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.helpers.NamedLoggerBase;
import org.slf4j.n.Level;
import org.slf4j.n.Threshold;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Logger implementation for testing.
 */
public class TestLogger
    extends NamedLoggerBase {
  private static final long serialVersionUID = 4339490930642966550L;

  private final List<PrimitiveEvent> events = new ArrayList<PrimitiveEvent>();
  private Threshold threshold = Threshold.ALL;

  public TestLogger(String loggerName) {
    name = loggerName;
  }

  public Threshold getThreshold() {
    return threshold;
  }

  public void setThreshold(Threshold threshold) {
    this.threshold = threshold;
  }

  public boolean isEnabled(Level level, Marker marker) {
    return isEnabled(level);
  }

  private boolean isEnabled(Level level) {
    return this.threshold.passes(level);
  }

  public void log(Level level, Marker marker, String message, Throwable throwable) {
    events.add(new PrimitiveEvent(level, marker, message, throwable));
  }


  public List<PrimitiveEvent> getEvents() {
    return Collections.unmodifiableList(events);
  }

  /**
   * Clears the event list.
   */
  public void clear() {
    events.clear();
  }

  public static class PrimitiveEvent {
    private Level level;
    private Marker marker;
    private String messageString;
    private Throwable throwable;

    public PrimitiveEvent() {
    }

    public PrimitiveEvent(Level level, Marker marker, String messageString, Throwable throwable) {
      this.level = level;
      this.marker = marker;
      this.messageString = messageString;
      this.throwable = throwable;
    }

    public Level getLevel() {
      return level;
    }

    public void setLevel(Level level) {
      this.level = level;
    }

    public Marker getMarker() {
      return marker;
    }

    public void setMarker(Marker marker) {
      this.marker = marker;
    }

    public Throwable getThrowable() {
      return throwable;
    }

    public void setThrowable(Throwable throwable) {
      this.throwable = throwable;
    }

    public String toString() {
      return "PrimitiveEvent[level=" + level + ", marker=" + marker + ", messageString=" + messageString + ", throwable=" + throwable + "]";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      PrimitiveEvent that = (PrimitiveEvent) o;

      if (level != that.level) return false;
      if (marker != null ? !marker.equals(that.marker) : that.marker != null) return false;
      if (messageString != null ? !messageString.equals(that.messageString) : that.messageString != null) return false;
      if (throwable != null ? !throwable.equals(that.throwable) : that.throwable != null) return false;

      return true;
    }

    @Override
    public int hashCode() {
      int result = level != null ? level.hashCode() : 0;
      result = 31 * result + (marker != null ? marker.hashCode() : 0);
      result = 31 * result + (messageString != null ? messageString.hashCode() : 0);
      result = 31 * result + (throwable != null ? throwable.hashCode() : 0);
      return result;
    }
  }


  // Oh dear...

  public boolean isTraceEnabled() {
    return isEnabled(Level.TRACE);
  }

  public boolean isTraceEnabled(Marker marker) {
    return isEnabled(Level.TRACE, marker);
  }

  public boolean isDebugEnabled() {
    return isEnabled(Level.DEBUG);
  }

  public boolean isDebugEnabled(Marker marker) {
    return isEnabled(Level.DEBUG, marker);
  }

  public boolean isInfoEnabled() {
    return isEnabled(Level.INFO);
  }

  public boolean isInfoEnabled(Marker marker) {
    return isEnabled(Level.INFO, marker);
  }

  public boolean isWarnEnabled() {
    return isEnabled(Level.WARN);
  }

  public boolean isWarnEnabled(Marker marker) {
    return isEnabled(Level.WARN, marker);
  }

  public boolean isErrorEnabled() {
    return isEnabled(Level.ERROR);
  }

  public boolean isErrorEnabled(Marker marker) {
    return isEnabled(Level.ERROR, marker);
  }

  public void trace(String msg) {
    final Level level = Level.TRACE;
    log(level, null, msg, null);
  }

  public void trace(String format, Object arg) {
    final Level level = Level.TRACE;
    FormattingTuple tuple = MessageFormatter.format(format, arg);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void trace(String format, Object arg1, Object arg2) {
    final Level level = Level.TRACE;
    FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void trace(String format, Object[] argArray) {
    final Level level = Level.TRACE;
    FormattingTuple tuple = MessageFormatter.format(format, argArray);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void trace(String msg, Throwable t) {
    final Level level = Level.TRACE;
    log(level, null, msg, t);
  }

  public void trace(Marker marker, String msg) {
    final Level level = Level.TRACE;
    log(level, marker, msg, null);
  }

  public void trace(Marker marker, String format, Object arg) {
    final Level level = Level.TRACE;
    FormattingTuple tuple = MessageFormatter.format(format, arg);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }


  public void trace(Marker marker, String format, Object arg1, Object arg2) {
    final Level level = Level.TRACE;
    FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }

  public void trace(Marker marker, String format, Object[] argArray) {
    final Level level = Level.TRACE;
    FormattingTuple tuple = MessageFormatter.format(format, argArray);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }


  public void trace(Marker marker, String msg, Throwable t) {
    final Level level = Level.TRACE;
    log(level, marker, msg, t);
  }

//

  public void debug(String msg) {
    final Level level = Level.DEBUG;
    log(level, null, msg, null);
  }

  public void debug(String format, Object arg) {
    final Level level = Level.DEBUG;
    FormattingTuple tuple = MessageFormatter.format(format, arg);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void debug(String format, Object arg1, Object arg2) {
    final Level level = Level.DEBUG;
    FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void debug(String format, Object[] argArray) {
    final Level level = Level.DEBUG;
    FormattingTuple tuple = MessageFormatter.format(format, argArray);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void debug(String msg, Throwable t) {
    final Level level = Level.DEBUG;
    log(level, null, msg, t);
  }

  public void debug(Marker marker, String msg) {
    final Level level = Level.DEBUG;
    log(level, marker, msg, null);
  }

  public void debug(Marker marker, String format, Object arg) {
    final Level level = Level.DEBUG;
    FormattingTuple tuple = MessageFormatter.format(format, arg);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }


  public void debug(Marker marker, String format, Object arg1, Object arg2) {
    final Level level = Level.DEBUG;
    FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }

  public void debug(Marker marker, String format, Object[] argArray) {
    final Level level = Level.DEBUG;
    FormattingTuple tuple = MessageFormatter.format(format, argArray);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }


  public void debug(Marker marker, String msg, Throwable t) {
    final Level level = Level.DEBUG;
    log(level, marker, msg, t);
  }

//

  public void info(String msg) {
    final Level level = Level.INFO;
    log(level, null, msg, null);
  }

  public void info(String format, Object arg) {
    final Level level = Level.INFO;
    FormattingTuple tuple = MessageFormatter.format(format, arg);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void info(String format, Object arg1, Object arg2) {
    final Level level = Level.INFO;
    FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void info(String format, Object[] argArray) {
    final Level level = Level.INFO;
    FormattingTuple tuple = MessageFormatter.format(format, argArray);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void info(String msg, Throwable t) {
    final Level level = Level.INFO;
    log(level, null, msg, t);
  }

  public void info(Marker marker, String msg) {
    final Level level = Level.INFO;
    log(level, marker, msg, null);
  }

  public void info(Marker marker, String format, Object arg) {
    final Level level = Level.INFO;
    FormattingTuple tuple = MessageFormatter.format(format, arg);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }


  public void info(Marker marker, String format, Object arg1, Object arg2) {
    final Level level = Level.INFO;
    FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }

  public void info(Marker marker, String format, Object[] argArray) {
    final Level level = Level.INFO;
    FormattingTuple tuple = MessageFormatter.format(format, argArray);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }


  public void info(Marker marker, String msg, Throwable t) {
    final Level level = Level.INFO;
    log(level, marker, msg, t);
  }

//

  public void warn(String msg) {
    final Level level = Level.WARN;
    log(level, null, msg, null);
  }

  public void warn(String format, Object arg) {
    final Level level = Level.WARN;
    FormattingTuple tuple = MessageFormatter.format(format, arg);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void warn(String format, Object arg1, Object arg2) {
    final Level level = Level.WARN;
    FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void warn(String format, Object[] argArray) {
    final Level level = Level.WARN;
    FormattingTuple tuple = MessageFormatter.format(format, argArray);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void warn(String msg, Throwable t) {
    final Level level = Level.WARN;
    log(level, null, msg, t);
  }

  public void warn(Marker marker, String msg) {
    final Level level = Level.WARN;
    log(level, marker, msg, null);
  }

  public void warn(Marker marker, String format, Object arg) {
    final Level level = Level.WARN;
    FormattingTuple tuple = MessageFormatter.format(format, arg);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }


  public void warn(Marker marker, String format, Object arg1, Object arg2) {
    final Level level = Level.WARN;
    FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }

  public void warn(Marker marker, String format, Object[] argArray) {
    final Level level = Level.WARN;
    FormattingTuple tuple = MessageFormatter.format(format, argArray);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }


  public void warn(Marker marker, String msg, Throwable t) {
    final Level level = Level.WARN;
    log(level, marker, msg, t);
  }

//

  public void error(String msg) {
    final Level level = Level.ERROR;
    log(level, null, msg, null);
  }

  public void error(String format, Object arg) {
    final Level level = Level.ERROR;
    FormattingTuple tuple = MessageFormatter.format(format, arg);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void error(String format, Object arg1, Object arg2) {
    final Level level = Level.ERROR;
    FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void error(String format, Object[] argArray) {
    final Level level = Level.ERROR;
    FormattingTuple tuple = MessageFormatter.format(format, argArray);
    log(level, null, tuple.getMessage(), tuple.getThrowable());
  }

  public void error(String msg, Throwable t) {
    final Level level = Level.ERROR;
    log(level, null, msg, t);
  }

  public void error(Marker marker, String msg) {
    final Level level = Level.ERROR;
    log(level, marker, msg, null);
  }

  public void error(Marker marker, String format, Object arg) {
    final Level level = Level.ERROR;
    FormattingTuple tuple = MessageFormatter.format(format, arg);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }


  public void error(Marker marker, String format, Object arg1, Object arg2) {
    final Level level = Level.ERROR;
    FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }

  public void error(Marker marker, String format, Object[] argArray) {
    final Level level = Level.ERROR;
    FormattingTuple tuple = MessageFormatter.format(format, argArray);
    log(level, marker, tuple.getMessage(), tuple.getThrowable());
  }


  public void error(Marker marker, String msg, Throwable t) {
    final Level level = Level.ERROR;
    log(level, marker, msg, t);
  }

}
