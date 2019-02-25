/*---------------------------------------------------------
/ CSCI 430                        Class Author: Jeff O'Keefe
/ St. Cloud State University
/ Dr. Sarnath
/
/ Project 1 - Invoice Class
/--------------------------------------------------------*/

import java.util.*;
import java.io.*;
public class Invoice implements Serializable {
  private static final long serialVersionUID = 1L;
  private final static int SIZE = 100;
  private String[] pid = new String[SIZE];
  private int[] price = new int[SIZE];
  private int[] quantity = new int[SIZE];
  private int[] totalPrice = new int[SIZE];
  private String[] shipped = new String[SIZE];
  private String id;
  private static final String INVOICE_STRING = "I";
  
  public Invoice () {
    id = INVOICE_STRING + (InvoiceIdServer.instance()).getId();
  }

  public static int i;
  
  public void setShipped(int j) {
	shipped[j] = "Y";
  }
  public String getPid(int i) {
    return pid[i];
  }
  public int getQuantity(int i) {
    return quantity[i];
  }
  public int getPrice(int i) {
    return price[i];
  }
  public int getTotalPrice(int i) {
	return totalPrice[i];
  }
  public String getShipped(int i) {
    return shipped[i];
  }
  public String getId() {
    return id;
  }
  public int getI() {
    return i;
  }
  public void acceptCustomerOrder(String newPid, int newQuantity, int newPrice, int j)
  {
	pid[j] = newPid;
	quantity[j] = newQuantity;
	price[j] = newPrice;
	totalPrice[j] = price[j]*quantity[j];
	shipped[j] = "N";
	i++;
  }
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public String toString(int j) {
    String string = "Product Id: " + pid[j] + " Price: " + price[j] + " Quantity: " + quantity[j] + " Total price: " + totalPrice[j] + " Shipped: " + shipped[j];
    return string;
  }
}
