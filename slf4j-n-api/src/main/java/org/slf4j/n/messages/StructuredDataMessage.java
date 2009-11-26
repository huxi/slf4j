package org.slf4j.n.messages;

import org.slf4j.n.Message;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.io.Serializable;

/**
 * Message to represent Structured Data. While structured data is defined by RFC 5424 for use
 * in Syslogs, it is a generally useful concept that can aid in writing data that can be easily
 * interprested.
 *
 * (based on rgoers/slf4j@02c35999dbd787efb8695410bf88a1dcdd743cac)
 * @author Ralph Goers, J&oml;rn Huxhorn
 */
public class StructuredDataMessage implements Message, Serializable
{
  private static final long serialVersionUID = 8283569438409820914L;

  public static final String FULL = "full";

  private Map<String,String> data = new HashMap<String,String>();

  private StructuredDataId id;

  private String message;

  private String type;

  public StructuredDataMessage(final String id, final String msg, final String type) {
    this.id = new StructuredDataId(id, null, null);
    this.message = msg;
    this.type = type;
  }

  public StructuredDataMessage(final StructuredDataId id, final String msg, final String type) {
    this.id = id;
    this.message = msg;
    this.type = type;
  }

  protected StructuredDataMessage() {

  }

  /**
   * Returns the identifier of structured data. This corresponds to the SD-ID
   * in RFC 5424.
   *
   * @return The structured data id.
   */
  public StructuredDataId getId() {
    return id;
  }

  protected void setId(String id) {
    this.id = new StructuredDataId(id, null, null);
  }

  protected void setId(StructuredDataId id) {
    this.id = id;
  }

  /**
   * Returns the type of data. This corresponds to the MSGID in RFC 5424.
   *
   * @return The message type.
   */
  public String getType() {
    return type;
  }

  protected void setType(String type) {
    if (type.length() > 32) {
      throw new IllegalArgumentException("Structured data type exceeds maximum length of 32 characters: " + type);
    }
    this.type = type;
  }

  /**
   * A message String that is associated with the data.
   *
   * @return The message String.
   */
  public String getMessage() {
    return message;
  }

  protected void setMessage(String msg) {
    this.message = msg;
  }

  /**
   * Returns an immutable Map of the data items and their values.
   *
   * @return The data item names (32 characters maximum) and their values.
   */
  public Map<String,String> getData() {
    return Collections.unmodifiableMap(data);
  }

  /**
   * Clears the data map.
   */
  public void clear() {
    data.clear();
  }

  /**
   * Add an item to the data Map.
   * @param key The name of the item.
   * @param value The value of the item.
   * @return this instance, to enable chaining of put calls
   */
  public StructuredDataMessage put(String key, String value) {
    if (value == null) {
      throw new IllegalArgumentException("No value provided for key " + key);
    }
    if (value.length() > 32) {
      throw new IllegalArgumentException("Structured data values are limited to 32 characters. key: " + key +
        " value: " + value);
    }
    data.put(key, value);
    return this;
  }

  /**
   * Add all the items from a Map to the data Map.
   * @param map The map to copy.
   * @return this instance, to enable chaining of put calls
   */
  public StructuredDataMessage putAll(Map<String,String> map)
  {
    for(Map.Entry<String,String> current:map.entrySet())
    {
      put(current.getKey(), current.getValue());
    }
    return this;
  }

  /**
   * Get a specific value from the data Map.
   * @param key The name of the item.
   * @return The value of the item.
   */
  public String get(String key) {
    return data.get(key);
  }

  /**
   * Remove an item from the data Map.
   * @param key The name of the item to remove.
   * @return The value of the item removed.
   */
  public String remove(String key) {
    return data.remove(key);
  }

  /**
   * Formats the structured data in the form [id key="value" ...] message as described in RFC 5424.
   *
   * @return The formatted String.
   */
  public final String asString() {
    return asString(FULL, null);
  }

  /**
   * Formats the structured data in the specified format. If the format is null or is something
   * the implementation does not support then the String will be
   * in the form [id key="value" ...] message as described in RFC 5424.
   *
   * @param format The format identifier.
   * @return The formatted String.
   */
  public String asString(String format) {
    return asString(format, null);
  }

  /**
   * Format the Structured data as described in RFC 5424.
   * @param format "full" will include the type and message. null will return only the STRUCTURED-DATA as
   * described in RFC 5424
   * @param structuredDataId The SD-ID as described in RFC 5424. If null the value in the StructuredData
   * will be used.
   * @return The formatted String.
   */
  public final String asString(String format, StructuredDataId structuredDataId) {
    StringBuilder sb = new StringBuilder();
    boolean full = FULL.equals(format);
    if (full) {
      String type = getType();
      if (type == null) {
        return sb.toString();
      }
      sb.append(getType()).append(" ");
    }
    StructuredDataId id = getId();
    if (id != null) {
      id = id.makeId(structuredDataId);
    } else {
      id = structuredDataId;
    }
    if (id == null || id.getName() == null || getData().size() == 0) {
      return sb.toString();
    }
    sb.append("[");
    sb.append(id);
    appendMap(getData(), sb);
    sb.append("]");
    if (full) {
      String msg = getMessage();
      if (msg != null) {
        sb.append(" ").append(msg);
      }
    }
    return sb.toString();
  }

  private void appendMap(Map<String,String> map, StringBuilder sb) {
    for (Map.Entry<String,String> current : map.entrySet())
    {
      sb.append(" ");
      sb.append(current.getKey()).append("=\"").append(current.getValue()).append("\"");
    }
  }

  public String toString() {
    return asString(null);
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    StructuredDataMessage that = (StructuredDataMessage) o;

    if (data != null ? !data.equals(that.data) : that.data != null) {
      return false;
    }
    if (type != null ? !type.equals(that.type) : that.type != null) {
      return false;
    }
    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (message != null ? !message.equals(that.message) : that.message != null) {
      return false;
    }

    return true;
  }

  public int hashCode() {
    int result = data != null ? data.hashCode() : 0;
    result = 31 * result + (type != null ? type.hashCode() : 0);
    result = 31 * result + (id != null ? id.hashCode() : 0);
    result = 31 * result + (message != null ? message.hashCode() : 0);
    return result;
  }

  public String getFormattedMessage()
  {
    return asString(null);
  }
}
