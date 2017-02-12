package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Created by ptriller on 11.02.2017.
 */
public class CommentNode implements Node{

  @Nonnull
  private final String comment;

  public CommentNode(@Nonnull String comment) {
    this.comment = Objects.requireNonNull(comment);
  }

  @Override
  public String debugTree() {
    return "<#--" + comment + "-->";
  }
}
