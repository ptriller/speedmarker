package de.soapwars.speedmarker.ast.node;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.SpeedMarkerModel;

import javax.annotation.Nonnull;
import java.io.Writer;

/**
 * Created by ptriller on 12.02.2017.
 */
public class ImportNode implements Node {

  @Nonnull
  private String hash;

  @Nonnull
  private Expression path;

  public ImportNode(@Nonnull String hash, @Nonnull Expression path) {
    this.hash = hash;
    this.path = path;
  }

  @Override
  public boolean isStatic() {
    return false;
  }

  @Override
  public void render(Writer writer, SpeedMarkerModel model) {
  }

  @Override
  public String debugTree() {
    return "[ 'import', {" + path.debugTree() + "}, " + hash + "]";
  }
}
