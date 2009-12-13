package org.slf4j.n.helpers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.core.Message;
import org.slf4j.n.messages.ParameterizedMessage;
import org.slf4j.n.messages.SimpleMessage;

public class SimpleNDCAdapterTest {
  private SimpleNDCAdapter instance;

  @Before
  public void setUp() {
    instance = new SimpleNDCAdapter();
  }

  @Test
  public void isEmpty() {
    Assert.assertTrue(instance.isEmpty());

  }

  @Test
  public void pushMessageUsingIsEmpty() {
    instance.push("message");
    Assert.assertFalse(instance.isEmpty());
  }

  @Test
  public void pushMessagePatternUsingIsEmpty() {
    instance.push("messagePattern", "foo", "bar");
    Assert.assertFalse(instance.isEmpty());
  }

  @Test
  public void popUsingPushAndIsEmpty() {
    instance.push("message");
    Assert.assertFalse(instance.isEmpty());
    instance.pop();
    Assert.assertTrue(instance.isEmpty());
  }

  @Test
  public void clearUsingPushAndIsEmpty() {
    instance.push("message");
    instance.push("message");
    Assert.assertFalse(instance.isEmpty());
    instance.clear();
    Assert.assertTrue(instance.isEmpty());
  }

  @Test
  public void getContextStackEmpty() {
    Message[] stack = instance.getContextStack();
    Assert.assertArrayEquals(new Message[0], stack);
  }

  @Test
  public void getContextStackUsingPush() {
    ParameterizedMessage[] messages = new ParameterizedMessage[]
      {
        ParameterizedMessage.create("message1", "foo", "bar"),
        ParameterizedMessage.create("message2", "foo", null),
        ParameterizedMessage.create("message3"),
        ParameterizedMessage.create(null),
        ParameterizedMessage.create(null, "foo", "bar"),
      };


    for (ParameterizedMessage current : messages) {
      instance.push(current);
    }

    Message[] stack = instance.getContextStack();
    Assert.assertArrayEquals(messages, stack);
  }

  @Test
  public void getContextStackUsingPushArgs() {
    String[] patterns = new String[]{
      "message1",
      "message2",
      "message3",
      null,
      null
    };

    Object[][] args = new Object[][]{
      new String[]{"foo", "bar"},
      new String[]{"foo", null},
      null,
      null,
      new String[]{"foo", "bar"},
    };

    Message[] messages = new Message[]
      {
        ParameterizedMessage.create("message1", "foo", "bar"),
        ParameterizedMessage.create("message2", "foo", null),
        new SimpleMessage("message3"),
        new SimpleMessage(null),
        ParameterizedMessage.create(null, "foo", "bar"),
      };


    for (int i = 0; i < patterns.length; i++) {
      instance.push(patterns[i], args[i]);
    }

    Message[] stack = instance.getContextStack();
    Assert.assertArrayEquals(messages, stack);
  }

  @Test
  public void inheritance()
    throws InterruptedException {
    Thread parent = new Thread(new Level1Runnable());
    parent.start();
    parent.join();
  }

  @Test
  public void depth() {
    Assert.assertEquals(0, instance.getDepth());
    instance.push("Foo");
    instance.push("Bar");
    Assert.assertEquals(2, instance.getDepth());
    instance.pop();
    Assert.assertEquals(1, instance.getDepth());
    instance.pop();
    Assert.assertEquals(0, instance.getDepth());
    instance.pop();
    Assert.assertEquals(0, instance.getDepth());
  }

  @Test
  public void maximumDepthChange() {
    instance.push("Foo");
    instance.push("Bar");
    instance.setMaximumDepth(1);
    Assert.assertEquals(1, instance.getDepth());
    instance.pop();
    Assert.assertEquals(0, instance.getDepth());
  }

  @Test
  public void maximumDepthNoChange() {
    instance.push("Foo");
    instance.push("Bar");
    instance.setMaximumDepth(3);
    Assert.assertEquals(2, instance.getDepth());
  }

  public class Level1Runnable
    implements Runnable {

    public void run() {
      instance.push("Foo");
      Assert.assertFalse(instance.isEmpty());
      Thread child = new Thread(new Level2Runnable());
      child.start();
      try {
        child.join();
      }
      catch (InterruptedException e) {
        // ignore
      }
      Assert.assertFalse(instance.isEmpty());
      instance.pop();
      Assert.assertTrue(instance.isEmpty());
    }
  }

  public class Level2Runnable
    implements Runnable {

    public void run() {
      instance.push("Bar");
      Message[] contextStack = instance.getContextStack();
      Assert.assertEquals(2, contextStack.length);
      instance.pop();
      instance.pop();
    }
  }
}
