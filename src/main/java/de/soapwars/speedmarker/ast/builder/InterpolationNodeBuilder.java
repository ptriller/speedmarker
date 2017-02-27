package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.ast.node.InterpolationNode;

/**
 * Created by ptriller on 26.02.2017.
 */
public class InterpolationNodeBuilder {

  private Expression expression;

  public void expression(Expression expression) {
    this.expression = expression;
  }

  public InterpolationNode build() {
    return new InterpolationNode(expression);
  }
}
