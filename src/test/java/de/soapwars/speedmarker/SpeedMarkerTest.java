package de.soapwars.speedmarker;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.PrintWriter;

/**
 * Created by ptriller on 21.07.2015.
 */
public class SpeedMarkerTest {

   @Test
   public void testMe() throws Exception {
      SpeedMarkerLexer lexer =
            new SpeedMarkerLexer(new ANTLRInputStream(SpeedMarkerTest.class.getResourceAsStream("test1.ftl")));
      SpeedMarker marker = new SpeedMarker(new CommonTokenStream(lexer));
      marker.setBuildParseTree(true);
      marker.setErrorHandler(new BailErrorStrategy());
      ParseTree tree = marker.start();
      System.out.println(tree.toStringTree(marker));
      SpeedMarkerTemplateGenerator visitor = new SpeedMarkerTemplateGenerator();
      Node visit = visitor.visit(tree);
      PrintWriter out = new PrintWriter(System.out);
      visit.debug(out,"");
      out.flush();

   }


}
