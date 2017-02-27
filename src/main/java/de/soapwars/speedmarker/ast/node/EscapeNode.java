package de.soapwars.speedmarker.ast.node;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.SpeedMarkerModel;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by ptriller on 12.02.2017.
 */
public class EscapeNode implements Node {

  private String varName;

  private Expression expression;

  private Node content;

  public EscapeNode(String varName, Expression expression, Node content) {
    this.varName = varName;
    this.expression = expression;
    this.content = content;
  }

  /**
   * No idea how to check the escape expression easily...
   *
   * @return
   */
  @Override
  public boolean isStatic() {
    return false;
  }

  @Override
  public void render(Writer writer, SpeedMarkerModel model) throws IOException {
    // TODO Push escaper
    content.render(writer, model);
  }

  @Override
  public String debugTree() {
    return "[ 'escape', " + varName + ", {" + expression.debugTree() + "}, "
        + content.debugTree() + "]";
  }
}
