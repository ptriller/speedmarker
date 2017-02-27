package de.soapwars.speedmarker.ast.node;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.SpeedMarkerModel;

import javax.annotation.Nonnull;
import java.io.Writer;
import java.util.Objects;

/**
 * Created by ptriller on 12.02.2017.
 */
public class AssignNode implements Node {

  @Nonnull
  private final String variableName;

  @Nonnull
  private final String scope;

  @Nonnull
  private final Expression value;

  public AssignNode(@Nonnull String variableName, @Nonnull String scope, @Nonnull Expression value) {
    this.variableName = Objects.requireNonNull(variableName);
    this.scope = Objects.requireNonNull(scope);
    this.value = Objects.requireNonNull(value);
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
    return "[ 'assign' , '" + variableName + "' = {" + value.debugTree() + "}]";
  }
}
