package org.slf4j.n.helpers;

import org.slf4j.core.Message;

public interface NDCAdapter {

  void push(String messagePattern, Object... arguments);

  void pop();

  int getDepth();

  void setMaximumDepth(int maximumDepth);

  boolean isEmpty();

  void clear();

  Message[] getContextStack();

  Message[] NO_MESSAGES = new Message[0];
}
