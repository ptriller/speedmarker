package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.ast.ContentNode;
import de.soapwars.speedmarker.ast.SequenceNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ptriller on 11.02.2017.
 */
public class SequenceNodeBuilder {

  private final List<Node> nodes = new ArrayList<>();

  private StringBuilder contentBuilder = null;

  public void add(Node node) {
    if (node instanceof ContentNode) {
      if (contentBuilder == null) {
        contentBuilder = new StringBuilder(((ContentNode) node).getContent());
      } else {
        contentBuilder.append(((ContentNode) node).getContent());
      }
    } else {
      if (contentBuilder != null) {
        nodes.add(new ContentNode(contentBuilder.toString()));
        contentBuilder = null;
      }
      nodes.add(node);
    }
  }

  public SequenceNode build() {
    if (contentBuilder != null) {
      nodes.add(new ContentNode(contentBuilder.toString()));
    }
    return new SequenceNode(nodes);
  }
}
