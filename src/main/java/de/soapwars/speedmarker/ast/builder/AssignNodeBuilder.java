package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.ast.expression.NodeExpression;
import de.soapwars.speedmarker.ast.node.AssignNode;

/**
 * Created by ptriller on 13.02.2017.
 */
public class AssignNodeBuilder {

  private String scope;

  private String name;

  private Expression value;

  public void scope(String scope) {
    this.scope = scope;
  }

  public void name(String name) {
    this.name = name;
  }

  public void value(Node value) {
    this.value = new NodeExpression(value);
  }

  public void value(Expression value) {
    this.value = value;
  }

  public AssignNode build() {
    return new AssignNode(name, scope, value);
  }
}
