package org.slf4j.core;

/**
 * <p>Abstract Message base class that already handles the lazy initialization and caching of the formatted message.</p>
 * <p/>
 * <p>Extending classes only need to implement generateFormattedMessage().<br/>
 * The formatted message is generated on demand and cached in a transient variable, i.e. it won't be serialized.</p>
 *
 * @author Joern Huxhorn
 * @see Message
 */
public abstract class AbstractMessage
        implements Message {
  private transient String formattedMessage = null;

  protected void resetFormattedMessage() {
    formattedMessage = null;
  }

  public final String getFormattedMessage() {
    if (formattedMessage != null) {
      return formattedMessage;
    }
    formattedMessage = generateFormattedMessage();
    return formattedMessage;
  }

  protected abstract String generateFormattedMessage();

  public AbstractMessage clone() throws CloneNotSupportedException {
    return (AbstractMessage) super.clone();
  }
}
