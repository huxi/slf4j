package org.slf4j.a;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ValueIdentifierTest {
  @Test
  public void identifierWithoutPrefix() {
    ValueIdentifier id = new ValueIdentifier("foo");
    assertNull(id.getPrefix());
    assertNull(id.getHeaderName());
  }

  @Test
  public void identifierWithPrefix() {
    ValueIdentifier id = new ValueIdentifier("c-foo");
    assertEquals(ValueIdentifier.Prefix.c, id.getPrefix());
    assertNull(id.getHeaderName());
  }

  @Test
  public void identifierWithHeader() {
    ValueIdentifier id = new ValueIdentifier("c(foo)");
    assertEquals(ValueIdentifier.Prefix.c, id.getPrefix());
    assertEquals("foo", id.getHeaderName());
  }

  @Test
  public void identifierWithHeaderWithoutName() {
    ValueIdentifier id = new ValueIdentifier("c()");
    assertEquals(ValueIdentifier.Prefix.c, id.getPrefix());
    assertEquals("", id.getHeaderName());
  }
}
