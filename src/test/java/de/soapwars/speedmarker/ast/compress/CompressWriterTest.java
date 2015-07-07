package de.soapwars.speedmarker.ast.compress;


import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

public class CompressWriterTest {


  @Test
  public void testStuff() throws Exception {
    final StringWriter stringWriter = new StringWriter();
    final CompressWriter writer = new CompressWriter(stringWriter);
    writer.write("  \n  Mann    ich   \n  bin   cool !!  ");
    writer.close();
    Assert.assertEquals("Mann ich\nbin cool !!",stringWriter.toString());
  }
}