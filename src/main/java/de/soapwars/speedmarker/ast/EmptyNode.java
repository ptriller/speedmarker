package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Environment;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Peter Triller
 *         <p/>
 *         Copyright (c) 2015 GMX GmbH, Muenchen. All rights reserved.
 */
public class EmptyNode implements Node {

  public static final EmptyNode EMPTY_NODE = new EmptyNode();

  private EmptyNode() {
  }

  @Override
  public Object value(Environment env) {
    return null;
  }

  @Override
  public void output(Environment env, Writer out) throws IOException {

  }

  @Override
  public void debug(Writer out, String indent) throws IOException {
    out.write(indent);
    out.write("<EMPTY>\n");
  }

  @Override
  public Node simplify() {
    return EMPTY_NODE;
  }

  @Override
  public boolean isConstant() {
    return true;
  }
}
