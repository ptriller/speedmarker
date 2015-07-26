package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;

/**
 * Created by ptriller on 20.07.2015.
 */
public class CommentNode implements Node {

   private String comment;

   public CommentNode(String comment) {
      this.comment = comment;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.print("(COMMENT");
      out.print(indent);
      out.print(" \"");
      out.print(comment);
      out.print('\"');
   }
}
