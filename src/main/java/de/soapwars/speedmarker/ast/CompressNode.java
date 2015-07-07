package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Environment;
import de.soapwars.speedmarker.ast.compress.CompressWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author Peter Triller
 *         <p/>
 *         Copyright (c) 2015 GMX GmbH, Muenchen. All rights reserved.
 */
public class CompressNode implements Node {

  private Node node;

  public CompressNode(Node node) {
    this.node = node;
  }

  @Override
  public boolean isConstant() {
    return node.isConstant();
  }

  @Override
  public Node simplify() {
    node = node.simplify();
    if (node.isConstant()) {
      return new ValueNode(value(null));
    }
    return this;
  }

  @Override
  public void debug(Writer out, String indent) throws IOException {
    out.append(indent);
    out.append("COMPRESS:\n");
    node.debug(out, indent + ' ');

  }

  @Override
  public void output(Environment env, Writer out) throws IOException {
    node.output(env, new CompressWriter(out));
  }

  @Override
  public Object value(Environment env) {
    final StringWriter stringWriter = new StringWriter();
    final CompressWriter writer = new CompressWriter(stringWriter);
    try {
      node.output(null, writer);
      writer.close();
      return writer.toString();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
