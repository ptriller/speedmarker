package de.soapwars.speedmarker;

import org.junit.Test;

import java.io.InputStream;

/**
 * Created by ptriller on 11.02.2017.
 */
public class SpeedmarkerTokenTest {
  @Test
  public void testTokenIzer() throws Exception {
    try(InputStream is = SpeedmarkerTokenTest.class.getResourceAsStream("tokentest.txt")) {
      SimpleCharStream stream = new SimpleCharStream(is);
      SpeedMarkerParserTokenManager tokenManager = new SpeedMarkerParserTokenManager(stream);
      for (Token token = tokenManager.getNextToken();
           token.kind != SpeedMarkerParserConstants.EOF;
           token = tokenManager.getNextToken()) {
        System.out.println("(" + token.kind + "): " + token.image);
      }
    }
  }

}
