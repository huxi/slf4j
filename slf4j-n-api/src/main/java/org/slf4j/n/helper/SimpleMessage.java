package org.slf4j.n.helper;

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
}
