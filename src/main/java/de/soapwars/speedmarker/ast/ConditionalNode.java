package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;

/**
 * Created by ptriller on 21.07.2015.
 */
public class ConditionalNode implements Node {

   private Node condition;

   private Node block;

   public ConditionalNode(Node condition, Node block) {
      this.condition = condition;
      this.block = block;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("CONDITIONAL");
      out.print(indent);
      out.println(" condition:");
      condition.debug(out, indent + "  ");
      out.print(indent);
      out.println(" block:");
      block.debug(out, indent + "  ");
   }
}
