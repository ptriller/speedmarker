package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.ast.node.IfNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ptriller on 12.02.2017.
 */
public class IfNodeBuilder {

  private List<IfNode.Conditional> conditionals = new ArrayList<>();

  public void add(Expression condition, Node body) {
    conditionals.add(new IfNode.Conditional(condition, body));
  }

  public IfNode build() {
    return new IfNode(conditionals);
  }
}
