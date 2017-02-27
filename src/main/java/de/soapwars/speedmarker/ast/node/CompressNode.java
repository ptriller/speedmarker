package de.soapwars.speedmarker.ast.node;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.SpeedMarkerModel;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.Writer;
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
  public boolean isStatic() {
    return content.isStatic();
  }

  @Override
  public void render(Writer writer, SpeedMarkerModel model) throws IOException {
    // TODO
    content.render(writer, model);
  }

  @Override
  public String debugTree() {
    return "[ 'compress', " + content.debugTree() + " ]";
  }
}
