package warehouse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 21cha
 */
import java.util.*;
import java.io.*;
public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String id;
  private int quantity;
  private List<Manufacturer> productManufacturer = new LinkedList<Manufacturer>();
  private List<Float> productPrices = new LinkedList<Float>();
  private static final String PRODUCT_STRING = "P";
  public  Product (String name, int quantity) {
    this.name = name;
    this.quantity = quantity;
    id = PRODUCT_STRING + (ProductIDServer.instance()).getId();
  }

  public String getName() {
        return name;
  }
  
  public int getQuantity() {
        return quantity;
  }
  
  public String getId() {
        return id;
  }
  
  public void setName(String newName) {
        name = newName;
  }
  
  public void setQuantity(int newQuantity) {
        quantity = newQuantity;
  }
  
  public boolean equals(String id) {
        return this.id.equals(id);
  }
  
  public boolean link(Manufacturer supplier) {
        return productManufacturer.add(supplier) ? true : false;
  }

    //It unassigns the relationship between products and (Manufacturer)suppliers.
  public boolean unlink(Manufacturer supplier) {
        return productManufacturer.remove(supplier) ? true : false;
  }

    public Iterator<Manufacturer> getManufacturer(){
  	return productManufacturer.iterator();
  }

    public int SearchSupplylist(Manufacturer manufacturer)
    {
      int i = 0;
      for (; i <= productManufacturer.size()-1; i++)
      {
        if ((productManufacturer.get(i)) == manufacturer)
        {
          return i;
        }
      }
      i = -1;
      return i;
    }

    public Boolean addPrice(Float price){
      return productPrices.add(price) ? true : false;
    }

    public Boolean removePrice(int position){
      if (productPrices.remove(position) >= 0)
      {
        return true;
      }
      else
      {
        return false;
      }
    }

    public Iterator<Float> getPrices(){
  		return productPrices.iterator();
  	}

    public double moneyRound(float num) {
        return Math.round(num * 100.00) / 100.00;
    }
  public String toString() {
        return "Product: " + name + " ID: " + id + " Qty: " + quantity;

    }
}