/*
 * Copyright (c) 2017 1&1 Mail & Media GmbH, Muenchen. All rights reserved.
 */
package de.soapwars.speedmarker;

import org.junit.Test;

import java.util.Collections;
import java.util.Map;

/**
 * @author ptriller
 */
public class MarkerCompareTest extends RenderTestBase {

  @Test
  public void testInterpolation() throws Exception {
    compareOutput("/render/interpolation.ftl",
        Collections.singletonMap("test", "interpolation"));
  }

  @Test
  public void testIf() throws Exception {
    compareOutput("/render/if.ftl",
        mapOf("test", Boolean.TRUE));
    compareOutput("/render/if.ftl",
        mapOf("test", Boolean.FALSE, "test2", Boolean.TRUE));
    compareOutput("/render/if.ftl",
        mapOf("test", Boolean.FALSE, "test2", Boolean.FALSE));
  }
}
