package de.soapwars.speedmarker.ast.node;

import de.soapwars.speedmarker.Node;
import de.soapwars.speedmarker.SpeedMarkerModel;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
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
  public boolean isStatic() {
    return attempt.isStatic();
  }

  @Override
  public void render(Writer writer, SpeedMarkerModel model) throws IOException {
    StringWriter buffer = new StringWriter();
    try {
      attempt.render(buffer, model);
      buffer.close();
    } catch (Exception ex) {
      recover.render(writer, model);
      return;
    }
    writer.append(buffer.toString());
  }

  @Override
  public String debugTree() {
    return "[ 'attempt' ," + attempt.debugTree() + "," + recover.debugTree() + " ]";
  }
}
