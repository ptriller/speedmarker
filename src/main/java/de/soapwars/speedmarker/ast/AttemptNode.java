package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;

/**
 * Created by ptriller on 21.07.2015.
 */
public class AttemptNode implements Node {

   private Node attempt;

   private Node recover;

   public AttemptNode(Node attempt, Node recover) {
      this.attempt = attempt;
      this.recover = recover;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("ATTEMPT");
      out.print(indent);
      out.println(" attempt:");
      attempt.debug(out, indent + "  ");
      out.print(indent);
      out.println(" recover:");
      recover.debug(out, indent + "  ");
   }
}
