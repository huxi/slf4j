package org.slf4j.n;

import org.junit.Test;
import org.slf4j.n.test.TestLogger;

public class AbstractLoggerBaseTest {


  @Test
  public void debug() {
    final org.slf4j.n.Logger newLogger = org.slf4j.n.LoggerFactory.getLogger(AbstractLoggerBaseTest.class);
    final org.slf4j.Logger oldLogger = org.slf4j.LoggerFactory.getLogger(AbstractLoggerBaseTest.class);
    final TestLogger testLogger = (TestLogger) newLogger;
    Throwable t = new Throwable();
    newLogger.debug("A message with Throwable and placeholder {}.", "Foo", t);

    oldLogger.debug("A message with Throwable and placeholder {}.", new Object[]{"Foo", t});

    newLogger.debug("A message with Throwable and placeholder {} {}.", "Foo", t);

    oldLogger.debug("A message with Throwable and placeholder {} {}.", new Object[]{"Foo", t});
    System.out.println(testLogger.getEvents());
  }
}
