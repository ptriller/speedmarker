package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import java.io.PrintWriter;

/**
 * @author Peter Triller
 *         <p/>
 *         Copyright (c) 2015 GMX GmbH, Muenchen. All rights reserved.
 */
public class ListNode implements Node {

  private Node sequence;

  private String variable;

  private Node prefix;

  private Node body;

  private Node postfix;

  private Node empty;

  public ListNode(Node sequence, String variable, Node prefix, Node body, Node postfix, Node empty) {
    this.sequence = sequence;
    this.variable = variable;
    this.prefix = prefix;
    this.body = body;
    this.postfix = postfix;
    this.empty = empty;
  }


  @Override
  public void debug(PrintWriter out, String indent) {
    out.print(indent);
    out.print("LIST");
    out.print(indent);
    out.print(" sequence:");
    sequence.debug(out, indent + "  ");

  }
}
