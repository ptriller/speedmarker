package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;

/**
 * Created by ptriller on 21.07.2015.
 */
public class BreakNode implements Node {

   public static final BreakNode BREAK_NODE = new BreakNode();

   private BreakNode() {
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("BREAK");
   }
}
