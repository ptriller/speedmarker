package de.soapwars.speedmarker.ast.builder;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.ParseException;
import de.soapwars.speedmarker.Token;
import de.soapwars.speedmarker.ast.node.IncludeNode;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Created by ptriller on 12.02.2017.
 */
public class IncludeNodeBuilder {

  private static final Set<String> VALID_OPTIONS;

  static {
    HashSet<String> set = new HashSet<>();
    set.add("encoding");
    set.add("parse");
    set.add("ignore_missing");
    VALID_OPTIONS = Collections.unmodifiableSet(set);
  }

  @Nonnull
  private Expression path;

  @Nonnull
  private Map<String, Expression> options = new HashMap<>();

  public void option(Token name, Expression value) throws ParseException {
    if (!VALID_OPTIONS.contains(name.image)) {
      throw new ParseException("Encountered \"" + name.image + "\" at line "
          + name.beginLine + ", column: " + name.beginColumn
          + "\nWas expecting:\n    \"encoding\", \"parse\" or \"ignore_missing}\" ...");

    }
    options.put(name.image, value);
  }

  public void path(Expression path) {
    this.path = path;
  }

  public IncludeNode build() {
    return new IncludeNode(path, options);
  }
}
