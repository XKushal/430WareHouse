/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse;

/**
 *
 * @author 21cha
 */
import java.util.*;
import java.io.*;
public class ClientList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List clients = new LinkedList();
  private static ClientList clientlist;
  private ClientList() {
  }
  public static ClientList instance() {
    if (clientlist == null) {
      return (clientlist = new ClientList());
    } else {
      return clientlist;
    }
  }

  public boolean insertClient(Client client) {
    clients.add(client);
    return true;
  }

  public Iterator getClient(){
     return clients.iterator();
  }
  
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(clientlist);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (clientlist != null) {
        return;
      } else {
        input.defaultReadObject();
        if (clientlist == null) {
          clientlist = (ClientList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  public String toString() {
    return clients.toString();
  }
}
