package de.soapwars.speedmarker;

/**
 * Created by ptriller on 02.03.2017.
 */
public interface Scope {

  Value get(String name);

  void put(String name, Value value);

  void putGlobal(String name, Value value);
}
