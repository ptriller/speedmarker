package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import javax.annotation.Nonnull;

/**
 * Created by ptriller on 12.02.2017.
 */
public class ImportNode implements Node {

  @Nonnull
  private String hash;

  @Nonnull
  private Node path;

  public ImportNode(@Nonnull String hash, @Nonnull Node path) {
    this.hash = hash;
    this.path = path;
  }

  @Override
  public String debugTree() {
    return "[ 'import', " + path.debugTree() + ", " + hash + "]";
  }
}
