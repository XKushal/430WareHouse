import java.util.*;
import java.io.*;
public class ClientList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List members = new LinkedList();
  private static ClientList memberList;
  private ClientList() {
  }
  public static ClientList instance() {
    if (memberList == null) {
      return (memberList = new ClientList());
    } else {
      return memberList;
    }
  }

  public boolean insertMember(Client member) {
    members.add(member);
    return true;
  }

  public Iterator getMembers(){
     return members.iterator();
  }
  
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(memberList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (memberList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (memberList == null) {
          memberList = (ClientList) input.readObject();
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
    return members.toString();
  }
}
