package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.ast.ContentNode;

/**
 * Created by ptriller on 12.02.2017.
 */
public class NoparseNodeBuilder {

  private StringBuilder builder = new StringBuilder();

  public void add(String segment) {
    builder.append(segment);
  }

  public ContentNode build() {
    return new ContentNode(builder.toString());
  }
}
