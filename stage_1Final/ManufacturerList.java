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
import warehouse.Manufacturer;
import java.util.*;
import java.io.*;
public class ManufacturerList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List manufacturers = new LinkedList();
  private static ManufacturerList manufacturerList;
  
  private ManufacturerList() {
  }
  public static ManufacturerList instance() {
    if (manufacturerList == null) {
      return (manufacturerList = new ManufacturerList());
    } else {
      return manufacturerList;
    }
  }
  public boolean insertManufacturer(Manufacturer manufacturer) {
    manufacturers.add(manufacturer);
    return true;
  }

  public Iterator getManufacturers(){
     return manufacturers.iterator();
  }
  
  public Manufacturer search(String manufacturerID)
  {
      for (Iterator iterator = manufacturers.iterator(); iterator.hasNext(); )
      {
          Manufacturer manufacturer = (Manufacturer) iterator.next();
          if (manufacturer.getId().equals(manufacturerID))
          {
              return manufacturer;
          }
      }
      return null;
  }
  
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(manufacturerList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (manufacturerList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (manufacturerList == null) {
          manufacturerList = (ManufacturerList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  public String toString() {
    return manufacturers.toString();
  }
}
