import java.util.*;
import java.io.*;
public class Waitlist implements Serializable {
  private Product product;
  private int quantity;

  public Waitlist(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }
  public Product getProduct() {
    return product;
  }
  public int getQuantity() {
    return quantity;
  }
}

