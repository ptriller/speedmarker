package de.soapwars.speedmarker.ast.node;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.SpeedMarkerModel;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by ptriller on 12.02.2017.
 */
public class InterpolationNode implements Node {

  private Expression expression;

  public InterpolationNode(Expression expression) {
    this.expression = expression;
  }

  @Override
  public boolean isStatic() {
    return expression.isStatic();
  }

  @Override
  public void render(Writer writer, SpeedMarkerModel model) throws IOException {
    writer.write(expression.getValue(model).getValue().toString());
  }

  @Override
  public String debugTree() {
    return "${" + expression.debugTree() + "}";
  }
}
