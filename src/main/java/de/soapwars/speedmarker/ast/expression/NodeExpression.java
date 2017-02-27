package de.soapwars.speedmarker.ast.expression;

import de.soapwars.speedmarker.*;
import de.soapwars.speedmarker.valaue.StringValue;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by ptriller on 25.02.2017.
 */
public class NodeExpression implements Expression {

  private Node value;

  public NodeExpression(Node value) {
    this.value = value;
  }

  @Override
  public boolean isStatic() {
    return value.isStatic();
  }

  @Override
  public Value getValue(SpeedMarkerModel model) {
    try {
      StringWriter out = new StringWriter();
      value.render(out, model);
      out.close();
      return new StringValue(out.toString());
    } catch (IOException e) {
      throw new SpeedMarkerException(e);
    }
  }

  @Override
  public String debugTree() {
    return "< 'render', " + value.debugTree() + '>';
  }
}
