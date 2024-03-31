package com.cell.common.utils;

import java.io.Serializable;
import java.util.UUID;

public class ModelEntityId implements Serializable {

  private String type;
  private UUID id;

  public ModelEntityId() {}

  public ModelEntityId(String type, UUID id) {
    this.type = type;
    this.id = id;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public String getObjectId() {
    return String.format("%s_%s", type, id);
  }

  public static ModelEntityId from(final String id) {
    if (!id.contains("_")) {
      throw new RuntimeException("Base Object ID does not have object separator.");
    }
    final String objectType = id.substring(0, id.indexOf("_"));
    final String objectId = id.substring(id.indexOf("_") + 1);
    try {
      final UUID objectUUID = UUID.fromString(objectId);
      return new ModelEntityId(objectType, objectUUID);
    } catch (IllegalArgumentException e) {
      throw new RuntimeException(
          "Invalid Model ID format"
      );
    }
  }

  @Override
  public String toString() {
    return String.format("%s_%s", type, id);
  }
}
