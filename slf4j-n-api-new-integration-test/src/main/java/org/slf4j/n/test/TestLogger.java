package org.slf4j.n.test;

import org.slf4j.Marker;
import org.slf4j.core.Message;
import org.slf4j.n.Level;
import org.slf4j.n.Logger;
import org.slf4j.n.Threshold;
import org.slf4j.n.helpers.AbstractLoggerBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Logger implementation for testing.
 */
public class TestLogger
    extends AbstractLoggerBase implements Logger {
  private static final long serialVersionUID = 4339490930642966550L;

  private Threshold threshold = Threshold.ALL;
  private final List<PrimitiveEvent> events = new ArrayList<PrimitiveEvent>();

  public TestLogger() {
    super();
  }

  public TestLogger(String loggerName) {
    super(loggerName);
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

  public void log(Level level, Marker marker, Message message, Throwable throwable) {
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
    private Message message;
    private String messageString;
    private Throwable throwable;

    public PrimitiveEvent() {
    }

    public PrimitiveEvent(Level level, Marker marker, Message message, Throwable throwable) {
      this.level = level;
      this.marker = marker;
      this.message = message;
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

    public Message getMessage() {
      return message;
    }

    public void setMessage(Message message) {
      this.message = message;
      if (message != null) {
        messageString = message.getFormattedMessage();
      }
      else {
        messageString = null;
      }
    }

    public Throwable getThrowable() {
      return throwable;
    }

    public void setThrowable(Throwable throwable) {
      this.throwable = throwable;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      PrimitiveEvent that = (PrimitiveEvent) o;

      if (level != that.level) return false;
      if (marker != null ? !marker.equals(that.marker) : that.marker != null) return false;
      if (message != null ? !message.equals(that.message) : that.message != null) return false;
      if (throwable != null ? !throwable.equals(that.throwable) : that.throwable != null) return false;

      return true;
    }

    @Override
    public int hashCode() {
      int result = level != null ? level.hashCode() : 0;
      result = 31 * result + (marker != null ? marker.hashCode() : 0);
      result = 31 * result + (message != null ? message.hashCode() : 0);
      result = 31 * result + (throwable != null ? throwable.hashCode() : 0);
      return result;
    }

    public String toString() {
      return "PrimitiveEvent[level=" + level + ", marker=" + marker + ", message=" + message + ", throwable=" + throwable + ", messageString=" + messageString + "]";
    }
  }

}
