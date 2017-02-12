package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Created by ptriller on 11.02.2017.
 */
public class ContentNode implements Node {

  @Nonnull
  private final String content;

  public ContentNode(@Nonnull String content) {
    this.content = Objects.requireNonNull(content);
  }


  @Nonnull
  public String getContent() {
    return content;
  }

  @Override
  public String debugTree() {
    return "\"" + content + "\"";
  }
}
