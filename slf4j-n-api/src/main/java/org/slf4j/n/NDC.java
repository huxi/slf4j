package org.slf4j.n;

import org.slf4j.core.Message;
import org.slf4j.n.helpers.NDCAdapter;
import org.slf4j.n.helpers.SimpleNDCAdapter;

public class NDC {
  private static final NDCAdapter ndcAdapter;

  static {
    // TODO: configuration
    ndcAdapter = new SimpleNDCAdapter();
  }

  private NDC() {

  }


  public static void push(String messagePattern, Object... arguments) {
    ndcAdapter.push(messagePattern, arguments);
  }

  /**
   * Pops the last message from the stack.
   * <p/>
   * This method does not return the popped message to discourage it's usage in application logic.
   */
  public static void pop() {
    ndcAdapter.pop();
  }

  public static int getDepth() {
    return ndcAdapter.getDepth();
  }

  public static void setMaximumDepth(int maximumDepth) {
    ndcAdapter.setMaximumDepth(maximumDepth);
  }

  public static boolean isEmpty() {
    return ndcAdapter.isEmpty();
  }

  public static void clear() {
    ndcAdapter.clear();
  }

  /**
   * Returns an array containing all messages of the stack.
   * <p/>
   * The messages from the NDC stack should not be used in application logic.
   *
   * @return an array containing all messages of the stack.
   */
  public static Message[] getContextStack() {
    return ndcAdapter.getContextStack();
  }
}
