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
public class Manufacturer implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private String phone;
  private String id;
  private List ManufacturerList = new LinkedList();
  private static final String MANUFACTURER_STRING = "M";
  private List<Product> Product = new LinkedList<Product>();
  
  public  Manufacturer (String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    id = MANUFACTURER_STRING + (ManufacturerIDServer.instance()).getId();
  }
  
  public boolean assignProduct(Product product){
		if (Product.add(product))
		{
			return true;
		}
		else
		{
			return false;
		}
   }
  public boolean removeProduct(Product product){
		if (Product.remove(product))
		{
			return true;
		}
		else
		{
			return false;
		}
  }
  
  public Iterator<Product> getProducts(){
		return Product.iterator();
  }

  public String getName() {
    return name;
  }
  public String getAddress(){
    return address;
  }
  public String getPhone(){
    return phone;
  }
  public String getId() {
    return id;
  }
  public void setName(String newName) {
    name = newName;
  }
  public void setAddress(String newAddress) {
    address = newAddress;
  }
  public void setPhone(String newPhone) {
    phone = newPhone;
  }
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public String toString() {
    String string = " Name:" + name + "   address:"+ address + "    phone:"+ phone +"  ID:"+id +"  ";
    return string;
  }
}