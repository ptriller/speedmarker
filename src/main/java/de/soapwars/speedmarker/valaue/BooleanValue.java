package de.soapwars.speedmarker.valaue;

import de.soapwars.speedmarker.ValueType;

import javax.annotation.Nonnull;

/**
 * Created by ptriller on 26.02.2017.
 */
public class BooleanValue extends ValueBase {
  @Nonnull
  private final Boolean value;

  public BooleanValue(@Nonnull Boolean value) {
    this.value = value.booleanValue() ? Boolean.TRUE : Boolean.FALSE;
  }

  @Override
  public ValueType getPrimaryType() {
    return ValueType.BOOLEAN;
  }

  @Override
  public Boolean asBoolean() {
    return value;
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
