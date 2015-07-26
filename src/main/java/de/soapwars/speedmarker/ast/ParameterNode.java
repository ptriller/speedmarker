package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;

/**
 * Created by ptriller on 26.07.2015.
 */
public class ParameterNode implements Node {

   private String parameterName;

   private Node defaultValue;

   private boolean ellipse;

   public ParameterNode(String parameterName, Node defaultValue, boolean elipse) {
      this.parameterName = parameterName;
      this.defaultValue = defaultValue;
      this.ellipse = elipse;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("PARAMETER");
      out.print(indent);
      out.print(" name: ");
      out.println(parameterName);
      if (defaultValue != null) {
         out.print(indent);
         out.println(" default:");
         defaultValue.debug(out, indent + "  ");
      }
      if (ellipse) {
         out.print(indent);
         out.println(" ellipse");
      }
   }
}