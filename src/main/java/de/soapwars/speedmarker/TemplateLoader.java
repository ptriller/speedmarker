package de.soapwars.speedmarker;

import java.io.InputStream;
import java.net.URI;

/**
 * Created by ptriller on 25.02.2017.
 */
public interface TemplateLoader {

  InputStream loadTeamplate(URI location);

}
