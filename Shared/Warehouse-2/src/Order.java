
import java.util.*;
import java.io.*;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    private String clientId;
    private List itemList = new LinkedList();

    public Order(String clientId) {
        this.clientId = clientId;
    }

    public List getItemList() {
        return itemList;
    }

    public void addItem(Item i){
        itemList.add(i);
    }

    public String getClientId() {
        return clientId;
    }
    

}
