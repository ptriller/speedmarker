package de.soapwars.speedmarker;

/**
 * Created by ptriller on 04.07.2015.
 */
public class SpeedMarkerParseException extends RuntimeException {

   public SpeedMarkerParseException() {
   }

   public SpeedMarkerParseException(String message) {
      super(message);
   }

   public SpeedMarkerParseException(String message, Throwable cause) {
      super(message, cause);
   }

   public SpeedMarkerParseException(Throwable cause) {
      super(cause);
   }

   public SpeedMarkerParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
