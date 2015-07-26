package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;
import java.util.List;

/**
 * Created by ptriller on 26.07.2015.
 */
public class FtlNode implements Node {

   private List<ParameterNode>  parameter;

   public FtlNode(List<ParameterNode> parameter) {
      this.parameter = parameter;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("FTL");
      out.print(indent);
      out.println(" parameter:");
      for(ParameterNode node: parameter) {
         node.debug(out, indent + "  ");
      }
   }
}
