package de.fnordheim.pantry.business.stocks.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by sebastianbasner on 18.02.16.
 */
@Stateless
public class SupplyManager {

   @PersistenceContext
   EntityManager em;
}
