package de.soapwars.speedmarker;

import de.soapwars.speedmarker.ast.ParseState;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by ptriller on 11.02.2017.
 */
public class SpeedMarkerParserTest {

  @Test
  public void testAssign() throws Exception {
    testFile("assign");
  }

  @Test
  public void testAttempt() throws Exception {
    testFile("attempt");
  }

  @Test
  public void testComment() throws Exception {
    testFile("comment");
  }

  @Test
  public void testCompress() throws Exception {
    testFile("compress");
  }

  @Test
  public void testContent() throws Exception {
    testFile("content");
  }

  @Test
  public void testEscape() throws Exception {
    testFile("escape");
  }

  @Test
  public void testFunction() throws Exception {
    testFile("function");
  }


  @Test
  public void testInlineExpression() throws Exception {
    testFile("inlineexpression");
  }

  @Test
  public void testNoParse() throws Exception {
    testFile("noparse");
  }

  @Test
  public void testIf() throws Exception {
    testFile("if");
  }

  @Test
  public void testImport() throws Exception {
    testFile("import");
  }

  @Test
  public void testInclude() throws Exception {
    testFile("include");
  }

  @Test
  public void testList() throws Exception {
    testFile("list");
  }

  private void testFile(String testName) throws Exception {
    try (InputStream is = SpeedmarkerTokenTest.class.getResourceAsStream(testName + "_input.ftl")) {
      SpeedMarkerParser parser = new SpeedMarkerParser(is, "UTF-8");
      String expected = new Scanner(SpeedmarkerTokenTest.class.getResourceAsStream(testName + "_expected.txt"))
          .useDelimiter("\\A").next();
      Assert.assertEquals(expected, parser.start(new ParseState()).debugTree());
    }
  }

}
