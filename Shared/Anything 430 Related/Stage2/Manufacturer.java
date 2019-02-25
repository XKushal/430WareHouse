import java.util.*;
import java.io.*;
public class Manufacturer implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String id;
  private List ManufacturerList = new LinkedList();
  private List transactions = new LinkedList();
  private static final String MANUFACTURER_STRING = "M";
  
  public  Manufacturer (String name) {
    this.name = name;
    id = MANUFACTURER_STRING + (ManufacturerIdServer.instance()).getId();
  }
  public String getName() {
    return name;
  }
  public String getId() {
    return id;
  }
  public void setName(String newName) {
    name = newName;
  }
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public String toString() {
    String string = "Manufacturer name: " + name + ", id: " + id;
    return string;
  }
}
