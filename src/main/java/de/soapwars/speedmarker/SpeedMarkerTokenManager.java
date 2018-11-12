/*
 * Copyright (c) 2018 1&1 Mail & Media GmbH, Muenchen. All rights reserved.
 */
package de.soapwars.speedmarker;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ptriller
 */
public class SpeedMarkerTokenManager extends SpeedMarkerParserBaseTokenManager {

  private Token nextToken;

  private Deque<Token> line = new ArrayDeque<>();

  public SpeedMarkerTokenManager(SimpleCharStream stream) {
    super(stream);
  }

  public SpeedMarkerTokenManager(SimpleCharStream stream, int lexState) {
    super(stream, lexState);
  }

  @Override
  public Token getNextToken() {
    for (Token token = fetchToken(); ; token = fetchToken()) {
      switch (token.kind) {
        case EXNEWLINE:
        case TAG_T:
        case TAG_LT:
        case TAG_RT:
        case TAG_NT:
          break;
        default:
          return token;
      }
    }
  }

  protected Token fetchToken() {
    // Init at start.
    if (nextToken == null) {
      nextToken = super.getNextToken();
    }
    while (line.isEmpty() && nextToken.kind != EOF) {
      refillLine();
    }
    if (line.isEmpty()) {
      return nextToken;
    }
    return line.pop();
  }

  private void refillLine() {
    line.addLast(nextToken);
    boolean tag_t = nextToken.kind == TAG_T;
    boolean tag_lt = nextToken.kind == TAG_LT;
    boolean tag_rt = nextToken.kind == TAG_RT;
    boolean tag_nt = nextToken.kind == TAG_NT;
    for (nextToken = super.getNextToken();
         nextToken.kind != EOF && nextToken.kind != NEWLINE && nextToken.kind != EXNEWLINE;
         nextToken = super.getNextToken()) {
      tag_t |= nextToken.kind == TAG_T;
      tag_lt |= nextToken.kind == TAG_LT;
      tag_rt |= nextToken.kind == TAG_RT;
      tag_nt |= nextToken.kind == TAG_NT;
      line.addLast(nextToken);
    }
    if (tag_nt) {
      return;
    }
    tag_rt |= !line.isEmpty() && (line.peekLast().kind == TAG_END || line.peekLast().kind == EMPTY_TAG_END);
    if ((tag_t || tag_rt) &&
        nextToken.kind != EOF) {
      nextToken = super.getNextToken();
    }
    if ((tag_t || tag_lt) &&
        !line.isEmpty() && (line.peekFirst().kind == NEWLINE || line.peekFirst().kind == EXNEWLINE)) {
      line.pop();
    }
  }
}