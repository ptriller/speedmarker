package de.soapwars.speedmarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.junit.Test;

import java.io.StringWriter;
import java.util.HashMap;

/**
 * Created by ptriller on 30.06.2015.
 */
public class FreeMarkerTest {

    @Test
    public void testFree() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("test", Boolean.TRUE);
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setClassForTemplateLoading(this.getClass(), "/");
        Template template = cfg.getTemplate("/ftl/test.ftl");
        StringWriter w = new StringWriter();
        template.process(map, w);
        System.out.println(w.toString());
    }
}
