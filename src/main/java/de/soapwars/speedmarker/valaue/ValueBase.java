/*
 * Copyright (c) 2017 1&1 Mail & Media GmbH, Muenchen. All rights reserved.
 */
package de.soapwars.speedmarker.valaue;

import de.soapwars.speedmarker.SpeedMarkerException;
import de.soapwars.speedmarker.Value;
import de.soapwars.speedmarker.ValueType;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author ptriller
 */
public abstract class ValueBase implements Value {

  @Override
  public boolean isType(ValueType type) {
    return type == getPrimaryType();
  }

  @Override
  public String asString() {
    throw new SpeedMarkerException("Not a string");
  }

  @Override
  public Number asNumber() {
    throw new SpeedMarkerException("Not a number");
  }

  @Override
  public Boolean asBoolean() {
    throw new SpeedMarkerException("Not a boolean");
  }

  @Override
  public ZonedDateTime asDate() {
    throw new SpeedMarkerException("Not a date");
  }

  @Override
  public Value getHashValue(Object key) {
    throw new SpeedMarkerException("Not a hash");
  }

  @Override
  public List<Object> asSequence() {
    throw new SpeedMarkerException("Not a sequence");
  }

  @Override
  public Collection<Object> asCollection() {
    throw new SpeedMarkerException("Not a collection");
  }

  @Override
  public Value invokeMethod(String name, Value... parameter) {
    throw new SpeedMarkerException("Not invokable");
  }

}
