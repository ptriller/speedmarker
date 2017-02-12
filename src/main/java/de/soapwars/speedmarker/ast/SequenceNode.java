package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import javax.annotation.Nonnull;
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

  public String debugTree() {
    return nodes.stream().map(Node::debugTree).collect(Collectors.joining(",", "(", ")"));
  }
}
