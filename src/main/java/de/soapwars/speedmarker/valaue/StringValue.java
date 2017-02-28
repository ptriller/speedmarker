package de.soapwars.speedmarker.valaue;

import de.soapwars.speedmarker.ValueType;

import javax.annotation.Nonnull;

/**
 * Created by ptriller on 26.02.2017.
 */
public class StringValue extends ValueBase {

  @Nonnull
  private final String value;

  public StringValue(@Nonnull String value) {
    this.value = value;
  }

  @Override
  public ValueType getPrimaryType() {
    return ValueType.STRING;
  }

  @Override
  public String asString() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }

}
