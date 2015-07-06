package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.*;

import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;

/**
 * Created by ptriller on 04.07.2015.
 */
public class ParsingStringNode implements Node {

   private Node node;

   public ParsingStringNode(String expression, int beginLine, int beginColumn) throws ParseException {
      SpeedMarkerParserTokenManager manager = new SpeedMarkerParserTokenManager(
            new SimpleCharStream(new StringReader(expression), beginLine, beginColumn),
            SpeedMarkerParserConstants.EXPRESSION);
      SpeedMarkerParser speedMarkerParser = new SpeedMarkerParser(manager);
      node = speedMarkerParser.Expression();
   }

   @Override
   public Object value(Environment env) {
      return node.value(env);
   }

   @Override
   public void output(Environment env, Writer out) throws IOException {
      node.output(env, out);
   }

   @Override
   public void debug(Writer out, String indent) throws IOException {
      out.write(indent);
      out.write("PARSED:\n");
      node.debug(out, indent + ' ');
   }

   @Override
   public Node simplify() {
      return node;
   }

   @Override
   public boolean isConstant() {
      return node.isConstant();
   }
}
