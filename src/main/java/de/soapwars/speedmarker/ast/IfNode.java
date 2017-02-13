package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;
import javafx.application.ConditionalFeature;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.locks.Condition;

/**
 * Created by ptriller on 12.02.2017.
 */
public class IfNode implements Node {

  public static class Conditional {
    Node condition;
    Node body;

    public Conditional(Node condition, Node body) {
      this.condition = condition;
      this.body = body;
    }
  }

  @Nonnull
  private final List<Conditional> blocks;

  public IfNode(@Nonnull List<Conditional> blocks) {
    this.blocks = Objects.requireNonNull(blocks);
  }

  @Override
  public String debugTree() {
    StringBuilder builder = new StringBuilder("[ 'if' ");
    for (Conditional block : blocks) {
      builder.append(", ");
      builder.append(block.condition == null ? "ELSE" : block.condition.debugTree());
      builder.append("->");
      builder.append(block.body.debugTree());
    }
    builder.append("]");
    return builder.toString();
  }
}
