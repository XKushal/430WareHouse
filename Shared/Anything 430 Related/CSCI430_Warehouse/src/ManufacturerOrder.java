import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class ManufacturerOrder implements Serializable {
  private static final long serialVersionUID = 1L;
  private Manufacturer manufacturer;
  private Product product;
  private int quantity;
  private String id;
  private static final String MANUFACTURER_ORDER_STRING = "MO";
  
  public  ManufacturerOrder (Manufacturer manufacturer, Product product, int quantity) {
    this.manufacturer = manufacturer;
    this.product = product;
    this.quantity = quantity;
    id = MANUFACTURER_ORDER_STRING + (ManufacturerOrderIdServer.instance()).getId();
  }
  
  public Manufacturer getManufacturer() {
    return manufacturer;
  }
  public Product getProduct() {
    return product;
  }
  public int getQuantity() {
    return quantity;
  }
  public String getId() {
      return id;
  }
    @Override
  public String toString() {
    return("\nManufacturer:" + manufacturer.getName() + "\tProduct: " + product.getName() + "\tQuantity: " + quantity + "\tID: " + id);
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