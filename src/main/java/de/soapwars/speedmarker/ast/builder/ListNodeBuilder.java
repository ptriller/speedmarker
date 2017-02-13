package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.ast.ListNode;
import de.soapwars.speedmarker.ast.ParseState;

/**
 * Created by ptriller on 12.02.2017.
 */
public class ListNodeBuilder {

  private Node expression;

  public static ListNodeBuilder create(ParseState state) {
    return new ListNodeBuilder();
  }

  public void expression(Node expression) {
    this.expression = expression;
  }

  public ListNode build() {
    return new ListNode();
  }
}
