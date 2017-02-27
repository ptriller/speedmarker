package de.soapwars.speedmarker.ast.expression;

import de.soapwars.speedmarker.Expression;
import de.soapwars.speedmarker.SpeedMarkerModel;
import de.soapwars.speedmarker.Value;

/**
 * Created by ptriller on 25.02.2017.
 */
public class IdentifierExpression implements Expression {

  private String identifier;

  public IdentifierExpression(String identifier) {
    this.identifier = identifier;
  }

  @Override
  public boolean isStatic() {
    return false;
  }

  @Override
  public Value getValue(SpeedMarkerModel model) {
    return model.get(identifier);
  }

  @Override
  public String debugTree() {
    return identifier;
  }
}
