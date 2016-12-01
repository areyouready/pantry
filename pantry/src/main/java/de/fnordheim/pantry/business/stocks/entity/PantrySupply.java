package de.fnordheim.pantry.business.stocks.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by sebastianbasner on 22.11.16.
 */
@Entity
@DiscriminatorValue("PANTRY")
public class PantrySupply extends Supply {

    public PantrySupply() {
    }

    public PantrySupply(String item, int weight, int quantity, Date expiryDate) {
        this.setItem(item);
        this.setWeight(weight);
        this.setQuantity(quantity);
        this.setExpiryDate(expiryDate);
    }

}
