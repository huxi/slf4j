package org.slf4j.core;

public interface BasicLogger<T extends Enum>
{
  /**
   * @param level the Level
   * @return true, if logging at the given level is enabled.
   */
  boolean isEnabled(T level);


  void log(T level, Message message);

  void log(T level, Message message, Throwable throwable);
}
