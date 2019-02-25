import java.util.*;
import java.io.*;
public class Wait implements Serializable {
  private Client member;
  private int quantity;
  public Wait(Client member, int q) {
    this.member = member;
    this.quantity = q;
  }
  public Client getMember() {
    return member;
  }

  public int getQuantity() {
    return quantity;
  }
      public String toString() {
        return "Client Id: " +  member.getId() +   " quantity: " + quantity ;
    }


}

