/*---------------------------------------------------------
/ CSCI 430                        Class Author: Jeff Okeefe
/ St. Cloud State University
/ Dr. Sarnath
/
/ Project 1 - Product Class
/--------------------------------------------------------*/

import java.util.*;
import java.io.*;
public class Product implements Serializable {
   private static final long serialVersionUID = 1L;
   private String name;
   private String id;
   private int quantity;
   private int price;
   private static final String PRODUCT_STRING ="P";
   private List waitlists = new LinkedList();
   public Product (String name, String id, int quantity, int price) {
      this.name     = name;
      this.quantity = quantity;
      this.id       = id;
	  this.price    = price;
   }

   public void waitlistItem(Waitlist waitlist) {
	waitlists.add(waitlist);
   }
   public String getName(){
      return name;
   }
   public String getId() {
      return id;
   }
   public int getQuantity() {
      return quantity;
   }
   public int getPrice() {
	  return price;
	}
   public void setName(String newName){
      name = newName;
   }
   public void setId(String newId){
      id = newId;
   }
   public void setQuantity(int newQuantity) {
      quantity = newQuantity;
   }
   public void setPrice(int newPrice) {
	  price = newPrice;
	}
   public boolean equals(String id) {
      return this.id.equals(id);
   }
   public String toString() {
      String PString = "Product id " + id + " Product name " + name + " quantity " + quantity + " price " + price;
      return PString;
   }
}

