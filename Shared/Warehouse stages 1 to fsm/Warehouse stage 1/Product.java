import java.util.*;
import java.lang.*;
import java.io.*;
public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  private String title;
  private String id;


  public Product(String title, String id) {
    this.title = title;
    this.id = id;
  }
/*
  public String getAuthor() {
    return author;
  }*/
  public String getTitle() {
    return title;
  }
  public String getId() {
    return id;
  }

  public String toString() {
      return "product " + title + " id " + id;
  }
}
