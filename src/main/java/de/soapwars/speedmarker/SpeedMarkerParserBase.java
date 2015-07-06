package de.soapwars.speedmarker;

/**
 * Created by ptriller on 29.06.2015.
 */
public class SpeedMarkerParserBase {


   public String parseEscape(String sequence) {
      int codepoint = Integer.parseInt(sequence.substring(2), 16);
      return  Character.toString((char)codepoint);
   }
}
