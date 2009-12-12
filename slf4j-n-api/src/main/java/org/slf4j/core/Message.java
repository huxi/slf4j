package org.slf4j.core;

public interface Message
  extends Cloneable {
  
  String getFormattedMessage();

  Message clone() throws CloneNotSupportedException;
}
