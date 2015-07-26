package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;

/**
 * Created by ptriller on 26.07.2015.
 */
public class FlushNode implements Node {

   public static final FlushNode FLUSH_NODE = new FlushNode();

   private FlushNode() {
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("FLUSH");
   }
}
