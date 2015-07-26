package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.PrintWriter;

/**
 * Created by ptriller on 26.07.2015.
 */
public class ImportNode implements Node {

   private String imported;

   private String namespace;

   public ImportNode(String imported, String namespace) {
      this.imported = imported;
      this.namespace = namespace;
   }

   @Override
   public void debug(PrintWriter out, String indent) {
      out.print(indent);
      out.println("IMPORT");
      out.print(indent);
      out.print(" imported: \"");
      out.print(StringEscapeUtils.escapeJava(imported));
      out.println("\"");
      out.print(indent);
      out.print(" namespace: ");
      out.println(namespace);
   }
}
