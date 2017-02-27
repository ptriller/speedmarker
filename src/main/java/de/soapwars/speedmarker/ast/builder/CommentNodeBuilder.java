package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.ast.node.CommentNode;

/**
 * Created by ptriller on 11.02.2017.
 */
public class CommentNodeBuilder {

  private final StringBuilder builder = new StringBuilder();

  public void add(String comment) {
    builder.append(comment);
  }

  public CommentNode build() {
    return new CommentNode(builder.toString());
  }
}
