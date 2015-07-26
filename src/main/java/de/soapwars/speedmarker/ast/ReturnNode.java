package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;

/**
 * Created by ptriller on 21.07.2015.
 */
public class ReturnNode implements Node {

   private Node expression;

   public ReturnNode(Node expression) {
      this.expression = expression;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("RETURN");
      if (expression != null) {
         out.print(indent);
         out.println(" value:");
         expression.debug(out, indent + "  ");
      }
   }
}
