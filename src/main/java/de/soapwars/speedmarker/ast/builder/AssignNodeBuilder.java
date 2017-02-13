package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.ast.AssignNode;
import de.soapwars.speedmarker.ast.ParseState;

/**
 * Created by ptriller on 13.02.2017.
 */
public class AssignNodeBuilder {

  private String scope;

  private String name;

  private Node value;

  public static AssignNodeBuilder create(ParseState state) {
    return new AssignNodeBuilder();
  }

  public void scope(String scope) {
    this.scope = scope;
  }

  public void name(String name) {
    this.name = name;
  }

  public void value(Node value) {
    this.value = value;
  }

  public AssignNode build() {
    return new AssignNode(name, scope, value);
  }
}
