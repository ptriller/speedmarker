package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.ast.node.CompressNode;

/**
 * Created by ptriller on 13.02.2017.
 */
public class CompressNodeBuilder {

  private Node content;

  public void content(Node content) {
    this.content = content;
  }

  public CompressNode build() {
    return new CompressNode(content);
  }

}
