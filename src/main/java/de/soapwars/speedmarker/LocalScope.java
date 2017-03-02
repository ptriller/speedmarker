package de.soapwars.speedmarker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ptriller on 02.03.2017.
 */
public class LocalScope implements Scope {

  private final Map<String, Value> data = new HashMap<>();

  private final Scope parent;

  public LocalScope(Scope parent) {
    this.parent = parent;
  }

  @Override
  public Value get(String name) {
    Value result = data.get(name);
    if (result == null) {
      return parent.get(name);
    }
    return result;
  }

  @Override
  public void put(String name, Value value) {
    data.put(name, value);
  }

  @Override
  public void putGlobal(String name, Value value) {
    parent.putGlobal(name, value);
  }
}
