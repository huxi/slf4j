package org.slf4j.n.messages;

import org.slf4j.core.Message;

import java.io.Serializable;

/**
 * The simplest possible implementation of Message. It just returns the String given as the c'tor argument.
 *
 * @author J&ouml;rn Huxhorn
 */
public class SimpleMessage
    implements Message, Serializable {
  private static final long serialVersionUID = -8398002534962715992L;

  private String message;

  public SimpleMessage() {
    this(null);
  }

  public SimpleMessage(String message) {
    this.message = message;
  }

  /**
   * @return the same as getFormattedMessage, this method exists to support xml serialization.
   */
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getFormattedMessage() {
    return message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SimpleMessage that = (SimpleMessage) o;

    return !(message != null ? !message.equals(that.message) : that.message != null);
  }

  @Override
  public int hashCode() {
    return message != null ? message.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "SimpleMessage[message=" + message + "]";
  }
}
