import java.util.*;
import java.lang.*;
import java.io.*;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String prodName;
    private String id;
    private int quantity;
    private List<Supplier> productManufacturer = new LinkedList<Supplier>();
    private static final String PRODUCT_STRING = "P";


    public Product(String prodName,int quantity) {
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


    // Used to check equality in searching
    public boolean equals(String id) {
        return this.id.equals(id);
    }

    // It assigns the relationship between products and (Manufacturer)suppliers.
    public boolean link(Manufacturer supplier, int q, double p) {
        Supplier S = new Supplier(supplier, q, p);
        return productManufacturer.add(S) ? true : false;
    }

    //It unassigns the relationship between products and (Manufacturer)suppliers.
    public boolean unlink(Supplier supplier) {
        return productManufacturer.remove(supplier) ? true : false;
    }

    public Iterator<Supplier> getSuppliers(){
  		return productManufacturer.iterator();
  	}

    public Supplier SearchSuppList(Manufacturer supplier)
    {
      int i = 0;
      for (; i <= productManufacturer.size()-1; i++)
      {
        if ((productManufacturer.get(i).getManufacturer()) == supplier)
        {
          return productManufacturer.get(i);
        }
      }
      return null;
    }

    public List<Supplier> getList(){
      return productManufacturer;
    }

    public double moneyRound(double num) {
        return Math.round(num * 100.00) / 100.00;
    }

    public String toString() {
        return "Product: " + prodName + ". ID: " + id +". Qty: " + quantity;

    }
}
