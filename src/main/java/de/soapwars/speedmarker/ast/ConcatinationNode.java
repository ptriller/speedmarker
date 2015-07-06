package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Environment;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ptriller on 04.07.2015.
 */
public class ConcatinationNode implements Node {

   private List<Node> nodes = new ArrayList<>();

   public void appendNode(Node node) {
      nodes.add(node);
   }

   public void appendAll(ConcatinationNode node) {
      nodes.addAll(node.nodes);
   }

   @Override
   public Object value(Environment env) {
      if (nodes.isEmpty()) {
         return null;
      } else if (nodes.size() == 1 && nodes.get(0) != null) {
         return nodes.get(0).value(env);
      }
      StringWriter writer = new StringWriter();
      try {
         output(env, writer);
      } catch (IOException e) {
         e.printStackTrace();
      }
      return writer.toString();
   }

   @Override
   public void output(Environment env, Writer out) throws IOException {
      for (Node node : nodes) {
         if(node != null) {
            node.output(env, out);
         }
      }
   }


   @Override
   public Node simplify() {
      List<Node> simpleNodes = new ArrayList<>();
      for (Node node : nodes) {
         if (node != null) {
            Node simpleNode = node.simplify();
            if (simpleNode != null) {
               simpleNodes.add(simpleNode);
            }
         }
      }
      nodes = simpleNodes;
      if (nodes.isEmpty()) {
         return null;
      } else if (nodes.size() == 1) {
         return nodes.get(0).simplify();
      }
      if (isConstant()) {
         return new ValueNode(value(null));
      }
      return this;
   }

   @Override
   public boolean isConstant() {
      for (Node node : nodes) {
         if (!node.isConstant()) {
            return false;
         }
      }
      return true;
   }

   @Override
   public void debug(Writer out, String indent) throws IOException {
      out.write(indent);
      out.write("[\n");
      String nidnent = indent + ' ';
      for (Node node : nodes) {
         node.debug(out, nidnent);
      }
      out.write(indent);
      out.write("]\n");
   }
}
