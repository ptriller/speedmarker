package de.soapwars.speedmarker.ast.node;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.SpeedMarkerModel;

import javax.annotation.Nonnull;
import java.io.Writer;
import java.util.Objects;

/**
 * Created by ptriller on 11.02.2017.
 */
public class CommentNode implements Node {

  @Nonnull
  private final String comment;

  public CommentNode(@Nonnull String comment) {
    this.comment = Objects.requireNonNull(comment);
  }

  @Override
  public boolean isStatic() {
    return true;
  }

  @Override
  public void render(Writer writer, SpeedMarkerModel model) {
  }

  @Override
  public String debugTree() {
    return "<#--" + comment + "-->";
  }
}
