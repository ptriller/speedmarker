package de.soapwars.speedmarker.ast.node;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.SpeedMarkerModel;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

/**
 * Created by ptriller on 11.02.2017.
 */
public class ContentNode implements Node {

  @Nonnull
  private final String content;

  public ContentNode(@Nonnull String content) {
    this.content = Objects.requireNonNull(content);
  }

  @Nonnull
  public String getContent() {
    return content;
  }

  @Override
  public boolean isStatic() {
    return true;
  }

  @Override
  public void render(Writer writer, SpeedMarkerModel model) throws IOException {
    writer.append(content);
  }

  @Override
  public String debugTree() {
    return "\"" + content + "\"";
  }
}
