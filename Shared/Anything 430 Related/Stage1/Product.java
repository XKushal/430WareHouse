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
   private String quantity;
   private static final String PRODUCT_STRING ="P";
   private List suppliers = new LinkedList();
   public Product (String name, String id, String quantity) {
      this.name     = name;
      this.quantity = quantity;
      this.id       = id;
   }

   public String getName(){
      return name;
   }
   public String getId() {
      return id;
   }
   public String getQuantity() {
      return quantity;
   }
   public void setName(String newName){
      name = newName;
   }
   public void setId(String newId){
      id = newId;
   }
   public void setQuantity(String newQuantity) {
      quantity = newQuantity;
   }
   public boolean equals(String id) {
      return this.id.equals(id);
   }
   public String toString() {
      String PString = "Product id " + id + " Product name " + name + " quantity " + quantity;
      return PString;
   }
}

