package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

/**
 * Created by ptriller on 13.02.2017.
 */
public class ListNode implements Node {

  @Override
  public String debugTree() {
    return "[ 'list' ]";
  }
}
