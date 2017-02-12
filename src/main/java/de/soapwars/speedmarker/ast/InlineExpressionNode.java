package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

/**
 * Created by ptriller on 12.02.2017.
 */
public class InlineExpressionNode implements Node {

  @Override
  public String debugTree() {
    return "${ ..... }";
  }
}
