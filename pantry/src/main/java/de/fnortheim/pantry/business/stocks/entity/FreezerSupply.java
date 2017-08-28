package de.fnortheim.pantry.business.stocks.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sebastianbasner on 22.11.16.
 */
@Entity
@DiscriminatorValue("FREEZER")
public class FreezerSupply extends Supply {

    @NotNull(message = "freeze date can not be null.")
    private Date freezeDate;

    public String getFormattedFreezeDate() {
        if (freezeDate != null) {
            return new SimpleDateFormat("dd.MM.yyyy").format(freezeDate);
        }
        return " ";
    }

    public Date getFreezeDate() {
        return freezeDate;
    }

    public void setFreezeDate(Date freezeDate) {
        this.freezeDate = freezeDate;
    }


    @Override
    public String toString() {
        return "FreezerSupply{" +
                "freezeDate=" + freezeDate +
                '}';
    }
}
