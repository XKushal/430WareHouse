import java.util.*;
import java.lang.*;
import java.io.*;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String prodName;
    private int quantity;
    private String id;
    private List<Manufacturer> productManufacturer = new LinkedList<Manufacturer>();
    private List<Double> productPrices = new LinkedList<Double>();
    private static final String PRODUCT_STRING = "P";


    public Product(String prodName, int quantity) {
        this.prodName = prodName;
        this.quantity = quantity;
        id = PRODUCT_STRING + (ProductIDServer.instance()).getID();
    }

    public String getProdName() {
        return prodName;
    }

    public int getQuantity() {
        return quantity;
    }


    public String getID() {
        return id;
    }

    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }

    // Used to check equality in searching
    public boolean equals(String id) {
        return this.id.equals(id);
    }

    // It assigns the relationship between products and (Manufacturer)suppliers.
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

    public int SearchSuppList(Manufacturer supplier)
    {
      int i = 0;
      for (; i <= productManufacturer.size()-1; i++)
      {
        if ((productManufacturer.get(i)) == supplier)
        {
          return i;
        }
      }
      i = -1;
      return i;
    }

    public Boolean addPrice(double price){
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

    public Iterator<Double> getPrices(){
  		return productPrices.iterator();
  	}

    public double moneyRound(double num) {
        return Math.round(num * 100.00) / 100.00;
    }

    public String toString() {
        return "Product: " + prodName + " ID: " + id + " Qty: " + quantity;

    }
}
