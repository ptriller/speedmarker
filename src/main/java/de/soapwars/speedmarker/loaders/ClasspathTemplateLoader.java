package de.soapwars.speedmarker.loaders;

import de.soapwars.speedmarker.TemplateLoader;

import java.io.InputStream;
import java.net.URI;

/**
 * Created by ptriller on 25.02.2017.
 */
public class ClasspathTemplateLoader implements TemplateLoader {

  @Override
  public InputStream loadTeamplate(URI location) {
    return ClasspathTemplateLoader.class.getResourceAsStream(location.getPath());
  }

}
