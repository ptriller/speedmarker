package de.soapwars.speedmarker.valaue;

import de.soapwars.speedmarker.Value;
import de.soapwars.speedmarker.ValueType;

import javax.annotation.Nonnull;

/**
 * Created by ptriller on 26.02.2017.
 */
public class StringValue implements Value {

  @Nonnull
  private final String value;

  public StringValue(@Nonnull String value) {
    this.value = value;
  }

  @Override
  public ValueType getType() {
    return ValueType.STRING;
  }

  @Override
  @Nonnull
  public String getValue() {
    return value;
  }
}
