package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Token;
import de.soapwars.speedmarker.ast.ContentNode;
import de.soapwars.speedmarker.ast.ParseState;

/**
 * Created by ptriller on 12.02.2017.
 */
public class NoparseNodeBuilder {

  private StringBuilder builder = new StringBuilder();

  public static NoparseNodeBuilder create(ParseState state) {
    return new NoparseNodeBuilder();
  }

  public void content(Token content) {
    builder.append(content.image);
  }

  public ContentNode build() {
    return new ContentNode(builder.toString());
  }

}
