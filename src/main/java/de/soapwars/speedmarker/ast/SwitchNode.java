package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;
import java.util.List;

/**
 * Created by ptriller on 21.07.2015.
 */
public class SwitchNode implements Node {

   private Node expression;

   private List<ConditionalNode> cases;

   public SwitchNode(Node expression, List<ConditionalNode> cases) {
      this.expression = expression;
      this.cases = cases;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("SWITCH");
      out.print(indent);
      out.println(" expression:");
      expression.debug(out, indent + "  ");
      for(ConditionalNode node: cases) {
         node.debug(out, indent + "  ");
      }
   }
}
