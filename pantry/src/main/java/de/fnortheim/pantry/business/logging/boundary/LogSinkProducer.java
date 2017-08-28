package de.fnortheim.pantry.business.logging.boundary;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

/**
 * Created by sebastianbasner on 17.05.17.
 */
public class LogSinkProducer {

   /**
    * Implementation of the LogSink interface.
    * @param ip the injection point
    * @return the logger configured to info
    */
   @Produces
   public LogSink produce(InjectionPoint ip) {
      final Class<?> injectionTarget = ip.getMember().getDeclaringClass();
      return Logger.getLogger(injectionTarget.getName())::info; //information about who is the calling class (BoundaryLogger i.e.)
   }
}
