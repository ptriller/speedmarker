package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import javax.annotation.Nonnull;
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
  private final Node value;

  public AssignNode(@Nonnull String variableName, @Nonnull String scope, @Nonnull Node value) {
    this.variableName = Objects.requireNonNull(variableName);
    this.scope = Objects.requireNonNull(scope);
    this.value = Objects.requireNonNull(value);
  }

  @Override
  public String debugTree() {
    return "[ 'assign' , '" + variableName + "' = " + value.debugTree() + ']';
  }
}
