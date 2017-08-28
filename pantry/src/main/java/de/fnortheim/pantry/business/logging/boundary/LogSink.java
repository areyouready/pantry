package de.fnortheim.pantry.business.logging.boundary;

/**
 * Created by sebastianbasner on 17.05.17.
 */
@FunctionalInterface
public interface LogSink {
   void log(String msg);
}
