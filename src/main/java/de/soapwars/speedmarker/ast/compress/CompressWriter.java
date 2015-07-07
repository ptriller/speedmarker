package de.soapwars.speedmarker.ast.compress;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Peter Triller
 *         <p/>
 *         Copyright (c) 2015 GMX GmbH, Muenchen. All rights reserved.
 */
public class CompressWriter extends Writer {

  private Writer delegate;

  private char state = 'D';

  public CompressWriter(Writer delegate) {
    this.delegate = delegate;
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for (int idx = off; idx < off + len; idx++) {
      if (Character.isWhitespace(cbuf[idx])) {
        if (state != 'D' && cbuf[idx] == '\n') {
          state = '\n';
        } else if (state == 'X') {
          state = ' ';
        }
      } else {
        if (state != 'D' && state != 'X') {
          delegate.write(state);
        }
        delegate.write(cbuf[idx]);
        state = 'X';
      }
    }

  }

  @Override
  public void flush() throws IOException {
    delegate.flush();
  }

  @Override
  public void close() throws IOException {
    delegate.close();
  }
}
