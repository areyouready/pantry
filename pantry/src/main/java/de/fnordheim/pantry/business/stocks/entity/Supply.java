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
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="SUPPLY_TYPE")
@NamedQueries({
        @NamedQuery(name = Supply.findAll, query = "SELECT s FROM Supply s"),
        @NamedQuery(name = Supply.findByType, query = "SELECT s FROM Supply s WHERE TYPE(s) = :supplyType")})
public abstract class Supply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long version;

    private static final String PREFIX = "de.fnordheim.pantry.business.stocks.entity.Supply";
    public static final String findAll = PREFIX + "findAll";
    public static final String findByType = PREFIX + "findByType";

    //calling validate manually needs message after @NotNull while bound parameters in JSF need message behind @Size
    @NotNull(message = "name must be between 2 and 256 characters.")
    @Size(min = 2, max = 256, message = "name must be between 2 and 256 characters.")
    private String item;
    private int weight;
    private int quantity;
    private Date expiryDate;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Supply{" +
                "id=" + id +
                ", version=" + version +
                ", item='" + item + '\'' +
                ", weight=" + weight +
                ", quantity=" + quantity +
                ", expiryDate=" + expiryDate +'}';
    }
}
