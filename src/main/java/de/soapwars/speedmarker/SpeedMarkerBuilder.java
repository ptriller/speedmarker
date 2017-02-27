package de.soapwars.speedmarker;

import de.soapwars.speedmarker.ast.NodeBuilderFactory;
import de.soapwars.speedmarker.loaders.ClasspathTemplateLoader;
import de.soapwars.speedmarker.loaders.DefaultTemplateFactory;
import de.soapwars.speedmarker.loaders.DefaultTemplateRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ptriller on 25.02.2017.
 */
public class SpeedMarkerBuilder {

  private List<TemplateLoader> loaders = null;

  private NodeBuilderFactory nodeBuilderFactory;

  private TemplateRepository templateRepository;

  private TemplateFactory templateFactory;

  private int cacheSize;

  private List<TemplateLoader> templateLoaders;

  public SpeedMarkerBuilder addTemplateLoader(TemplateLoader loader) {
    if (loaders == null) {
      loaders = new ArrayList<>();
    }
    loaders.add(loader);
    return this;
  }

  public SpeedMarkerBuilder setCacheSize(int cacheSize) {
    this.cacheSize = cacheSize;
    return this;
  }

  private NodeBuilderFactory getNodeBuilderFactory() {
    return nodeBuilderFactory == null ? new DefaultNodeBuilderFactory() : nodeBuilderFactory;
  }

  private List<TemplateLoader> getTemplateLoaders() {
    return loaders == null ? Collections.singletonList(new ClasspathTemplateLoader()) : loaders;
  }

  public SpeedMarkerBuilder setTemplateLoaders(List<TemplateLoader> loaders) {
    this.loaders = loaders;
    return this;
  }

  private TemplateRepository getTemplateRepository() {
    TemplateRepository repo = templateRepository == null ? new DefaultTemplateRepository(cacheSize) : templateRepository;
    repo.setTemplateFactory(getTemplateFactory());
    repo.setTemplateLoaders(getTemplateLoaders());
    return repo;
  }

  public SpeedMarkerBuilder setTemplateRepository(TemplateRepository templateRepository) {
    this.templateRepository = templateRepository;
    return this;
  }

  public TemplateFactory getTemplateFactory() {
    return templateFactory == null ? new DefaultTemplateFactory(getNodeBuilderFactory()) : templateFactory;
  }


  public SpeedMarker build() {
    return new SpeedMarker(getTemplateRepository());
  }
}
