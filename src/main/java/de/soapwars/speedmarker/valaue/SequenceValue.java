/*
 * Copyright (c) 2017 1&1 Mail & Media GmbH, Muenchen. All rights reserved.
 */
package de.soapwars.speedmarker.valaue;

import de.soapwars.speedmarker.ValueType;

import java.util.List;

/**
 * @author ptriller
 */
public class SequenceValue extends ValueBase {

  private final List<Object> value;

  public SequenceValue(List<Object> value) {
    this.value = value;
  }

  @Override
  public ValueType getPrimaryType() {
    return ValueType.SEQUENCE;
  }


  @Override
  public List<Object> asSequence() {
    return value;
  }

  @Override
  public String toString() {
    return value.toString();
  }

}
