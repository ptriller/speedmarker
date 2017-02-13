package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Token;
import de.soapwars.speedmarker.ast.ContentNode;
import de.soapwars.speedmarker.ast.ParseState;

/**
 * Created by ptriller on 12.02.2017.
 */
public class ContentNodeBuilder {

  private String content;

  public static ContentNodeBuilder create(ParseState state) {
    return new ContentNodeBuilder();
  }

  public void add(Token token) {
    this.content = token.image;
  }

  public ContentNode build() {
    return new ContentNode(content);
  }
}
