package org.slf4j.n.helpers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.core.Message;
import org.slf4j.n.messages.ParameterizedMessage;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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
    ParameterizedMessage[] messages = new ParameterizedMessage[] {
        ParameterizedMessage.create("message1", "foo", "bar"),
        ParameterizedMessage.create("message2", "foo", null),
        ParameterizedMessage.create("message3"),
        ParameterizedMessage.create(null, null, null),
        ParameterizedMessage.create(null, "foo", "bar"),
      };
    messages[2].setParameters(null);
    messages[3].setParameters(null);


    for (ParameterizedMessage current : messages) {
      String pattern = current.getMessagePattern();
      String[] params = current.getParameters();
      if(params == null) {
        instance.push(pattern);
      } else {
        instance.push(pattern, params);
      }
    }

    Message[] stack = instance.getContextStack();
    assertArrayEquals(messages, stack);
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

    ParameterizedMessage[] messages = new ParameterizedMessage[] {
        ParameterizedMessage.create("message1", "foo", "bar"),
        ParameterizedMessage.create("message2", "foo", null),
        ParameterizedMessage.create("message3"),
        ParameterizedMessage.create(null),
        ParameterizedMessage.create(null, "foo", "bar"),
      };
    messages[2].setParameters(null);
    messages[3].setParameters(null);

    for (int i = 0; i < patterns.length; i++) {
      instance.push(patterns[i], args[i]);
    }

    Message[] stack = instance.getContextStack();
    Assert.assertArrayEquals(messages, stack);
  }

  @Test
  public void depth() {
    assertEquals(0, instance.getDepth());
    instance.push("Foo");
    instance.push("Bar");
    assertEquals(2, instance.getDepth());
    instance.pop();
    assertEquals(1, instance.getDepth());
    instance.pop();
    assertEquals(0, instance.getDepth());
    instance.pop();
    assertEquals(0, instance.getDepth());
  }

  @Test
  public void maximumDepthChange() {
    instance.push("Foo");
    instance.push("Bar");
    instance.setMaximumDepth(1);
    assertEquals(1, instance.getDepth());
    instance.pop();
    assertEquals(0, instance.getDepth());
  }

  @Test
  public void maximumDepthNoChange() {
    instance.push("Foo");
    instance.push("Bar");
    instance.setMaximumDepth(3);
    assertEquals(2, instance.getDepth());
  }
}
