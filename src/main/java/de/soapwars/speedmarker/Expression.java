package de.soapwars.speedmarker;

/**
 * Created by ptriller on 25.02.2017.
 */
public interface Expression {

  default boolean isStatic() {
    return false;
  }

  Value getValue(SpeedMarkerModel model);

  String debugTree();
}
