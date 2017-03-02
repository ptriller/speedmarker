package de.soapwars.speedmarker;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * Created by ptriller on 26.02.2017.
 */
public class SpeedMarkerModel {

  private Filter filter;

  private Deque<Scope> scopes = new ArrayDeque<>();

  public SpeedMarkerModel(Map<String, Object> model) {
    scopes.push(new GlobalScope(model));
  }

  public SpeedMarkerModel() {
    scopes.push(new GlobalScope());
  }


  public void put(String name, Value value) {
    scopes.peek().put(name, value);
  }

  public void putGlobal(String name, Value value) {
    scopes.peek().putGlobal(name, value);
  }

  public void pushScope() {
    scopes.push(new LocalScope(scopes.peek()));
  }

  public void popScope() {
    scopes.pop();
  }

  public Value get(String name) {
    return scopes.peek().get(name);
  }

  public Filter updateEscaper(Filter filter) {
    Filter oldFilter = this.filter;
    this.filter = filter;
    return oldFilter;
  }

  public Value escape(Value value) {
    return filter == null ? value : filter.filter(this, value);
  }
}
