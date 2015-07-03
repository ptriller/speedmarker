package de.soapwars.speedmarker.ast;

/**
 * @author Peter Triller
 *         <p/>
 *         Copyright (c) 2015 GMX GmbH, Muenchen. All rights reserved.
 */
public class EmptyNode implements Node {

  public static final EmptyNode EMPTY_NODE = new EmptyNode();

  @Override
  public void print(String depth) {
    System.out.print(depth);
    System.out.println("EMPTY");
  }
}
