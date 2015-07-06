package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Environment;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by ptriller on 06.07.2015.
 */
public class AssignNode implements Node {

   private Node variable;

   private Node value;

   public AssignNode(Node variable, Node value) {
      this.variable = variable;
      this.value = value;
   }

   @Override
   public Object value(Environment env) {
      return null;
   }

   @Override
   public void output(Environment env, Writer out) throws IOException {
      // NIL
   }


   @Override
   public Node simplify() {
      variable = variable.simplify();
      value = value.simplify();
      return this;
   }

   @Override
   public boolean isConstant() {
      return false;
   }


   @Override
   public void debug(Writer out, String indent) throws IOException {
      out.append(indent);
      out.append("ASSIGN:\n");
      variable.debug(out, indent + ' ');
      value.debug(out, indent + ' ');
   }

}
