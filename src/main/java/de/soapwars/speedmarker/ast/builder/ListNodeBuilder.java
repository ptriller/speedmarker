package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.ast.node.ListNode;

/**
 * Created by ptriller on 12.02.2017.
 */
public class ListNodeBuilder {

  private Expression expression;

  private String varName;

  private Node prefixNode;

  private Node loopNode;

  private Node sepNode;

  private Node postSepNode;

  private Node postfixNode;

  private Node elseNode;

  public void expression(Expression expression) {
    this.expression = expression;
  }

  public void prefix(Node node) {
    this.postfixNode = node;
  }

  public void loop(Node node) {
    this.loopNode = node;
  }

  public void sep(Node node) {
    this.sepNode = node;
  }

  public void postSep(Node node) {
    this.postSepNode = node;
  }

  public void postfix(Node node) {
    this.postfixNode = node;
  }

  public ListNode build() {
    return new ListNode();
  }
}
