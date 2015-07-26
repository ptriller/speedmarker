package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;
import java.util.List;

/**
 * Created by ptriller on 21.07.2015.
 */
public class SequenceNode implements Node {

   private List<Node> nodes;

   public SequenceNode(List<Node> nodes) {
      this.nodes = nodes;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("SEQUENCE");
      for (Node node : nodes) {
         node.debug(out, indent + "  ");
      }
   }
}
