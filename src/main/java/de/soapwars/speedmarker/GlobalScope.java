package de.soapwars.speedmarker;

import de.soapwars.speedmarker.valaue.*;

import java.util.*;

/**
 * Created by ptriller on 02.03.2017.
 */
public class GlobalScope implements Scope {

  private Map<String, Object> external = Collections.emptyMap();

  private Map<String, Value> data = new HashMap<>();

  public GlobalScope() {
  }

  public GlobalScope(Map<String, Object> external) {
    this.external = external;
  }

  @Override
  public Value get(String name) {
    return data.computeIfAbsent(name, this::convertModel);
  }

  @Override
  public void put(String name, Value value) {
    data.put(name, value);
  }

  @Override
  public void putGlobal(String name, Value value) {
    data.put(name, value);
  }

  @SuppressWarnings("unchecked")
  private Value convertModel(String name) {
    Object val = external.get(name);
    if (val instanceof String) {
      return new StringValue((String) val);
    } else if (val instanceof Boolean) {
      return new BooleanValue((Boolean) val);
    } else if (val instanceof Number) {
      return new NumberValue((Number) val);
    } else if (val instanceof List) {
      return new SequenceValue((List<Object>) val);
    } else if (val instanceof Collection) {
      return new CollectionValue((Collection<Object>) val);
    } else if (val instanceof Map) {
      // TODO HASH VALUE
      return null;
    }
    // TODO BEAN VALUE
    return null;
  }

}
