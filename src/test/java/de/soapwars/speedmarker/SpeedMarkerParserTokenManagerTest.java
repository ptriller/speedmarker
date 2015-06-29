package de.soapwars.speedmarker;

import org.junit.Test;

import java.io.StringReader;

import static de.soapwars.speedmarker.SpeedMarkerParserConstants.*;
import static org.junit.Assert.*;

/**
 * Created by ptriller on 28.06.2015.
 */
public class SpeedMarkerParserTokenManagerTest {

    @Test
    public void testTokenizer1() throws Exception {
        validateTokenStream("<#assign test />",
                DIRECTIVE_START, ASSIGN, IDENTIFIER, EMPTY_TAG, EOF);
    }

    @Test
    public void testTokenizer2() throws Exception {
        validateTokenStream("xxx${{}}yyy",
                CONTENT, EXPRESSION_START, CURLYOPEN, CURLYCLOSE, CURLYCLOSE, CONTENT, EOF);
    }

    @Test
    public void testTokenizer3() throws Exception {
        validateTokenStream("xxx${\"Hallo ${du} da\"}yyy",
                CONTENT, EXPRESSION_START, DOUBLE_STRINGSEGMENT, STRING_EXPRESSION_START
                , IDENTIFIER, CURLYCLOSE, DOUBLE_STRINGSEGMENT, END_DOUBLE_STRING,
                CURLYCLOSE, CONTENT, EOF);
    }

    @Test
    public void testTokenizer4() throws Exception {
        validateTokenStream("xxx${\'Hallo ${du} da\'}yyy",
                CONTENT, EXPRESSION_START, SINGLE_STRINGSEGMENT, STRING_EXPRESSION_START
                , IDENTIFIER, CURLYCLOSE, SINGLE_STRINGSEGMENT, END_SINGLE_STRING,
                CURLYCLOSE, CONTENT, EOF);
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
        validateNextToken(manager, IDENTIFIER, "test");
        validateNextToken(manager, SpeedMarkerParserConstants.CURLYCLOSE, "}");
        validateNextToken(manager, SpeedMarkerParserConstants.CONTENT, "y");
    }

    private void validateTokenStream(String input, int... tokens) {
        SimpleCharStream stream = new SimpleCharStream(new StringReader(input));
        SpeedMarkerParserTokenManager manager = new SpeedMarkerParserTokenManager(stream);
        int i = 0;
        for (int token : tokens) {
            Token tk = manager.getNextToken();
            assertEquals("Failed at Token " + i + ' ' + tk,
                    token, tk.kind);
            ++i;
        }
    }

    private void validateNextToken(SpeedMarkerParserTokenManager manager, int kind, String value) {
        Token token = manager.getNextToken();
        assertEquals(kind, token.kind);
        assertEquals(value, token.image);
    }
}