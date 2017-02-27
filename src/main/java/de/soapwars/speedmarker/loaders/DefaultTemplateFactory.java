package de.soapwars.speedmarker.loaders;

import de.soapwars.speedmarker.*;
import de.soapwars.speedmarker.ast.NodeBuilderFactory;
import de.soapwars.speedmarker.ast.ParseState;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by ptriller on 26.02.2017.
 */
public class DefaultTemplateFactory implements TemplateFactory {

  @Nonnull
  private final NodeBuilderFactory nodeBuilderFactory;

  @Nonnull
  private final Charset charset;

  public DefaultTemplateFactory(@Nonnull NodeBuilderFactory nodeBuilderFactory, Charset charset) {
    this.nodeBuilderFactory = nodeBuilderFactory;
    this.charset = charset;
  }

  public DefaultTemplateFactory(@Nonnull NodeBuilderFactory nodeBuilderFactory) {
    this.nodeBuilderFactory = nodeBuilderFactory;
    this.charset = StandardCharsets.UTF_8;
  }

  @Override
  public Template parseTemplate(URI uri, InputStream stream) {
    ParseState state = new ParseState();
    SpeedMarkerParser parser = new SpeedMarkerParser(nodeBuilderFactory, new InputStreamReader(stream, charset));
    try {
      Node start = parser.start(state);
      return new Template(uri, start);
    } catch (ParseException e) {
      throw new SpeedMarkerException("Error parsing the template", e);
    }
  }
}
