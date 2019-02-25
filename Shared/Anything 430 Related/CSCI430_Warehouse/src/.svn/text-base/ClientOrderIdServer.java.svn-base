import java.io.*;
public class ClientOrderIdServer implements Serializable {
  private int idCounter;
  private static ClientOrderIdServer server;
  private ClientOrderIdServer() {
    idCounter = 1;
  }
  public static ClientOrderIdServer instance() {
    if (server == null) {
      return (server = new ClientOrderIdServer());
    } else {
      return server;
    }
  }
  public int getId() {
    return idCounter++;
  }
  public String toString() {
    return ("IdServer" + idCounter);
  }
  public static void retrieve(ObjectInputStream input) {
    try {
      server = (ClientOrderIdServer) input.readObject();
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
  private void writeObject(java.io.ObjectOutputStream output) throws IOException {
    try {
      output.defaultWriteObject();
      output.writeObject(server);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
    try {
      input.defaultReadObject();
      if (server == null) {
        server = (ClientOrderIdServer) input.readObject();
      } else {
        input.readObject();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
}