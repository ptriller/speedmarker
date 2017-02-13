package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Node;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Created by ptriller on 12.02.2017.
 */
public class AttemptNode implements Node {

  @Nonnull
  private final Node attempt;

  @Nonnull
  private final Node recover;

  public AttemptNode(@Nonnull Node attempt, @Nonnull Node recover) {
    this.attempt = Objects.requireNonNull(attempt);
    this.recover = Objects.requireNonNull(recover);
  }

  @Override
  public String debugTree() {
    return "[ 'attempt' ," + attempt.debugTree() + "," + recover.debugTree() + " ]";
  }
}
