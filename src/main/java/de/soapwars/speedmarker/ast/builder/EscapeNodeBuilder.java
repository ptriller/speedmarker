package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.ParseException;
import de.soapwars.speedmarker.Token;
import de.soapwars.speedmarker.ast.node.EscapeNode;

/**
 * Created by ptriller on 12.02.2017.
 */
public class EscapeNodeBuilder {

  private String name;

  private Expression expression;

  private Node content;

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

  public void expression(Expression expression) {
    this.expression = expression;
  }

  public void content(Node content) {
    this.content = content;
  }

  public EscapeNode build() {
    return new EscapeNode(name, expression, content);
  }
}
