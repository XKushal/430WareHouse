
import java.util.*;
import java.lang.*;
import java.io.*;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    private String product;
    private String id;
    private String manufact;
    private double price;
    private int quantity;
    private List waitList = new LinkedList();

    public Product(String title, String id, double price, int quantity) {
        this.product = title;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }
    
        public String getProduct() {
        return product;
    }

    public Iterator getWaitList(){
        return waitList.iterator();
    }

    
    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void updateQuantity(int q) {
        quantity = q;
    }

    public void addToWaitList(Wait item) {
        waitList.add(item);
    }

    public String toString() {
        return "id " + id + " product " + product + " price " + price + " quantity " + quantity;
    }
}
