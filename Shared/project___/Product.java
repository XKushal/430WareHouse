import java.util.*;
import java.lang.*;
import java.io.*;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String prodName;
    private int quantity;
    private double price;
    private String id;
    private List productManufacturer = new LinkedList();
    private static final String PRODUCT_STRING = "P";


    public Product(String prodName, int quantity, double price) {
        this.prodName = prodName;
        this.quantity = quantity;
        this.price = moneyRound(price);
        id = PRODUCT_STRING + (ProductIDServer.instance()).getID();
    }

    public String getProdName() {
        return prodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
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

    public double moneyRound(double num) {
        return Math.round(num * 100.0) / 100.0;
    }

    public String toString() {
        return "Product: " + prodName + " ID: " + id + " Price: $" + price + " Qty: " + quantity;

    }
}
