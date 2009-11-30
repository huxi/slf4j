package org.slf4j.n.messages;

import static de.huxhorn.sulky.junit.JUnitTools.testSerialization;
import static de.huxhorn.sulky.junit.JUnitTools.testXmlSerialization;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import java.io.IOException;

public class SimpleMessageTest {
  @Test
  public void uninitialzed() throws IOException, ClassNotFoundException {
    SimpleMessage message = new SimpleMessage();
    testSerialization(message);
    testXmlSerialization(message);
  }

  @Test
  public void initialzed() throws IOException, ClassNotFoundException {
    SimpleMessage message = new SimpleMessage("Foo");
    testSerialization(message);
    testXmlSerialization(message);
  }

  @Test
  public void checkEquals() {
    SimpleMessage msg1 = new SimpleMessage("Foo");
    SimpleMessage msg2 = new SimpleMessage("Foo");
    assertEquals(msg1, msg2);
    assertEquals(msg1.hashCode(), msg2.hashCode());
    msg2.setMessage("Bar");
    assertFalse(msg1.equals(msg2));
  }
}
