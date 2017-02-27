package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.ParseException;
import de.soapwars.speedmarker.Token;
import de.soapwars.speedmarker.ast.node.ImportNode;

/**
 * Created by ptriller on 12.02.2017.
 */
public class ImportNodeBuilder {

  private String name;

  private Expression path;

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

  public void path(Expression path) {
    this.path = path;
  }

  public ImportNode build() {
    return new ImportNode(name, path);
  }
}
