package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.ast.node.AttemptNode;

/**
 * Created by ptriller on 13.02.2017.
 */
public class AttemptNodeBuilder {

  private Node attempt;

  private Node recover;

  public void attempt(Node attempt) {
    this.attempt = attempt;
  }

  public void recover(Node recover) {
    this.recover = recover;
  }

  public AttemptNode build() {
    return new AttemptNode(attempt, recover);
  }
}
