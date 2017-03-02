package de.soapwars.speedmarker;

/**
 * Created by ptriller on 01.03.2017.
 */
public interface Filter {

  Value filter(SpeedMarkerModel model, Value value);
}
