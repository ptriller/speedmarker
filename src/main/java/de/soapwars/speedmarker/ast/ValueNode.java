package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Environment;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by ptriller on 04.07.2015.
 */
public class ValueNode implements Node {

   private Object value;

   public ValueNode(Object value) {
      this.value = value;
   }


   @Override
   public Object value(Environment env) {
      return value;
   }

   @Override
   public void output(Environment env, Writer out) throws IOException {
      out.write(value.toString());
   }

   @Override
   public void debug(Writer out, String indent) throws IOException {
      out.write(indent);
      out.append('\"');
      out.write(value.toString());
      out.write("\"\n");
   }

   @Override
   public Node simplify() {
      return this;
   }

   @Override
   public boolean isConstant() {
      return true;
   }
}
