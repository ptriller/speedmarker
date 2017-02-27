package de.soapwars.speedmarker.loaders;

import de.soapwars.speedmarker.*;
import de.soapwars.speedmarker.util.LRUCache;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Simple caching Template Repository. No Reload, also caches "Not found"
 * Caching is not really optimized for multi threads but works
 * Created by ptriller on 26.02.2017.
 */
public class DefaultTemplateRepository implements TemplateRepository {

  @Nonnull
  private final Map<URI, Optional<Template>> cache;
  @Nonnull
  private TemplateFactory templateFactory;
  @Nonnull
  private List<TemplateLoader> loaders;

  public DefaultTemplateRepository(int cacheSize) {
    this.cache = Collections.synchronizedMap(new LRUCache<>(cacheSize));
  }

  @Override
  public Template getTemplate(URI location) {
    return cache.computeIfAbsent(location, this::computeTemplate).orElse(null);
  }

  private Optional<Template> computeTemplate(URI location) {
    for (TemplateLoader loader : loaders) {
      try (InputStream stream = loader.loadTeamplate(location)) {
        if (stream != null) {
          return Optional.of(templateFactory.parseTemplate(location, stream));
        }
      } catch (IOException ex) {
        throw new SpeedMarkerException("Problem loading Template " + location.toASCIIString(), ex);
      }
    }
    return Optional.empty();
  }

  @Override
  public void setTemplateFactory(TemplateFactory factory) {
    this.templateFactory = factory;
  }

  @Override
  public void setTemplateLoaders(List<TemplateLoader> loaders) {
    this.loaders = loaders;
  }
}
