import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class ClientOrder implements Serializable {
  private static final long serialVersionUID = 1L;
  private Client client;
  private Product product;
  private int quantity;
  private int backQuantity;
  private float totalCost;
  private Calendar date;
  private String id;
  private static final String CLIENT_ORDER_STRING = "CO";
  
  public  ClientOrder (Client client, Product product, int quantity, Calendar date) {
    this.client = client;
    this.product = product;
    this.quantity = quantity;
    this.backQuantity = 0;
    this.date = date;
    this.totalCost = product.getSellPrice() * quantity;
    id = CLIENT_ORDER_STRING + (ClientOrderIdServer.instance()).getId();
  }
  
  public Client getClient() {
    return client;
  }
  public Product getProduct() {
    return product;
  }
  public int getQuantity() {
    return quantity;
  }
  public int getBackQuantity() {
      return backQuantity;
  }
  public Calendar getDate() {
      return date;
  }
  public String getId() {
      return id;
  }
  public void setBackQuantity(int newBackQuantity) {
      backQuantity = newBackQuantity;
  }
    @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy");
    return("\nOrder ID: " + id + "\tClient: " + client.getName() + "\tProduct: " + product.getName() + 
            "\tQuantity: " + quantity + "\tBackorder Quantity: " + backQuantity + "\tDate: " + sdf.format(date.getTime())
            + "\tTotal Cost: " + totalCost);
  }
  
  /*
  public boolean issue(Product book) {
    if (booksBorrowed.add(book)) {
      orders.add(new Client_Order ("Book issued ", book.Product.super.getName()));
      return true;
    }
    return false;
  }
  public boolean returnBook(Product book) {
    if ( booksBorrowed.remove(book)){
      orders.add(new Client_Order ("Book returned ", book.Product.super.getName()));
      return true;
    }
    return false;
  }

  public boolean renew(Product book) {
    for (ListIterator iterator = booksBorrowed.listIterator(); iterator.hasNext(); ) {
      Product aBook = (Product) iterator.next();
      String id = aBook.getId();
      if (id.equals(book.getId())) {
        orders.add(new Client_Order ("Book renewed ",  book.Product.super.getName()));
        return true;
      }
    }
    return false;
  }
  public Iterator getBooksIssued() {
    return (booksBorrowed.listIterator());
  }
  public void placeHold(Manufacturer hold) {
    orders.add(new Client_Order ("Hold Placed ", hold.getBook().Product.super.getName()));
    booksOnHold.add(hold);
  }
  public boolean removeHold(String bookId) {
    for (ListIterator iterator = booksOnHold.listIterator(); iterator.hasNext(); ) {
      Manufacturer hold = (Manufacturer) iterator.next();
      String id = hold.getBook().getId();
      if (id.equals(bookId)) {
        orders.add(new Client_Order ("Hold Removed ", hold.getBook().Product.super.getName()));
        iterator.remove();
        return true;
      }
    }
    return false;
  }
  public Iterator getTransactions(Calendar date) {
    List result = new LinkedList();
    for (Iterator iterator = orders.iterator(); iterator.hasNext(); ) {
      Client_Order transaction = (Client_Order) iterator.next();
      if (transaction.onDate(date)) {
        result.add(transaction);
      }
    }
    return (result.iterator());
  }
  * *
  */
}