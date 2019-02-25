import java.util.*;
import java.lang.*;
import java.io.*;
public class ProductList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List products = new LinkedList();
  private static ProductList catalog;
  private ProductList() {
  }
  public static ProductList instance() {
    if (catalog == null) {
      return (catalog = new ProductList());
    } else {
      return catalog;
    }
  }
  public Product search(String productId) {
    for (Iterator iterator = products.iterator(); iterator.hasNext(); ) {
      Product product = (Product) iterator.next();
      if (product.getId().equals(productId)) {
        return product;
      }
    }
            
    return null;
  }
  public boolean removeBook(String bookId) {
    Product book = search(bookId);
    if (book == null) {
      return false;
    } else {
      return products.remove(book);
    }
  }
  public boolean addProduct(Product book) {
    products.add(book);
    return true;
  }
  public Iterator getBooks() {
    return products.iterator();
  }
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(catalog);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (catalog != null) {
        return;
      } else {
        input.defaultReadObject();
        if (catalog == null) {
          catalog = (ProductList) input.readObject();
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
    return products.toString();
  }
}
