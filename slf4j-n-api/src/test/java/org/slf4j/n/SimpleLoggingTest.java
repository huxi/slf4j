package org.slf4j.n;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import static org.slf4j.n.SimpleLogging.error;

/**
 * This test isn't really testing very much, yet. ;)
 */
public class SimpleLoggingTest {

  @Test
  public void testCallerClass()
  {
    String callerClass=SimpleLogging.resolveCallerClass(1);
    assertEquals(SimpleLoggingTest.class.getName(), callerClass);
  }

  @Test
  public void testIsErrorEnabled()
  {
    assertTrue(SimpleLogging.isErrorEnabled());
  }

  @Test
  public void testError()
  {
    error("Test! {}", "foo");
  }

  @Test
  public void testCallingLogger()
  {
    Logger logger = SimpleLogging.getCallingLogger();
    logger.error("CallingLogger-Test! {}", "foo");
    Runnable r=new Runnable() {
      public void run() {
        Logger logger = SimpleLogging.getCallingLogger();
        logger.error("CallingLogger-InnerTest! {}", "foo");
      }
    };
    r.run();
    B b=new B();
    b.foo();

  }

  public class A
  {
    private final Logger logger = SimpleLogging.getCallingLogger();

    public void foo()
    {
      logger.warn("A.foo");
    }
  }

  public class B
    extends A
  {
    private final Logger logger = SimpleLogging.getCallingLogger();

    public void foo()
    {
      super.foo();
      logger.warn("B.foo");
    }
  }
}
