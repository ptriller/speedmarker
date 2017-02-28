package de.soapwars.speedmarker;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by ptriller on 25.02.2017.
 */
public interface Value {

  ValueType getPrimaryType();

  boolean isType(ValueType type);

  String asString();

  Number asNumber();

  Boolean asBoolean();

  ZonedDateTime asDate();

  Value getHashValue(Object key);

  List<Object> asSequence();

  Collection<Object> asCollection();

  Value invokeMethod(String name, Value ...parameter);
}
