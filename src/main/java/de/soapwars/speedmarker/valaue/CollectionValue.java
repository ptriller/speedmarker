/*
 * Copyright (c) 2017 1&1 Mail & Media GmbH, Muenchen. All rights reserved.
 */
package de.soapwars.speedmarker.valaue;

import de.soapwars.speedmarker.Value;
import de.soapwars.speedmarker.ValueType;

import java.util.Collection;

/**
 * @author ptriller
 */
public class CollectionValue extends ValueBase {

  private final Collection<Object> value;

  public CollectionValue(Collection<Object> value) {
    this.value = value;
  }

  @Override
  public ValueType getPrimaryType() {
    return ValueType.COLLECTION;
  }

  @Override
  public Collection<Object> asCollection() {
    return value;
  }

  @Override
  public String toString() {
    return value.toString();
  }

}
