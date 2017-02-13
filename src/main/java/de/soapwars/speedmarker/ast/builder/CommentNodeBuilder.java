package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.ast.CommentNode;
import de.soapwars.speedmarker.ast.ParseState;

/**
 * Created by ptriller on 11.02.2017.
 */
public class CommentNodeBuilder {

  private final StringBuilder builder = new StringBuilder();

  public static CommentNodeBuilder create(ParseState state) {
    return new CommentNodeBuilder();
  }

  public void add(String comment) {
    builder.append(comment);
  }

  public CommentNode build() {
    return new CommentNode(builder.toString());
  }
}
