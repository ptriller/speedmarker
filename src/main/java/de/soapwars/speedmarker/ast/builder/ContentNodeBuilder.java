package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Token;
import de.soapwars.speedmarker.ast.node.ContentNode;

/**
 * Created by ptriller on 12.02.2017.
 */
public class ContentNodeBuilder {

  private String content;

  public void add(Token token) {
    this.content = token.image;
  }

  public ContentNode build() {
    return new ContentNode(content);
  }
}
