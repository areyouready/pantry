package de.fnordheim.pantry.business.stocks.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sebastianbasner on 17.02.16.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Supply.findAll, query = "SELECT s FROM Supply s"),
        @NamedQuery(name = Supply.findByType, query = "SELECT s FROM Supply s WHERE s.supplyType LIKE :supplyType")})
public class Supply {

    @Id
    @GeneratedValue
    private long id;

    private long version;

    private static final String PREFIX = "de.fnordheim.pantry.business.stocks.entity.Supply";
    public static final String findAll = PREFIX + "findAll";
    public static final String findByType = PREFIX + "findByType";

    @NotNull
    @Size(min = 2, max = 256)
    private String item;
    private int weight;
    private int quantity;
    private Date expiryDate;
    private Date freezeDate;
    @Enumerated(EnumType.STRING)
    private SupplyType supplyType;

    public Supply(String item, int weight, int quantity, Date expiryDate, SupplyType supplyType) {
        this.item = item;
        this.weight = weight;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.supplyType = supplyType;
    }

    public Supply(String item, int weight, int quantity, Date expiryDate, Date freezeDate, SupplyType supplyType) {
        this.item = item;
        this.weight = weight;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.freezeDate = freezeDate;
        this.supplyType = supplyType;
    }

    public Supply() {
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFormattedExpiryDate() {
        if (expiryDate != null) {
            return new SimpleDateFormat("dd.MM.yyyy").format(expiryDate);
        }
        return " ";
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SupplyType getSupplyType() {
        return supplyType;
    }

    public void setSupplyType(SupplyType supplyType) {
        this.supplyType = supplyType;
    }

    //TODO[SB] implement crosscheck where isValid checks if freeze date is empty when supply type is pantry
//    @Override
//    public boolean isValid() {
//        if (this.supplyType == SupplyType.PANTRY) {
//            return this.freezeDate == null;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "Supply{" +
                "id=" + id +
                ", version=" + version +
                ", item='" + item + '\'' +
                ", weight=" + weight +
                ", quantity=" + quantity +
                ", expiryDate=" + expiryDate +
                ", freezeDate=" + freezeDate +
                '}';
    }

}
