package de.soapwars.speedmarker.ast.node;

import de.soapwars.speedmarker.*;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by ptriller on 12.02.2017.
 */
public class EscapeNode implements Filter, Node {

  private String varName;

  private Expression expression;

  private Node content;

  public EscapeNode(String varName, Expression expression, Node content) {
    this.varName = varName;
    this.expression = expression;
    this.content = content;
  }

  /**
   * No idea how to check the filter expression easily...
   *
   * @return
   */
  @Override
  public boolean isStatic() {
    return false;
  }

  @Override
  public void render(Writer writer, SpeedMarkerModel model) throws IOException {
    Filter old = model.updateEscaper(this);
    try {
      content.render(writer, model);
    } finally {
      model.updateEscaper(old);
    }
  }

  @Override
  public String debugTree() {
    return "[ 'escape', " + varName + ", {" + expression.debugTree() + "}, "
        + content.debugTree() + "]";
  }

  @Override
  public Value filter(SpeedMarkerModel model, Value value) {
    model.pushScope();
    try {
      model.put(varName, value);
      return expression.getValue(model);
    } finally {
      model.popScope();
    }
  }
}
