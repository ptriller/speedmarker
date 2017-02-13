package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.ast.AttemptNode;
import de.soapwars.speedmarker.ast.ParseState;

/**
 * Created by ptriller on 13.02.2017.
 */
public class AttemptNodeBuilder {

  private Node attempt;

  private Node recover;

  public static AttemptNodeBuilder create(ParseState state) {
    return new AttemptNodeBuilder();
  }

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
