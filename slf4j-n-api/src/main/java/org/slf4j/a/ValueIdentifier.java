package org.slf4j.a;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Value identifier as described in http://www.w3.org/TR/WD-logfile.html
 */
public class ValueIdentifier
    implements Serializable
{
  private static final long serialVersionUID = -2771803418785509977L;

  private String identifier;
  private transient Prefix prefix;
  private transient String headerName;

  public ValueIdentifier()
  {
    this(null);
  }

  public ValueIdentifier(String identifier)
  {
    setIdentifier(identifier);
  }

  public String getIdentifier()
  {
    return identifier;
  }

  public void setIdentifier(String identifier)
  {
    this.identifier = identifier;
    this.prefix = Prefix.resolve(identifier);
    this.headerName = resolveHeaderName(identifier);
  }

  public Prefix getPrefix()
  {
    return prefix;
  }

  public String getHeaderName()
  {
    return headerName;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ValueIdentifier that = (ValueIdentifier) o;

    if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;

    return true;
  }

  private static String resolveHeaderName(String identifier)
  {
    if(identifier == null)
    {
      return null;
    }
    int startIdx = identifier.indexOf('(');
    int endIdx = identifier.indexOf(')');
    if (startIdx < 0 || endIdx < 0 || endIdx < startIdx+1)
    {
      return null;
    }
    return identifier.substring(startIdx+1, endIdx);
  }

  @Override
  public int hashCode()
  {
    return identifier != null ? identifier.hashCode() : 0;
  }

  public enum Prefix
  {
    c("Client"),
    s("Server"),
    r("Remote"),
    cs("Client to Server."),
    sc("Server to Client."),
    sr("Server to Remote Server, this prefix is used by proxies."),
    rs("Remote Server to Server, this prefix is used by proxies."),
    x("Application specific identifier.");

    private String description;

    Prefix(String description)
    {
      this.description = description;
    }

    public String getDescription()
    {
      return description;
    }

    private static final Map<String, Prefix> values;

    static
    {
      values = new HashMap<String, Prefix>();
      for (Prefix current : Prefix.values())
      {
        values.put(current.name(), current);
      }
    }

    public static Prefix resolve(String identifier)
    {
      if (identifier == null)
      {
        return null;
      }
      int sepIdx = identifier.indexOf('-');
      if (sepIdx < 0)
      {
        sepIdx = identifier.indexOf('(');
        if (sepIdx < 0)
        {
          return null;
        }
      }
      String prefix = identifier.substring(0, sepIdx);
      return values.get(prefix);
    }
  }

}
