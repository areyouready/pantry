package de.fnortheim.pantry.presentation;

import de.fnortheim.pantry.business.stocks.boundary.SupplyManager;
import de.fnortheim.pantry.business.stocks.entity.FreezerSupply;
import de.fnortheim.pantry.business.stocks.entity.Supply;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by sebastianbasner on 17.02.16.
 */
@ManagedBean
@ViewScoped
public class Freezer {

   @Inject
   SupplyManager boundary;

   private FreezerSupply supply;

   @Inject
   Validator validator;

   private List<Supply> supplyList;

   @PostConstruct
   public void init() {
      this.supply = new FreezerSupply();
   }

   private void showValidationError(String content) {
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, content, content);
      FacesContext.getCurrentInstance().addMessage("", message);
   }

   public Object save() {
      final Set<ConstraintViolation<Supply>> violations = this.validator.validate(this.supply);

      violations.forEach(s -> this.showValidationError(s.getMessage()));

      if (violations.isEmpty()) {
         this.boundary.save(supply);
         this.supplyList = null; //forces reload of supplies to show the newly inserted supply
         this.supply = null;
      }
      return null;
   }

   public void delete() {
      this.boundary.delete(this.supply.getId());
      this.supplyList = null;
   }


   public void add() {
      this.supply = null;
   }

   public List<Supply> getSupplies() {
      if(this.supplyList == null) {
         this.supplyList = this.boundary.findByType(FreezerSupply.class);
      }
      return this.supplyList;
   }

   public FreezerSupply getSupply() {
      if (this.supply == null) {
         this.supply = new FreezerSupply();
      }
      return this.supply;
   }

   public void setSupply(FreezerSupply supply) {
      this.supply = supply;
   }
}
