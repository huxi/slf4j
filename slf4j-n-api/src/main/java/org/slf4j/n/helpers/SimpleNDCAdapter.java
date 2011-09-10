package org.slf4j.n.helpers;

import org.slf4j.core.Message;
import org.slf4j.n.messages.ParameterizedMessage;

import java.util.LinkedList;
import java.util.List;

public class SimpleNDCAdapter
  implements NDCAdapter {
  private final ThreadLocal<List<String>> threadLocalMessagePatterns = new ThreadLocal<List<String>>();
  private final ThreadLocal<List<String[]>> threadLocalMessageArguments = new ThreadLocal<List<String[]>>();

  public void push(String messagePattern, Object... arguments) {
    String[] processedArgs = null;

    if (arguments != null && arguments.length > 0) {
      ParameterizedMessage message = ParameterizedMessage.create(messagePattern, arguments);
      processedArgs = message.getParameters();
      if(processedArgs != null && processedArgs.length == 0) {
        processedArgs = null;
      }
    }

    List<String> messages = threadLocalMessagePatterns.get();
    List<String[]> args = threadLocalMessageArguments.get();
    if (messages == null) {
      messages = new LinkedList<String>();
      args = new LinkedList<String[]>();
      threadLocalMessagePatterns.set(messages);
      threadLocalMessageArguments.set(args);
    }
    messages.add(messagePattern);
    args.add(processedArgs);
  }

  public void pop() {
    List<String> messages = threadLocalMessagePatterns.get();
    if (messages == null) {
      return;
    }
    int count = messages.size();
    if (count == 0 || count == 1) {
      clear();
      return;
    }
    List<String[]> args = threadLocalMessageArguments.get();
    messages.remove(count - 1);
    args.remove(count - 1);
  }

  public int getDepth() {
    List<String> messages = threadLocalMessagePatterns.get();
    if (messages == null) {
      return 0;
    }
    int count = messages.size();
    if (count == 0) {
      // should never happen
      clear();
    }
    return count;
  }

  public void setMaximumDepth(int maximumDepth) {
    int overflow = getDepth() - maximumDepth;
    for (int i = 0; i < overflow; i++) {
      pop();
    }
  }

  public boolean isEmpty() {
    return getDepth() == 0;
  }

  public void clear() {
    threadLocalMessagePatterns.remove();
    threadLocalMessageArguments.remove();
  }

  public Message[] getContextStack() {
    List<String> messages = threadLocalMessagePatterns.get();
    if (messages == null) {
      return NO_MESSAGES;
    }
    int count = messages.size();
    if (count == 0) {
      // should never happen
      clear();
      return NO_MESSAGES;
    }
    List<String[]> args = threadLocalMessageArguments.get();

    Message[] result = new Message[count];
    for (int i = 0; i < count; i++) {
      result[i] = new ParameterizedMessage(messages.get(i), args.get(i), null);
    }
    return result;
  }
}
