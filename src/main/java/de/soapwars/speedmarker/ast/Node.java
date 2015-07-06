package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Environment;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Peter Triller
 *         <p/>
 *         Copyright (c) 2015 GMX GmbH, Muenchen. All rights reserved.
 */
public interface Node {


   Object value(Environment env);

   void output(Environment env, Writer out) throws IOException;

   void debug(Writer out, String indent) throws IOException;

   Node simplify();

   boolean isConstant();
}