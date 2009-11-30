package org.slf4j.n.helpers;

import org.slf4j.n.ILoggerFactory;
import org.slf4j.n.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * SubstituteLoggerFactory is an trivial implementation of {@link
 * ILoggerFactory} which always returns the unique instance of NOPLogger.
 * <p/>
 * <p/>
 * It used as a temporary substitute for the real ILoggerFactory during its
 * auto-configuration which may re-enter LoggerFactory to obtain logger
 * instances. See also http://bugzilla.slf4j.org/show_bug.cgi?id=106
 *
 * @author Ceki G&uuml;lc&uuml;, J&ouml;rn Huxhorn
 */
public class SubstituteLoggerFactory implements ILoggerFactory {

  // keep a record of requested logger names
  private final List<String> loggerNameList = new ArrayList<String>();

  public Logger getLogger(String name) {
    loggerNameList.add(name);
    return NOPLogger.NOP_LOGGER;
  }

  public List<String> getLoggerNameList() {
    return loggerNameList;
  }

}
