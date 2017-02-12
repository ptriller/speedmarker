package de.soapwars.speedmarker;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by ptriller on 11.02.2017.
 */
public class SpeedMarkerParserTest {

  @Test
  public void testComment() throws Exception {
    try(InputStream is = SpeedmarkerTokenTest.class.getResourceAsStream("parse_comment.txt")) {
      SpeedMarkerParser parser = new SpeedMarkerParser(is, "UTF-8");
      Assert.assertEquals("(<#-- adssdfhsdf <#-- ${ -->)", parser.start().debugTree());
    }
  }
  @Test
  public void testContent() throws Exception {
    try(InputStream is = SpeedmarkerTokenTest.class.getResourceAsStream("parse_content.txt")) {
      SpeedMarkerParser parser = new SpeedMarkerParser(is, "UTF-8");
      Assert.assertEquals("(\"dsfsdfds  sdf sdf < dsf sdf $ sdf \",<#-- stuff -->,\" sdf $ sdf < dsf\")"
          , parser.start().debugTree());
    }
  }

  @Test
  public void testInlineExpression() throws Exception {
    try(InputStream is = SpeedmarkerTokenTest.class.getResourceAsStream("parse_inlineexpression.txt")) {
      SpeedMarkerParser parser = new SpeedMarkerParser(is, "UTF-8");
      Assert.assertEquals("(\"sdfsdf sdf $ds \",${ ..... },\" $ }}}\")", parser.start().debugTree());
    }
  }

  @Test
  public void tesNoParse() throws Exception {
    try(InputStream is = SpeedmarkerTokenTest.class.getResourceAsStream("parse_noparse.txt")) {
      SpeedMarkerParser parser = new SpeedMarkerParser(is, "UTF-8");
      Assert.assertEquals("(\"asdfasdfasd <#-- testme --> ${again} sdfsdf\")", parser.start().debugTree());
    }
  }

}
