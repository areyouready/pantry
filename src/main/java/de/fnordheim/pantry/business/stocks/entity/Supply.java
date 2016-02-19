package de.fnordheim.pantry.business.stocks.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sebastianbasner on 17.02.16.
 */
@Entity
@NamedQuery(name = Supply.findAll, query = "SELECT s FROM Supply s")
public class Supply {

   @Id
   @GeneratedValue
   private long id;

   private long version;

   static final String PREFIX = "de.fnordheim.pantry.business.stocks.entity.Supply";
   public static final String findAll = PREFIX + "findAll";

   @NotNull
   @Size(min = 2, max = 256)
   private String item;
   private int weight;
   private int quantity;
   private Date expiryDate;
   private Date freezeDate;

   public Supply(String item, int weight, int quantity, Date expiryDate) {
      this.item = item;
      this.weight = weight;
      this.quantity = quantity;
      this.expiryDate = expiryDate;
   }

   public Supply(String item, int weight, int quantity, Date expiryDate, Date freezeDate) {
      this.item = item;
      this.weight = weight;
      this.quantity = quantity;
      this.expiryDate = expiryDate;
      this.freezeDate = freezeDate;
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
      return new SimpleDateFormat("dd.MM.yyyy").format(expiryDate);
   }

   public Date getExpiryDate() {
      return expiryDate;
   }

   public void setExpiryDate(Date expiryDate) {
      this.expiryDate = expiryDate;
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
