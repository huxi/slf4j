package org.slf4j.n.messages;

import org.slf4j.n.Message;

import java.io.Serializable;

public class SimpleMessage
  implements Message, Serializable
{
  private static final long serialVersionUID = -8398002534962715992L;

  private final String message;

  public SimpleMessage(String message)
  {
    this.message = message;
  }

  public String getFormattedMessage()
  {
    return message;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SimpleMessage that = (SimpleMessage) o;

    return !(message != null ? !message.equals(that.message) : that.message != null);
  }

  @Override
  public int hashCode()
  {
    return message != null ? message.hashCode() : 0;
  }

  @Override
  public String toString()
  {
    return "SimpleMessage[message="+message+"]";
  }
}
