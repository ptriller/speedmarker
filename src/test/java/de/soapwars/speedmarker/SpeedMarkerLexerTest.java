package de.soapwars.speedmarker;

import org.antlr.v4.runtime.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.soapwars.speedmarker.SpeedMarkerLexer.*;

/**
 * Created by ptriller on 20.07.2015.
 */
public class SpeedMarkerLexerTest {


   @Test
   public void testInlineExpressionDoubleString() {
      Assert.assertArrayEquals(tokenize("$ {${\"xxx\"}x"),
            new int[]{CONTENT,
                  WHITESPACE,
                  CONTENT,
                  EXPRESSION_START,
                  STRINGLITERAL,
                  CURLY_CLOSE,
                  CONTENT});
   }

   @Test
   public void testInlineExpressionSingleString() {
      Assert.assertArrayEquals(tokenize("$ {${\"xx\\\"x\"}x"),
            new int[]{CONTENT,
                  WHITESPACE,
                  CONTENT,
                  EXPRESSION_START,
                  STRINGLITERAL,
                  CURLY_CLOSE,
                  CONTENT});
   }

   @Test
   public void testInlineExpressionRawString() {
      Assert.assertArrayEquals(tokenize("$ {${r\"x\\nxx\"}x"),
            new int[]{CONTENT,
                  WHITESPACE,
                  CONTENT,
                  EXPRESSION_START,
                  RAWSTRINGLITERAL,
                  CURLY_CLOSE,
                  CONTENT});
   }

   @Test
   public void testContentStream() {
      Assert.assertArrayEquals(tokenize("test \n me $    peter"),
            new int[]{CONTENT,
                  WHITESPACE,
                  CONTENT,
                  WHITESPACE,
                  CONTENT,
                  WHITESPACE,
                  CONTENT});
   }

   @Test
   public void testDirective() {
      Assert.assertArrayEquals(tokenize("test <#assign>"),
            new int[]{CONTENT,
                  WHITESPACE,
                  CONTENT,
                  WHITESPACE,
                  CONTENT,
                  WHITESPACE,
                  CONTENT});

   }

   @Test
   public void textLexer() throws Exception {
      ANTLRInputStream stream = new ANTLRInputStream(SpeedMarkerLexerTest.class.getResourceAsStream("test1.ftl"));
      TokenSource testee = new SpeedMarkerLexer(stream);
      testee = new WhitespaceFIlteringTokenSource(testee);
      Token t = testee.nextToken();
      while(t.getType() != Token.EOF) {
         System.out.println(SpeedMarkerLexer.VOCABULARY.getSymbolicName(t.getType()));
         t = testee.nextToken();
      }
   }

   @Test
   public void testFile() throws Exception {
      ANTLRInputStream stream = new ANTLRInputStream(SpeedMarkerLexerTest.class.getResourceAsStream("example.ftl"));
      SpeedMarkerLexer testee = new SpeedMarkerLexer(stream);
      List<? extends Token> tokens = testee.getAllTokens();
      int[] result = new int[tokens.size()];
      int i = 0;
      for (Token t : tokens) {
         result[i++] = t.getType();
         System.out.print(SpeedMarkerLexer.VOCABULARY.getSymbolicName(t.getType()));
         System.out.println(": " + t.toString());
      }
   }

   int[] tokenize(String data) {
      ANTLRInputStream stream = new ANTLRInputStream(data);
      SpeedMarkerLexer testee = new SpeedMarkerLexer(stream);
      List<? extends Token> tokens = testee.getAllTokens();
      int[] result = new int[tokens.size()];
      int i = 0;
      for (Token t : tokens) {
         result[i++] = t.getType();
         System.out.print(SpeedMarkerLexer.VOCABULARY.getSymbolicName(t.getType()));
         System.out.println(": " + t.toString());
      }
      return result;
   }


   @Test
   public void regexTest() throws Exception {
      Pattern pattern = Pattern.compile("([ \t\u000B\f]*\r?\n)?(.*)", Pattern.DOTALL);
      Matcher matcher = pattern.matcher("\r\n\r\n\r\n   ");
      System.err.println(matcher.matches());
      System.err.println(matcher.group(1));
      System.err.println(matcher.group(2));

   }
}
