package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.ast.IfNode;
import de.soapwars.speedmarker.ast.ParseState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ptriller on 12.02.2017.
 */
public class IfNodeBuilder {

  private List<IfNode.Conditional> conditionals = new ArrayList<>();

  public static IfNodeBuilder create(ParseState state) {
    return new IfNodeBuilder();
  }

  public void add(Node condition, Node body) {
    conditionals.add(new IfNode.Conditional(condition, body));
  }

  public IfNode build() {
    return new IfNode(conditionals);
  }
}
