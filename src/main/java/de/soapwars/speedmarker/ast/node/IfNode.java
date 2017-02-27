package de.soapwars.speedmarker.ast.node;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.SpeedMarkerModel;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Objects;

/**
 * Created by ptriller on 12.02.2017.
 */
public class IfNode implements Node {

  @Nonnull
  private final List<Conditional> blocks;

  public IfNode(@Nonnull List<Conditional> blocks) {
    this.blocks = Objects.requireNonNull(blocks);
  }

  @Override
  public boolean isStatic() {
    return blocks.stream().allMatch(Conditional::isStatic);
  }

  @Override
  public void render(Writer writer, SpeedMarkerModel model) throws IOException {
    // TODO
  }

  @Override
  public String debugTree() {
    StringBuilder builder = new StringBuilder("[ 'if' ");
    for (Conditional block : blocks) {
      builder.append(", ");
      builder.append(block.condition == null ? "ELSE" : "{" + block.condition.debugTree() + "}");
      builder.append("->");
      builder.append(block.body.debugTree());
    }
    builder.append("]");
    return builder.toString();
  }

  public static class Conditional {
    Expression condition;
    Node body;

    public Conditional(Expression condition, Node body) {
      this.condition = condition;
      this.body = body;
    }

    public boolean isStatic() {
      return condition.isStatic() && body.isStatic();
    }
  }
}
