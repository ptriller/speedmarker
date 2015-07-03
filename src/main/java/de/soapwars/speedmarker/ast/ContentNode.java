package de.soapwars.speedmarker.ast;

/**
 * @author Peter Triller
 *         <p/>
 *         Copyright (c) 2015 GMX GmbH, Muenchen. All rights reserved.
 */
public class ContentNode implements Node {

  private String content;

  public ContentNode(String content) {
    this.content = content;
  }

  @Override
  public void print(String depth) {
    System.out.print(depth);
    System.out.print("CONTENT: \"");
    System.out.print(content);
    System.out.println('\"');
  }

}
