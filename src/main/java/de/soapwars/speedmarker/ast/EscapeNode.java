package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;

/**
 * Created by ptriller on 22.07.2015.
 */
public class EscapeNode implements Node {

   private String varName;

   private Node escapeExpression;

   private Node node;

   public EscapeNode(String varName, Node escapeExpression, Node node) {
      this.varName = varName;
      this.escapeExpression = escapeExpression;
      this.node = node;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.print("ESCAPE");
      out.print(indent);
      out.print(" var=");
      out.println(varName);
      out.print(indent);
      out.print(" escape:");
      escapeExpression.debug(out, indent + "  ");
      out.print(indent);
      out.print(" block:");
      node.debug(out, indent + "  ");
   }
}
