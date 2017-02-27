package de.soapwars.speedmarker;

/**
 * Created by ptriller on 26.02.2017.
 */
public class SpeedMarkerException extends RuntimeException {

  public SpeedMarkerException() {
  }

  public SpeedMarkerException(String message) {
    super(message);
  }

  public SpeedMarkerException(String message, Throwable cause) {
    super(message, cause);
  }

  public SpeedMarkerException(Throwable cause) {
    super(cause);
  }

  public SpeedMarkerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
