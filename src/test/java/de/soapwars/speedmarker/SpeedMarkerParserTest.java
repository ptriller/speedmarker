package de.soapwars.speedmarker;

import de.soapwars.speedmarker.ast.Node;
import org.junit.Test;

import java.io.StringReader;

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
}
