package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.ParseException;
import de.soapwars.speedmarker.Token;
import de.soapwars.speedmarker.ast.ImportNode;
import de.soapwars.speedmarker.ast.ParseState;

/**
 * Created by ptriller on 12.02.2017.
 */
public class ImportNodeBuilder {

  private String name;

  private Node path;

  public static ImportNodeBuilder create(ParseState state) {
    return new ImportNodeBuilder();
  }

  public void name(String name) {
    this.name = name;
  }

  public void keyword(Token keyword) throws ParseException {
    if (!"as".equals(keyword.image)) {
      throw new ParseException("Encountered \"" + keyword.image + "\" at line "
          + keyword.beginLine + ", column: " + keyword.beginColumn
          + "\nWas expecting:\n    \"as\" ...");
    }
  }

  public void path(Node path) {
    this.path = path;
  }

  public ImportNode build() {
    return new ImportNode(name, path);
  }
}
