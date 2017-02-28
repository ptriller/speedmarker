package de.soapwars.speedmarker.valaue;

import de.soapwars.speedmarker.ValueType;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

/**
 * Created by ptriller on 26.02.2017.
 */
public class NumberValue extends ValueBase {

  @Nonnull
  private final BigDecimal value;

  public NumberValue(@Nonnull Number value) {
    this.value = new BigDecimal(value.toString());
  }

  @Override
  public ValueType getPrimaryType() {
    return ValueType.NUMBER;
  }

  @Override
  public Number asNumber() {
    return value;
  }

  @Override
  public String toString() {
    return value.toString();
  }

}
