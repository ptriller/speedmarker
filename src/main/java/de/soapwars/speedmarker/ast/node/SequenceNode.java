package de.soapwars.speedmarker.ast.node;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.SpeedMarkerModel;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by ptriller on 11.02.2017.
 */
public class SequenceNode implements Node {

  @Nonnull
  private final List<Node> nodes;

  public SequenceNode(@Nonnull List<Node> nodes) {
    this.nodes = Objects.requireNonNull(nodes);
  }

  @Override
  public boolean isStatic() {
    return nodes.stream().allMatch(Node::isStatic);
  }

  @Override
  public void render(Writer writer, SpeedMarkerModel model) throws IOException {
    for (Node node : nodes) {
      node.render(writer, model);
    }
  }

  public String debugTree() {
    return nodes.stream().map(Node::debugTree).collect(Collectors.joining(",", "(", ")"));
  }
}
