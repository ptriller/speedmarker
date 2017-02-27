package de.soapwars.speedmarker;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by ptriller on 11.02.2017.
 */
public interface Node {

  default boolean isStatic() {
    return false;
  }

  default void render(Writer writer, SpeedMarkerModel model) throws IOException {
  }

  String debugTree();
}
