import java.util.*;
import java.lang.*;
import java.io.*;
public class ProductList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List books = new LinkedList();
  private static ProductList product;
  private ProductList() {
  }
  public static ProductList instance() {
    if (product == null) {
      return (product = new ProductList());
    } else {
      return product;
    }
  }
  
  public boolean insertBook(Product book) {
    books.add(book);
    return true;
  }
  public Iterator getBooks() {
    return books.iterator();
  }
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(product);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (product != null) {
        return;
      } else {
        input.defaultReadObject();
        if (product == null) {
          product = (ProductList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      System.out.println("in Catalog readObject \n" + ioe);
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  public String toString() {
    return books.toString();
  }
}
