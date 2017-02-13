package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Created by ptriller on 12.02.2017.
 */
public class CompressNode implements Node {

  @Nonnull
  private Node content;

  public CompressNode(@Nonnull Node content) {
    this.content = Objects.requireNonNull(content);
  }

  @Override
  public String debugTree() {
    return "[ 'compress', " + content.debugTree() + " ]";
  }
}
