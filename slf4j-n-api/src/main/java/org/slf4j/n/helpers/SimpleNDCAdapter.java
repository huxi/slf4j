package org.slf4j.n.helpers;

import org.slf4j.core.Message;
import org.slf4j.n.helpers.NDCAdapter;
import org.slf4j.n.messages.ParameterizedMessage;
import org.slf4j.n.messages.SimpleMessage;

import java.util.ArrayList;
import java.util.List;

public class SimpleNDCAdapter
  implements NDCAdapter {
  private CloningNdcStackThreadLocal ndcStackThreadLocal = new CloningNdcStackThreadLocal();

  public void push(Message message) {
    getNdcStack().push(message);
  }

  public void push(String messagePattern, Object... arguments) {
    getNdcStack().push(messagePattern, arguments);
  }

  public void pop() {
    getNdcStack().pop();
  }

  public int getDepth() {
    return getNdcStack().getDepth();
  }

  public void setMaximumDepth(int maximumDepth) {
    getNdcStack().setMaximumDepth(maximumDepth);
  }

  public boolean isEmpty() {
    return getNdcStack().isEmpty();
  }

  public void clear() {
    getNdcStack().clear();
  }

  public Message[] getContextStack() {
    return getNdcStack().getContextStack();
  }

  private NdcStack getNdcStack() {
    NdcStack result = ndcStackThreadLocal.get();
    if (result == null) {
      result = new NdcStack();
      ndcStackThreadLocal.set(result);
    }
    return result;
  }

  private static class CloningNdcStackThreadLocal
    extends InheritableThreadLocal<NdcStack> {
    @Override
    protected NdcStack childValue(NdcStack parentValue) {
      NdcStack result = null;
      if (parentValue != null) {
        // this method seems to get called only if parent
        // is not null but this isn't documented so I'll make sure...
        try {
          result = parentValue.clone();
        }
        catch (CloneNotSupportedException e) {
          // can't happen, see above...
        }
      }
      return result;
    }
  }

  private static class NdcStack
    implements Cloneable {
    private List<Message> stackList;

    private NdcStack() {
      stackList = new ArrayList<Message>();
    }

    public int getDepth() {
      return stackList.size();
    }

    public void setMaximumDepth(int maximumDepth) {
      int overflow = stackList.size() - maximumDepth;
      for (int i = 0; i < overflow; i++) {
        pop();
      }
    }

    public void push(Message message) {
      stackList.add(message);
    }

    public void push(String messagePattern, Object... arguments) {
      if (arguments == null || arguments.length == 0) {
        push(new SimpleMessage(messagePattern));
        return;
      }
      stackList.add(ParameterizedMessage.create(messagePattern, arguments));
    }

    public void pop() {
      int size = stackList.size();
      if (size > 0) {
        stackList.remove(size - 1);
      }
    }

    public boolean isEmpty() {
      return stackList.isEmpty();
    }

    public void clear() {
      stackList.clear();
    }

    public Message[] getContextStack() {
      if (stackList.isEmpty()) {
        return NO_MESSAGES;
      }

      Message[] result = new Message[stackList.size()];
      for (int i = 0; i < stackList.size(); i++) {
        try {
          result[i] = stackList.get(i).clone();
        }
        catch (CloneNotSupportedException e) {
          // can't happen... yeah, I know... it *will* happen one day :p
        }
      }
      return result;
    }

    public NdcStack clone()
      throws CloneNotSupportedException {
      NdcStack result = (NdcStack) super.clone();

      ArrayList<Message> clonedStackList = new ArrayList<Message>(stackList.size());
      for (Message current : stackList) {
        try {
          clonedStackList.add(current.clone());
        }
        catch (CloneNotSupportedException e) {
          // can't happen... yeah, I know... it *will* happen one day :p
          clonedStackList.add(null);
        }
      }
      result.stackList = clonedStackList;

      return result;
    }
  }
}
