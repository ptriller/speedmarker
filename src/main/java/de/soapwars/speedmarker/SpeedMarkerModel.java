package de.soapwars.speedmarker;

import de.soapwars.speedmarker.valaue.BooleanValue;
import de.soapwars.speedmarker.valaue.CollectionValue;
import de.soapwars.speedmarker.valaue.NumberValue;
import de.soapwars.speedmarker.valaue.SequenceValue;
import de.soapwars.speedmarker.valaue.StringValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ptriller on 26.02.2017.
 */
public class SpeedMarkerModel {

  private final Map<String, Object> model;

  private Map<String, Value> valueMap = new HashMap<>();

  public SpeedMarkerModel(Map<String, Object> model) {
    this.model = model;
  }

  public SpeedMarkerModel() {
    model = new HashMap<>();
  }

  public void put(String name, Object value) {
    model.put(name, value);
  }

  public void putValue(String name, Value value) {
    valueMap.put(name, value);
  }

  public Value get(String name) {
    return valueMap.computeIfAbsent(name, this::convertModel);
  }

  private Value convertModel(String name) {
    Object val = model.get(name);
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

  public void add(Map<String, Object> param) {
    model.putAll(param);
  }
}
