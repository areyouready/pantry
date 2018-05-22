package de.fnortheim.pantry.presentation;

import de.fnortheim.pantry.business.stocks.boundary.SupplyManager;
import de.fnortheim.pantry.business.stocks.entity.PantrySupply;
import de.fnortheim.pantry.business.stocks.entity.Supply;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
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

    private PantrySupply supply;

    @Inject
    Validator validator;

    private List<Supply> supplyList;

    @PostConstruct
    public void init() {
        this.supply = new PantrySupply();
    }

    private void showValidationError(String content) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, content, content);
        FacesContext.getCurrentInstance().addMessage("", message);
    }

    public int dateSort(Object o1, Object o2) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.GERMANY);
        final LocalDate date1 = o1 == null || (o1.equals(" ")) ? null : LocalDate.parse((String) o1, dtf);
        final LocalDate date2 = o2 == null || (o2.equals(" ")) ? null : LocalDate.parse((String) o2, dtf);

        if (date1 == null ||  date2 != null && date1.isBefore(date2)) {
            return -1;
        }
        if (date2 == null || date1.isAfter(date2)) {
            return 1;
        }
        return 0;
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
        if (this.supplyList == null) {
            this.supplyList = this.boundary.findByType(PantrySupply.class);
        }
        return this.supplyList;
    }

    public Supply getSupply() {
        if (this.supply == null) {
            this.supply = new PantrySupply();
        }
        return this.supply;
    }

    public void setSupply(PantrySupply supply) {
        this.supply = supply;
    }
}
