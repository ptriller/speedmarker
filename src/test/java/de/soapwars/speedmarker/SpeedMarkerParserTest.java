package de.soapwars.speedmarker;

import com.google.common.escape.Escaper;
import de.soapwars.speedmarker.ast.Node;
import org.junit.Test;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

/**
 * Created by ptriller on 30.06.2015.
 */
public class SpeedMarkerParserTest {

   @Test
   public void testExpressionInContent() throws Exception {
      SpeedMarkerParser parser = new SpeedMarkerParser(new StringReader("<hallo>${peter}</hallo>"));
      Node node = parser.Start();
      System.out.println(node);
   }


   @Test
   public void testStringParsing() throws Exception {
      Writer pw = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
      SpeedMarkerParser parser = new SpeedMarkerParser(new StringReader("<#assign a=b><hallo>${\"Hallo\\nPeter\"}</hallo>"));
      Node node = parser.Start();
      node.debug(pw, "");
   }


   @Test
   public void testAssign() throws Exception {
      Writer pw = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
      SpeedMarkerParser parser = new SpeedMarkerParser(new StringReader("<#assign a=b c=d><#assign h>huzza</#assign><hallo>${\"Hallo\\nPeter\"}</hallo>"));
      Node node = parser.Start();
      node.debug(pw, "");
      Node simpleNode = node.simplify();
      simpleNode.debug(pw, "");
      pw.flush();
   }

   @Test
   public void testUnicodeEscapes() throws Exception {
      Writer pw = new PrintWriter(new OutputStreamWriter(System.out));
      SpeedMarkerParser parser = new SpeedMarkerParser(new StringReader("${\"x\\x00DC\\x0DC\\xDC\"}"));
      Node node = parser.Start();
      node.debug(pw, "");
      Node simpleNode = node.simplify();
      simpleNode.debug(pw, "");
     pw.flush();
   }

   @Test
   public void testStringInStringParsing() throws Exception {
      Writer pw = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
      SpeedMarkerParser parser = new SpeedMarkerParser(new StringReader("hoi${\"b${\\\"x}\\\"}}}}}}}c\"}d"));
      Node node = parser.Start();
      node.debug(pw, "");
      Node simple = node.simplify();
      simple.debug(pw, "");
     pw.flush();
      assertEquals("hoib${\"x}\"}}}}}}}cd", simple.value(null));
      assertEquals("hoib${\"x}\"}}}}}}}cd", node.value(null));
   }

   @Test
   public void testSimpleIf() throws Exception {
      Writer pw = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
      SpeedMarkerParser parser = new SpeedMarkerParser(new StringReader("<#if a>hallo</#if>"));
      Node node = parser.Start();
      node.debug(pw, "");
      Node simple = node.simplify();
      simple.debug(pw, "");
     pw.flush();
   }

   @Test
   public void testCompleteIf() throws Exception {
      Writer pw = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
      SpeedMarkerParser parser = new SpeedMarkerParser(new StringReader("<#if a>hallo<#elseif b>hallo2" +
            "<#elseif c>q<#assign e=f>4<#else>ddddd</#if>"));
      Node node = parser.Start();
      node.debug(pw, "");
      Node simple = node.simplify();
      simple.debug(pw, "");
      pw.flush();
   }

}
