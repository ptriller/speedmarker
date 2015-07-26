package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;
import java.util.List;

/**
 * Created by ptriller on 21.07.2015.
 */
public class IfNode implements Node {

   private List<ConditionalNode> nodes;

   public IfNode(List<ConditionalNode> nodes) {
      this.nodes = nodes;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("IF");
      for (ConditionalNode node : nodes) {
         node.debug(out, indent + "  ");
      }
   }
}
