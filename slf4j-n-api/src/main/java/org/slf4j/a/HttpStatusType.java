package org.slf4j.a;

/**
 * HTTP/1.1 Status Types according to
 * http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html
 */
public enum HttpStatusType
{
  INFORMATIONAL("Informational",100),
  SUCCESSFUL("Successful", 200),
  REDIRECTION("Redirection", 300),
  CLIENT_ERROR("Client Error", 400),
  SERVER_ERROR("Server Error", 500);

  private String description;
  private int threshold;

  HttpStatusType(String description, int threshold)
  {
    this.description = description;
    this.threshold = threshold;
  }

  public static HttpStatusType resolve(int statusCode)
  {
    if(statusCode < INFORMATIONAL.getThreshold() || statusCode >= SERVER_ERROR.getThreshold() + 100)
    {
      return null;
    }
    if(statusCode < SUCCESSFUL.getThreshold())
    {
      return INFORMATIONAL;
    }
    if(statusCode < REDIRECTION.getThreshold())
    {
      return SUCCESSFUL;
    }
    if(statusCode < CLIENT_ERROR.getThreshold())
    {
      return REDIRECTION;
    }
    if(statusCode < SERVER_ERROR.getThreshold())
    {
      return CLIENT_ERROR;
    }
    return SERVER_ERROR;
  }

  public String getDescription()
  {
    return description;
  }

  public int getThreshold()
  {
    return threshold;
  }

  @Override
  public String toString()
  {
    return description;
  }
}
