package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.Environment;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by ptriller on 06.07.2015.
 */
public class ConditionalBlock {

   public Node condition;

   public Node block;

   public ConditionalBlock(Node condition, Node block) {
      this.condition = condition;
      this.block = block;
   }


}
