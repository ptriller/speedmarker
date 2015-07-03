package de.soapwars.speedmarker.ast;

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
  public void print(String depth) {
    System.out.print(depth);
    System.out.print("IDENT: \"");
    System.out.print(identifier);
    System.out.println('\"');
  }
}
