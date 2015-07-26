package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;
import java.util.List;

/**
 * Created by ptriller on 27.07.2015.
 */
public class IncludeNode implements Node {

   String included;

   List<ParameterNode> parameters;

   public IncludeNode(String included, List<ParameterNode> parameters) {
      this.included = included;
      this.parameters = parameters;
   }

   @Override
   public void debug(PrintWriter out, String indent) {

   }
}
