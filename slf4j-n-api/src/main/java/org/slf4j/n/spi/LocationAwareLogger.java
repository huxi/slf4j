package org.slf4j.n.spi;

import org.slf4j.Marker;
import org.slf4j.core.Message;
import org.slf4j.n.Level;
import org.slf4j.n.Logger;

/**
 * An <b>optional</b> interface helping integration with logging systems capable of
 * extracting location information. This interface is mainly used by SLF4J bridges
 * such as jcl104-over-slf4j which need to provide hints so that the underlying logging
 * system can extract the correct location information (method name, line number, etc.).
 *
 * @author Ceki Gulcu, J&ouml;rn Huxhorn
 */
public interface LocationAwareLogger extends Logger {
  /**
   * Printing method with support for location information.
   *
   * @param marker
   * @param fqcn      The fully qualified class name of the <b>caller</b>
   * @param level
   * @param message
   * @param throwable
   */
  void log(String fqcn, Level level, Marker marker, Message message, Throwable throwable);

  /**
   * Printing method with support for location information.
   *
   * @param marker
   * @param fqcn      The fully qualified class name of the <b>caller</b>
   * @param level
   * @param message
   * @param throwable
   */
  void log(String fqcn, Level level, Marker marker, String message, Throwable throwable);
}
