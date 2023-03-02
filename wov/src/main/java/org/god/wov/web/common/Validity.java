package org.god.wov.web.common;

public enum Validity {

  VALID("valid"),
  INVALID("invalid"),
  ALL_VALID("all valid"),
  SOME_INVALID("some invalid"),
  ALL_INVALID("all invalid");

  private final String label;

  Validity(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
