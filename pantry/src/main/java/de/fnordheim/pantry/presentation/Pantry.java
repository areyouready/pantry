package de.fnordheim.pantry.presentation;

import de.fnordheim.pantry.business.stocks.boundary.SupplyManager;
import de.fnordheim.pantry.business.stocks.entity.Supply;
import de.fnordheim.pantry.business.stocks.entity.SupplyType;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by sebastianbasner on 17.02.16.
 */
@ManagedBean
@ViewScoped
public class Pantry implements Serializable {

    private static final long serialVersionUID = -271980965670286390L;

    @Inject
    SupplyManager boundary;

    private Supply supply;

    @Inject
    Validator validator;
    private List<Supply> supplyList;

    @PostConstruct
    public void init() {
        this.supply = new Supply();
    }

    private void showValidationError(String content) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, content, content);
        FacesContext.getCurrentInstance().addMessage("", message);
    }

    public Object save() {
        final Set<ConstraintViolation<Supply>> violations = this.validator.validate(this.supply);

        for(ConstraintViolation violation : violations) {
            this.showValidationError(violation.getMessage());
        }

        if (violations.isEmpty()) {
            this.supply.setSupplyType(SupplyType.PANTRY);
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
        System.out.println("test");
        this.supply = null;
    }

    public List<Supply> getSupplies() {
        if(this.supplyList == null) {
            this.supplyList = this.boundary.findByType(SupplyType.PANTRY);
        }
        return this.supplyList;
    }

    public Supply getSupply() {
        if (this.supply == null) {
            this.supply = new Supply();
        }
        return this.supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }
}
