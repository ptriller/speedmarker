package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Environment;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created by ptriller on 06.07.2015.
 */
public class ConditionalNode implements Node {

   private List<ConditionalBlock> conditionals;

   private Node elseBlock;

   public ConditionalNode(List<ConditionalBlock> conditionals, Node elseBlock) {
      this.conditionals = conditionals;
      this.elseBlock = elseBlock;
   }

   @Override
   public Object value(Environment env) {
      Node node = selectBlock(env);
      if (node != null) {
         return node.value(env);
      }
      return null;
   }

   @Override
   public void output(Environment env, Writer out) throws IOException {
      Node node = selectBlock(env);
      if (node != null) {
         node.output(env, out);
      }
   }

   private Node selectBlock(Environment env) {
      for (ConditionalBlock conditional : conditionals) {
         // TODO Expressions
         if (conditional.condition.value(env) != null) {
            return conditional.block;
         }
      }
      if (elseBlock != null) {
         return elseBlock;
      }
      return null;
   }


   @Override
   public void debug(Writer out, String indent) throws IOException {
      out.append(indent);
      out.append("IF:\n");
      for(ConditionalBlock block: conditionals) {
         out.append(indent + ' ');
         out.append("COND:\n");
         block.condition.debug(out, indent + "  ");
         out.append(indent + ' ');
         out.append("BLOCK:\n");
         block.block.debug(out, indent + "  ");
      }
      if(elseBlock != null) {
         out.append(indent + ' ');
         out.append("ELSE:\n");
         elseBlock.debug(out, indent + "  ");
      }
   }

   @Override
   public Node simplify() {
      return this;
   }

   @Override
   public boolean isConstant() {
      return false;
   }
}
