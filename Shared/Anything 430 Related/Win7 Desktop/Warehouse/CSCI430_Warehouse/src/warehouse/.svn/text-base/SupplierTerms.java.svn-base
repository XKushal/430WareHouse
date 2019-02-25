package warehouse;
import java.util.*;
import java.io.*;
public class SupplierTerms implements Serializable {
  private static final long serialVersionUID = 1L;
  private float supplyCost;
  private Product product;
  private Manufacturer manufacturer;
  
  public SupplierTerms (Manufacturer manufacturer, Product product, float supplyCost) {
    this.manufacturer = manufacturer;
    this.product = product;
    this.supplyCost = supplyCost;
  }
  public Product getProduct()
  {
      return product;
  }
  public Manufacturer getManufacturer()
  {
      return manufacturer;
  }
  public float getCost()
  {
      return supplyCost;
  }
  public String toString(){
      return ("\nSupply Cost: " + supplyCost + "\tProduct: " + product.getName() + "\tManufacturer: " + manufacturer.getName());
  }
}