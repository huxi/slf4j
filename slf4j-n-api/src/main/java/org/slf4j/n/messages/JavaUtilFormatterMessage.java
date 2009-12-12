package org.slf4j.n.messages;

import org.slf4j.core.Message;

import java.util.Formatter;
import java.util.Locale;

/**
 * Message implementation that's using java.util.Formatter to format the message.
 * This essentially provides everything that http://bugzilla.slf4j.org/show_bug.cgi?id=116
 * requested.
 * Please be aware that this Message is about 8 times slower than ParameterizedMessage!
 * ( http://bugzilla.slf4j.org/show_bug.cgi?id=116#c5 )
 * Also, this class is not Serializable since args are not guaranteed to be Serializable and there
 * is no conversion that can performed that would make the arguments Serializable an would not break
 * formatting, for example in case of java.util.Date.
 *
 * @author J&ouml;rn Huxhorn
 */
public class JavaUtilFormatterMessage
  implements Message {
  private Locale locale;
  private String format;
  private Object[] args;

  public JavaUtilFormatterMessage(String format, Object... args) {
    this(null, format, args);
  }

  public JavaUtilFormatterMessage(Locale locale, String format, Object... args) {
    this.locale = locale;
    this.format = format;
    this.args = args;
  }

  public String getFormattedMessage() {
    try {
      Formatter formatter = new Formatter();

      return formatter.format(locale, format, args).out().toString();
    }
    catch (Throwable t) {
      // we must not throw an exception here!
      return t.toString();
    }
  }

  public JavaUtilFormatterMessage clone() throws CloneNotSupportedException {
    JavaUtilFormatterMessage result = (JavaUtilFormatterMessage) super.clone();
    if (args != null) {
      result.args = new Object[args.length];
      System.arraycopy(args, 0, result.args, 0, args.length);
      // args are not deep-cloned.
    }
    if (locale != null) {
      result.locale = (Locale) locale.clone();
    }
    return result;
  }
}
