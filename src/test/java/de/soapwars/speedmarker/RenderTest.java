package de.soapwars.speedmarker;

import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by ptriller on 26.02.2017.
 */
public class RenderTest {

  SpeedMarker testee;

  @Before
  public void setUp() {
    testee = new SpeedMarkerBuilder().build();
  }

  @Test
  public void testConstantRender() throws Exception {
    StringWriter writer = new StringWriter();
    SpeedMarkerModel model = new SpeedMarkerModel();
    model.put("test", "suppe");
    testee.render("/render/test1.ftl", writer, model);
    writer.close();
    System.out.printf(writer.toString());
  }

}
