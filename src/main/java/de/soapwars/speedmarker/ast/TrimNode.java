package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;

/**
 * Created by ptriller on 28.07.2015.
 */
public class TrimNode implements Node {

   public final static TrimNode TRIM_LEFT = new TrimNode(true, false);

   public final static TrimNode TRIM_RIGHT = new TrimNode(false, true);

   public final static TrimNode TRIM_BOTH = new TrimNode(true, true);

   public final static TrimNode NO_TRIM = new TrimNode(false, false);

   private boolean trimLeft;

   private boolean trimRight;

   public TrimNode(boolean trimLeft, boolean trimRight) {
      this.trimLeft = trimLeft;
      this.trimRight = trimRight;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("TRIM");
      out.print(indent);
      out.print(" trimLeft: ");
      out.println(trimLeft);
      out.print(indent);
      out.print(" trimRight: ");
      out.println(trimRight);
   }
}
