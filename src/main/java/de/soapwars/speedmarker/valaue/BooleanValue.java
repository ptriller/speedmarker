package de.soapwars.speedmarker.valaue;

import de.soapwars.speedmarker.Value;
import de.soapwars.speedmarker.ValueType;

import javax.annotation.Nonnull;

/**
 * Created by ptriller on 26.02.2017.
 */
public class BooleanValue implements Value {

  @Nonnull
  private final Boolean value;

  public BooleanValue(@Nonnull Boolean value) {
    this.value = value.booleanValue() ? Boolean.TRUE : Boolean.FALSE;
  }

  @Override
  public ValueType getType() {
    return ValueType.BOOLEAN;
  }

  @Override
  @Nonnull
  public Boolean getValue() {
    return value;
  }
}
