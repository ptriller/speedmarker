package de.soapwars.speedmarker;

import java.net.URI;
import java.util.List;

/**
 * Created by ptriller on 26.02.2017.
 */
public interface TemplateRepository {

  void setTemplateFactory(TemplateFactory factory);

  void setTemplateLoaders(List<TemplateLoader> loaders);

  Template getTemplate(URI location);

}
