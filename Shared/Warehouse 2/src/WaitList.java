import java.util.*;
import java.io.*;
public class WaitList implements Serializable {
    private static final long serialVersionUID = 1L;
    private LinkedList itemList = new LinkedList();
    private static WaitList waitList;

    private WaitList(){
    }

    public static WaitList instance(){
        if(waitList == null){
            return (waitList = new WaitList());
        }
        else{
            return waitList;
        }
    }

    public void addItem(Item item){
        itemList.add(item);
    }


    public Iterator getWaitList(){
        return itemList.iterator();
    }

    public Item findProduct(String pId){
         for (Iterator iterator = itemList.iterator(); iterator.hasNext(); ) {
          Item item = (Item) iterator.next();
          if (item.getProductId().equals(pId)) {
            return item;
          }
        }
        return null;
    }

    public boolean remove(String clientId, String pId){
         for (Iterator iterator = itemList.iterator(); iterator.hasNext(); ) {
          Item item = (Item) iterator.next();
          if (item.getProductId().equals(pId) && item.getClientID().equals(clientId)) {
             return itemList.remove(item);
          }
        }
       return false;
    }

}

