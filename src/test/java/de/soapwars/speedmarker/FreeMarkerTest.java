package de.soapwars.speedmarker;

import com.google.common.collect.ImmutableMap;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by ptriller on 30.06.2015.
 */
public class FreeMarkerTest {

    @Test
    public void testFree() throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setClassForTemplateLoading(this.getClass(), "/");
        Template template = cfg.getTemplate("/ftl/test.ftl");
        StringWriter w = new StringWriter();
        template.process(ImmutableMap.of("test", Boolean.TRUE), w);
        System.out.println(w.toString());
    }
}
