package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;
import java.util.List;

/**
 * Created by ptriller on 26.07.2015.
 */
public class FunctionNode implements Node {

   private String name;

   private List<ParameterNode> parameters;

   private Node body;

   public FunctionNode(String name, List<ParameterNode> parameters, Node body) {
      this.name = name;
      this.parameters = parameters;
      this.body = body;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("FUNCTION");
      out.print(indent);
      out.print(" name: ");
      out.println(name);
      out.print(indent);
      out.println(" parameters:");
      for (ParameterNode node : parameters) {
         node.debug(out, indent + "  ");
      }
      out.print(indent);
      out.print(" body: ");
      body.debug(out, indent + "  ");
   }
}
