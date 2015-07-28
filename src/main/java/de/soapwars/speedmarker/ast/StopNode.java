package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.PrintWriter;

/**
 * Created by ptriller on 28.07.2015.
 */
public class StopNode implements Node {

   private String message;

   public StopNode(String message) {
      this.message = message;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("STOP");
      out.print(indent);
      out.print(" message: \"");
      out.print(StringEscapeUtils.escapeJava(message));
      out.println("\"");
   }
}
