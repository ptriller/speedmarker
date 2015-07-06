package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Environment;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Peter Triller
 *         <p/>
 *         Copyright (c) 2015 GMX GmbH, Muenchen. All rights reserved.
 */
public class IdentifierNode implements Node {

  private String identifier;

  public IdentifierNode(String identifier) {
    this.identifier = identifier;
  }

  @Override
  public Object value(Environment env) {
    //TODO
    return null;
  }

  @Override
  public void output(Environment env, Writer out) throws IOException {
    //TODO
  }

  @Override
  public void debug(Writer out, String indent) throws IOException {
    out.write(indent);
    out.write("IDENTIFIER: \"");
    out.write(identifier);
    out.write("\"\n");
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
