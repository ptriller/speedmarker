package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;

/**
 * Created by ptriller on 21.07.2015.
 */
public class CompressNode implements Node {

   private Node child;

   public CompressNode(Node child) {
      this.child = child;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("COMPRESS");
      child.debug(out, indent + "  ");
   }
}
