package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Environment;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author Peter Triller
 *         <p/>
 *         Copyright (c) 2015 GMX GmbH, Muenchen. All rights reserved.
 */
public class AttemptNode implements Node {

  private Node attempt;

  private Node recover;

  public AttemptNode(Node attempt, Node recover) {
    this.attempt = attempt;
    this.recover = recover;
  }

  @Override
  public Object value(Environment env) {
    try {
      // TODO Protect env ?
      return attempt.value(env);
    } catch (RuntimeException e) {
      return recover.value(env);
    }
  }

  @Override
  public void output(Environment env, Writer out) throws IOException {
    StringWriter stringWriter = new StringWriter();
    ;
    try {
      // TODO Protect env ?
      attempt.output(env, stringWriter);
    } catch (RuntimeException e) {
      recover.output(env, out);
    }
    out.write(stringWriter.toString());
  }

  @Override
  public Node simplify() {
    attempt = attempt.simplify();
    if (attempt.isConstant()) {
      return attempt;
    }
    recover = recover.simplify();
    return this;
  }

  @Override
  public boolean isConstant() {
    return attempt.isConstant();
  }

  @Override
  public void debug(Writer out, String indent) throws IOException {
    out.append(indent);
    out.append("ATTEMPT:\n");
    attempt.debug(out, indent + "  ");
    out.append(indent);
    out.append(" RECOVER:\n");
    recover.debug(out, indent + "  ");
  }
}
