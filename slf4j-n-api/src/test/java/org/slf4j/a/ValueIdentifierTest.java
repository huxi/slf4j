package org.slf4j.a;

import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class ValueIdentifierTest
{
  @Test
  public void identifierWithoutPrefix()
  {
    ValueIdentifier id=new ValueIdentifier("foo");
    assertNull(id.getPrefix());
    assertNull(id.getHeaderName());
  }

  @Test
  public void identifierWithPrefix()
  {
    ValueIdentifier id=new ValueIdentifier("c-foo");
    assertEquals(ValueIdentifier.Prefix.c, id.getPrefix());
    assertNull(id.getHeaderName());
  }

  @Test
  public void identifierWithHeader()
  {
    ValueIdentifier id=new ValueIdentifier("c(foo)");
    assertEquals(ValueIdentifier.Prefix.c, id.getPrefix());
    assertEquals("foo", id.getHeaderName());
  }

  @Test
  public void identifierWithHeaderWithoutName()
  {
    ValueIdentifier id=new ValueIdentifier("c()");
    assertEquals(ValueIdentifier.Prefix.c, id.getPrefix());
    assertEquals("", id.getHeaderName());
  }
}
