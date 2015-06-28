package de.soapwars.speedmarker;

import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.*;

/**
 * Created by ptriller on 28.06.2015.
 */
public class SpeedMarkerParserTokenManagerTest {

    @Test
    public void testTokenizer1() throws Exception {
        SimpleCharStream stream = new SimpleCharStream(new StringReader("<#assign test />"));
        SpeedMarkerParserTokenManager manager = new SpeedMarkerParserTokenManager(stream);
        assertEquals(SpeedMarkerParserConstants.DIRECTIVE_START, manager.getNextToken().kind);
        assertEquals(SpeedMarkerParserConstants.ASSIGN, manager.getNextToken().kind);
        assertEquals(SpeedMarkerParserConstants.IDENTIFIER, manager.getNextToken().kind);
        assertEquals(SpeedMarkerParserConstants.EMPTY_TAG, manager.getNextToken().kind);
        assertEquals(SpeedMarkerParserConstants.EOF, manager.getNextToken().kind);
    }

    @Test
    public void testContentMerge() throws Exception {
        SimpleCharStream stream = new SimpleCharStream(new StringReader("test<me"));
        SpeedMarkerParserTokenManager manager = new SpeedMarkerParserTokenManager(stream);
        validateNextToken(manager, SpeedMarkerParserConstants.CONTENT, "test");
        validateNextToken(manager, SpeedMarkerParserConstants.CONTENT, "<");
        validateNextToken(manager, SpeedMarkerParserConstants.CONTENT, "me");
    }

    @Test
    public void testDollarExpression() throws Exception {
        SimpleCharStream stream = new SimpleCharStream(new StringReader("x${test}y"));
        SpeedMarkerParserTokenManager manager = new SpeedMarkerParserTokenManager(stream);
        validateNextToken(manager, SpeedMarkerParserConstants.CONTENT, "x");
        validateNextToken(manager, SpeedMarkerParserConstants.EXPRESSION_START, "${");
        validateNextToken(manager, SpeedMarkerParserConstants.IDENTIFIER, "test");
        validateNextToken(manager, SpeedMarkerParserConstants.CURLYCLOSE, "}");
        validateNextToken(manager, SpeedMarkerParserConstants.CONTENT, "y");
    }

    private void validateNextToken(SpeedMarkerParserTokenManager manager, int kind, String value) {
        Token token = manager.getNextToken();
        assertEquals(kind, token.kind);
        assertEquals(value, token.image);
    }
}