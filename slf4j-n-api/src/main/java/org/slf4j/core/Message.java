package org.slf4j.core;

/**
 * <p>
 * This is a general interface for a message that can be logged using SLF4J.
 * </p>
 *
 * <p>It's deliberately simple.</p>
 *
 * <p>
 * One could argue that it's similar to Object.toString() but this interface has a slightly different contract:<br/>
 * Formatting a message is generally an expensive operation. Because of this, Message implementation are supposed to format
 * messages lazily (i.e. only if really needed) and should cache formatted messages to reduce cost of multiple calls.
 * This is different to Object.toString() since this method is expected to print the actual state of an Object at the
 * time of the method call. Caching is highly unusual in typical implementations.<br/>
 * In addition, using a different method than Object.toString() enables us to define one for debugging purposes.
 * </p>
 *
 * <p>The class AbstractMessage already implements that behavior to ease Message implementations as intended.</p>
 *
 * @see AbstractMessage Abstract Message base class that already handles the lazy initialization and caching of the formatted message.
 * @see org.slf4j.n.messages.ParameterizedMessage An message that implements the default SLF4J parameterized message format.
 * @see org.slf4j.n.messages.JavaUtilFormatterMessage An message that's using java.util.Formatter.
 * @see org.slf4j.n.messages.SimpleMessage A no-op implementation that simply returns the String
 * @author Joern Huxhorn
 */
public interface Message
  extends Cloneable {
  
  String getFormattedMessage();

  Message clone() throws CloneNotSupportedException;
}
