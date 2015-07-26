package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.PrintWriter;

/**
 * Created by ptriller on 21.07.2015.
 */
public class ValueNode implements Node {

   private Object value;

   public ValueNode(Object value) {
      this.value = value;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.print("VALUE: ");
      if(value instanceof String) {
         out.print('\"');
      }
      out.print(StringEscapeUtils.escapeJava(value.toString()));
      if(value instanceof String) {
         out.print('\"');
      }
      out.println();
   }
}
