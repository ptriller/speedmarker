/*
 * Copyright (c) 2017 1&1 Mail & Media GmbH, Muenchen. All rights reserved.
 */
package de.soapwars.speedmarker;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Assert;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ptriller
 */
public class RenderTestBase {


  protected void compareOutput(String templatePath, Map<String, Object> model) throws Exception {
    Assert.assertEquals(renderFreemarker(templatePath, model), renderSpeedMarker(templatePath, model));
  }

  protected String renderFreemarker(String templatePath, Map<String, Object> model) throws Exception {
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
    cfg.setTemplateLoader(new ClassTemplateLoader(this.getClass().getClassLoader(), ""));
    Template template = cfg.getTemplate(templatePath);
    StringWriter writer = new StringWriter();
    template.process(model, writer);
    writer.close();
    return writer.toString();
  }

  protected String renderSpeedMarker(String templatePath, Map<String, Object> model) throws Exception {
    SpeedMarker speedMarker = new SpeedMarkerBuilder().build();
    StringWriter writer = new StringWriter();
    speedMarker.render(templatePath, writer, model);
    writer.close();
    return writer.toString();
  }

  protected Map<String, Object> mapOf(Object... params) {
    HashMap<String, Object> map = new HashMap<>();
    for (int i = 0; i < params.length; i += 2) {
      map.put((String) params[i], params[i + 1]);
    }
    return map;
  }
}
