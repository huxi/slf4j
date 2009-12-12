package org.slf4j.n.messages;

import java.io.Serializable;

/**
 * The StructuredData identifier
 */
public class StructuredDataId implements Serializable, Cloneable {
  private static final long serialVersionUID = 9031746276396249990L;

  public static StructuredDataId TIME_QUALITY = new StructuredDataId("timeQuality", null,
    new String[]{"tzKnown", "isSynced", "syncAccuracy"});
  public static StructuredDataId ORIGIN = new StructuredDataId("origin", null,
    new String[]{"ip", "enterpriseId", "software", "swVersion"});
  public static StructuredDataId META = new StructuredDataId("meta", null,
    new String[]{"sequenceId", "sysUpTime", "language"});

  public static final int RESERVED = -1;

  private final String name;
  private final int enterpriseNumber;
  private String[] required;
  private String[] optional;

  protected StructuredDataId(String name, String[] required, String[] optional) {
    int index = -1;
    if (name != null) {
      if (name.length() > 32) {
        throw new IllegalArgumentException("Length of id exceeds maximum of 32 characters: " + name);
      }
      index = name.indexOf("@");
    }

    if (index > 0) {
      this.name = name.substring(0, index);
      this.enterpriseNumber = Integer.parseInt(name.substring(index + 1));
    }
    else {
      this.name = name;
      this.enterpriseNumber = RESERVED;
    }
    this.required = required;
    this.optional = optional;
  }

  /**
   * A Constructor that helps conformance to RFC 5424.
   *
   * @param name             The name portion of the id.
   * @param enterpriseNumber The enterprise number.
   * @param required         The list of keys that are required for this id.
   * @param optional         The list of keys that are optional for this id.
   */
  public StructuredDataId(String name, int enterpriseNumber, String[] required, String[] optional) {
    if (name == null) {
      throw new IllegalArgumentException("No structured id name was supplied");
    }
    if (enterpriseNumber <= 0) {
      throw new IllegalArgumentException("No enterprise number was supplied");
    }
    this.name = name;
    this.enterpriseNumber = enterpriseNumber;
    String id = enterpriseNumber < 0 ? name : name + "@" + enterpriseNumber;
    if (id.length() > 32) {
      throw new IllegalArgumentException("Length of id exceeds maximum of 32 characters: " + id);
    }
    this.required = required;
    this.optional = optional;
  }

  public StructuredDataId makeId(StructuredDataId id) {
    if (id == null) {
      return this;
    }
    return makeId(id.getName(), id.getEnterpriseNumber());
  }

  public StructuredDataId makeId(String defaultId, int enterpriseNumber) {
    String id;
    String[] req;
    String[] opt;
    if (enterpriseNumber <= 0) {
      return this;
    }
    if (this.name != null) {
      id = this.name;
      req = this.required;
      opt = this.optional;
    }
    else {
      id = defaultId;
      req = null;
      opt = null;
    }

    return new StructuredDataId(id, enterpriseNumber, req, opt);
  }

  public String[] getRequired() {
    if (required == null) {
      return null;
    }
    if (required.length == 0) {
      return required;
    }

    String[] result = new String[required.length];
    System.arraycopy(required, 0, result, 0, required.length);

    return result;
  }

  public String[] getOptional() {
    if (optional == null) {
      return null;
    }
    if (optional.length == 0) {
      return optional;
    }

    String[] result = new String[optional.length];
    System.arraycopy(optional, 0, result, 0, optional.length);

    return result;
  }

  public String getName() {
    return name;
  }

  public int getEnterpriseNumber() {
    return enterpriseNumber;
  }

  public boolean isReserved() {
    return enterpriseNumber <= 0;
  }

  public String toString() {
    return isReserved() ? name : name + "@" + enterpriseNumber;
  }

  public StructuredDataId clone() throws CloneNotSupportedException {
    StructuredDataId result = (StructuredDataId) super.clone();

    result.required = getRequired();
    result.optional = getOptional();

    return result;
  }
}
