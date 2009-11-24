package org.slf4j.n.helper;

import org.slf4j.n.helper.MessageFormatter;
import org.slf4j.n.Message;

import java.io.Serializable;

public class ParameterizedMessage
  implements Message, Serializable
{
  private static final long serialVersionUID = -665975803997290697L;

  private String messagePattern;
  private String[] parameters;
  private transient String formattedMessage;
  private transient Throwable throwable;

  public ParameterizedMessage(String messagePattern, Object... parameters)
  {
    this.messagePattern=messagePattern;
    MessageFormatter.ArgumentResult result = MessageFormatter.evaluateArguments(messagePattern, parameters);
    this.parameters = result.getArguments();
    this.throwable = result.getThrowable();
  }

  public String getFormattedMessage()
  {
    if(formattedMessage == null)
    {
      formatMessage();
    }
    return formattedMessage;
  }

  public String getMessagePattern()
  {
    return messagePattern;
  }

  public String[] getParameters()
  {
    return parameters.clone();
  }

  /**
   * Returns the Throwable that was given as the last argument, if any.
   * It will not survive serialization.
   *
   * @return the Throwable, if any.
   */
  public Throwable getThrowable()
  {
    return throwable;
  }

  private void formatMessage()
  {
    if(formattedMessage == null)
    {
      formattedMessage = MessageFormatter.format(messagePattern, parameters);
    }
  }
}
