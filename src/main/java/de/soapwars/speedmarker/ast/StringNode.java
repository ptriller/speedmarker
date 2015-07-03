package de.soapwars.speedmarker.ast;

/**
 * @author Peter Triller
 *         <p/>
 *         Copyright (c) 2015 GMX GmbH, Muenchen. All rights reserved.
 */
public class StringNode implements Node {

  private String value;

  public StringNode(String value) {
    this.value = value;
  }

  @Override
  public void print(String depth) {
    System.out.print(depth);
    System.out.print("STR: \"");
    System.out.print(value);
    System.out.println("\"");
  }
}
