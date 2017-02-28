package de.soapwars.speedmarker;

import javax.annotation.Nonnull;
import java.io.Writer;
import java.net.URI;
import java.util.Map;

/**
 * Created by ptriller on 25.02.2017.
 */
public class SpeedMarker {

  @Nonnull
  private final TemplateRepository repository;

  private URI baseURI = URI.create("template:/");

  public SpeedMarker(@Nonnull TemplateRepository repository) {
    this.repository = repository;
  }


  public void render(String path, Writer output, Map<String, Object> model) {
    URI uri = baseURI.resolve(path);
    Template template = repository.getTemplate(uri);
    template.render(output, buildModel(model));
  }

  private SpeedMarkerModel buildModel(Map<String, Object> model) {
    return new SpeedMarkerModel(model);
  }
}
