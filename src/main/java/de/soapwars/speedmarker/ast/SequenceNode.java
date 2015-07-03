package de.soapwars.speedmarker.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Peter Triller
 *         <p/>
 *         Copyright (c) 2015 GMX GmbH, Muenchen. All rights reserved.
 */
public class SequenceNode implements Node {

  private List<Node> nodes;

  public static final SequenceNode EMPTY_SEQUENCE = new SequenceNode();

  private SequenceNode() {
    this.nodes = Collections.emptyList();
  }


  public SequenceNode(SequenceNode oldNode, Node node) {
    this.nodes = new ArrayList<Node>();
    nodes.add(node);
    nodes.addAll(oldNode.nodes);
  }

  @Override
  public void print(String depth) {
    System.out.print(depth);
    System.out.println('[');
    for (Node node : nodes) {
      node.print(depth + ' ');
    }
    System.out.print(depth);
    System.out.println(']');
  }
}
