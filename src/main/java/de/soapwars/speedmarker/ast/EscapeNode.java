package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

/**
 * Created by ptriller on 12.02.2017.
 */
public class EscapeNode implements Node {

  private String varName;

  private Node expression;

  private Node content;

  public EscapeNode(String varName, Node expression, Node content) {
    this.varName = varName;
    this.expression = expression;
    this.content = content;
  }

  @Override
  public String debugTree() {
    return "[ 'escape', " + varName + ", " + expression.debugTree() + ", "
        + content.debugTree() + "]";
  }
}
