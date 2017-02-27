package de.soapwars.speedmarker.ast.node;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.SpeedMarkerModel;

import javax.annotation.Nonnull;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by ptriller on 12.02.2017.
 */
public class IncludeNode implements Node {

  @Nonnull
  private Expression path;

  @Nonnull
  private Map<String, Expression> options = new HashMap<>();

  public IncludeNode(@Nonnull Expression path, @Nonnull Map<String, Expression> options) {
    this.path = Objects.requireNonNull(path);
    this.options = Objects.requireNonNull(options);
  }

  @Override
  public boolean isStatic() {
    return false;
  }

  @Override
  public void render(Writer writer, SpeedMarkerModel model) {
  }

  @Override
  public String debugTree() {
    return "[ 'include', {" + path.debugTree() + "}"
        + options.entrySet().stream().map(e -> e.getKey() + "={" + e.getValue().debugTree() + "}")
        .collect(Collectors.joining(",", "{", "}"))
        + "]";
  }
}
