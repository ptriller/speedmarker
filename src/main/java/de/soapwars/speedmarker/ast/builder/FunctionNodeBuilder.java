package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.Token;
import de.soapwars.speedmarker.ast.FunctionNode;
import de.soapwars.speedmarker.ast.ParseState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ptriller on 12.02.2017.
 */
public class FunctionNodeBuilder {

  private String name;

  private String paramName;

  private List<FunctionNode.FunctionParam> params = new ArrayList<>();

  private Node body;

  public static FunctionNodeBuilder create(ParseState state) {
    return new FunctionNodeBuilder();
  }

  public void name(String name) {
    this.name = name;
  }

  public void param(String param) {
    resolveParam(null);
    paramName = param;
  }

  public void def(Node expression) {
    resolveParam(expression);
  }

  public void body(Node body) {
    this.body = body;
  }

  private void resolveParam(Node expression) {
    if (paramName != null) {
      params.add(new FunctionNode.FunctionParam(paramName, expression));
      paramName = null;
    }
  }

  public FunctionNode build() {
    resolveParam(null);
    return new FunctionNode(name, params, body);
  }
}
