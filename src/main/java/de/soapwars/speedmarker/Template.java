package de.soapwars.speedmarker;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;

/**
 * Created by ptriller on 26.02.2017.
 */
public class Template {

  @Nonnull
  private URI uri;

  @Nonnull
  private Node node;

  public Template(@Nonnull URI uri, @Nonnull Node node) {
    this.uri = uri;
    this.node = node;
  }

  public void render(Writer writer, SpeedMarkerModel model) {
    try {
      node.render(writer, model);
    } catch (IOException e) {
      throw new SpeedMarkerException(e);
    }
  }

  @Nonnull
  public URI getUri() {
    return uri;
  }
}
