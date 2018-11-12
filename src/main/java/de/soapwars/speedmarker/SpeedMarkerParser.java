package de.soapwars.speedmarker;

import de.soapwars.speedmarker.ast.NodeBuilderFactory;

import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * Created by ptriller on 26.02.2017.
 */
public class SpeedMarkerParser extends SpeedMarkerParserBase {

  public SpeedMarkerParser(NodeBuilderFactory builderFactory, InputStream stream) {
    super(new SpeedMarkerTokenManager(new SimpleCharStream(stream)));
    this.builderFactory = builderFactory;
  }

  public SpeedMarkerParser(NodeBuilderFactory builderFactory, InputStream stream, String encoding)
      throws UnsupportedEncodingException {
    super(new SpeedMarkerTokenManager(new SimpleCharStream(stream, encoding)));
    this.builderFactory = builderFactory;
  }

  public SpeedMarkerParser(NodeBuilderFactory builderFactory, Reader stream) {
    super(new SpeedMarkerTokenManager(new SimpleCharStream(stream)));
    this.builderFactory = builderFactory;
  }

  @Override
  public void ReInit(InputStream stream) {
    super.ReInit(new SpeedMarkerTokenManager(new SimpleCharStream(stream)));
  }

  @Override
  public void ReInit(InputStream stream, String encoding) {
    try {
      super.ReInit(new SpeedMarkerTokenManager(new SimpleCharStream(stream, encoding)));
    } catch (UnsupportedEncodingException exception) {
      throw new RuntimeException(exception);
    }
  }

  @Override
  public void ReInit(Reader stream) {
    super.ReInit(new SpeedMarkerTokenManager(new SimpleCharStream(stream)));
  }

}
