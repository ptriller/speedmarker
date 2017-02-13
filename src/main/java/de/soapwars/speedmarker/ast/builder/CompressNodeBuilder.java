package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.ast.CompressNode;
import de.soapwars.speedmarker.ast.ParseState;

/**
 * Created by ptriller on 13.02.2017.
 */
public class CompressNodeBuilder {

  private Node content;

  public static CompressNodeBuilder create(ParseState state) {
    return new CompressNodeBuilder();
  }

  public void content(Node content) {
    this.content = content;
  }

  public CompressNode build() {
    return new CompressNode(content);
  }

}
