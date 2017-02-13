package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by ptriller on 12.02.2017.
 */
public class IncludeNode implements Node {

  @Nonnull
  private Node path;

  @Nonnull
  private Map<String, Node> options = new HashMap<>();

  public IncludeNode(@Nonnull Node path, @Nonnull Map<String, Node> options) {
    this.path = Objects.requireNonNull(path);
    this.options = Objects.requireNonNull(options);
  }


  @Override
  public String debugTree() {
    return "[ 'include', " + path.debugTree()
        + options.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue().debugTree())
        .collect(Collectors.joining(",", "{", "}"))
        +"]";
  }
}
