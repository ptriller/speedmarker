package de.soapwars.speedmarker;

import java.io.InputStream;
import java.net.URI;

/**
 * Created by ptriller on 26.02.2017.
 */
public interface TemplateFactory {

  Template parseTemplate(URI uri, InputStream stream);
}
