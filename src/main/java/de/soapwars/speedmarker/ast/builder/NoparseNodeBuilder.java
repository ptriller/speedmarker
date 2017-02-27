package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Token;
import de.soapwars.speedmarker.ast.node.ContentNode;

/**
 * Created by ptriller on 12.02.2017.
 */
public class NoparseNodeBuilder {

  private StringBuilder builder = new StringBuilder();

  public void content(Token content) {
    builder.append(content.image);
  }

  public ContentNode build() {
    return new ContentNode(builder.toString());
  }

}
