package de.fnordheim.pantry.business.stocks.boundary;

import de.fnordheim.pantry.business.stocks.entity.Supply;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by sebastianbasner on 18.02.16.
 */
@Stateless
public class SupplyManager {

   @PersistenceContext
   private EntityManager em;

   public List<Supply> all() {
      return this.em.createNamedQuery(Supply.findAll, Supply.class).getResultList();
   }


   public List<Supply> findByType(final Class c) {
      return this.em.createNamedQuery(Supply.findByType, Supply.class).setParameter("supplyType", c).getResultList();
   }

   public Supply findById(long id) {
      return this.em.find(Supply.class, id);
   }

   public void delete(long id) {
      final Supply reference = this.em.getReference(Supply.class, id); //never becomes null unlike find
      this.em.remove(reference);
   }

   public Supply save(Supply supply) {
      return this.em.merge(supply);
   }
}
