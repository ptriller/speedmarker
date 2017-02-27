package de.soapwars.speedmarker;

import de.soapwars.speedmarker.ast.NodeBuilderFactory;

import java.io.InputStream;
import java.io.Reader;

/**
 * Created by ptriller on 26.02.2017.
 */
public class SpeedMarkerParser extends SpeedMarkerParserBase {

  public SpeedMarkerParser(NodeBuilderFactory builderFactory, InputStream stream) {
    super(stream);
    this.builderFactory = builderFactory;
  }

  public SpeedMarkerParser(NodeBuilderFactory builderFactory, InputStream stream, String encoding) {
    super(stream, encoding);
    this.builderFactory = builderFactory;
  }

  public SpeedMarkerParser(NodeBuilderFactory builderFactory, Reader stream) {
    super(stream);
    this.builderFactory = builderFactory;
  }

  public SpeedMarkerParser(NodeBuilderFactory builderFactory, SpeedMarkerParserBaseTokenManager tm) {
    super(tm);
    this.builderFactory = builderFactory;
  }
}
