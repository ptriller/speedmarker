package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;

/**
 * Created by ptriller on 21.07.2015.
 */
public class AssignNode implements Node {

   private String variableName;

   private Node value;

   public enum Scope {
      DEFAULT,
      GLOBAL,
      LOCAL
   };

   private Scope scope;

   public AssignNode(String variableName, Node value, Scope scope) {
      this.variableName = variableName;
      this.value = value;
      this.scope = scope;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("ASSIGN");
      out.print(indent);
      out.print(" var=");
      out.println(variableName);
      out.print(indent);
      out.println(" value:");
      value.debug(out, indent + "  ");
   }
}
