package de.soapwars.speedmarker.ast.node;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.SpeedMarkerModel;

import java.io.Writer;
import java.util.List;

/**
 * Created by ptriller on 12.02.2017.
 */
public class FunctionNode implements Node {

  private String name;
  private List<FunctionParam> parameter;
  private Node body;

  public FunctionNode(String name, List<FunctionParam> parameter, Node body) {
    this.name = name;
    this.parameter = parameter;
    this.body = body;
  }

  @Override
  public boolean isStatic() {
    return true;
  }

  @Override
  public void render(Writer writer, SpeedMarkerModel model) {
  }

  @Override
  public String debugTree() {
    StringBuilder builder = new StringBuilder("[ 'function', <");
    builder.append(name);
    builder.append(">, ");
    for (FunctionParam functionParam : parameter) {
      builder.append(functionParam.name);
      if (functionParam.defValue != null) {
        builder.append("={");
        builder.append(functionParam.defValue.debugTree());
        builder.append('}');
      }
      builder.append(", ");
    }
    builder.append(body.debugTree());
    builder.append("]");
    return builder.toString();
  }

  public static class FunctionParam {
    String name;
    Expression defValue;

    public FunctionParam(String name, Expression defValue) {
      this.name = name;
      this.defValue = defValue;
    }
  }
}
