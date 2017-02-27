package de.soapwars.speedmarker.valaue;

import de.soapwars.speedmarker.Value;
import de.soapwars.speedmarker.ValueType;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

/**
 * Created by ptriller on 26.02.2017.
 */
public class NumberValue implements Value {

  @Nonnull
  private final BigDecimal value;

  public NumberValue(@Nonnull Number value) {
    this.value = new BigDecimal(value.toString());
  }

  @Override
  public ValueType getType() {
    return ValueType.NUMBER;
  }

  @Override
  public BigDecimal getValue() {
    return value;
  }
}
