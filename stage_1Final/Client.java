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
public class Client implements Serializable {
  private static final long serialVersionUID = 1L;
  private String clientname;
  private String clientaddress;
  private String clientphone;
  private String clientid;
  private static final String MEMBER_STRING = "C";

  public  Client (String name, String address, String phone) {
    this.clientname = name;
    this.clientaddress = address;
    this.clientphone = phone;
    clientid = MEMBER_STRING + (ClientIDServer.instance()).getId();
  }

  public String getClientName() {
    return clientname;
  }
  public String getClientPhone() {
    return clientphone;
  }
  public String getClientAddress() {
    return clientaddress;
  }
  public String getClientId() {
    return clientid;
  }
  public void setClientName(String newName) {
    clientname = newName;
  }
  public void setClientAddress(String newAddress) {
    clientaddress = newAddress;
  }
  public void setClientPhone(String newPhone) {
    clientphone = newPhone;
  }
  public boolean equals(String id) {
    return this.clientid.equals(id);
  }
  public String toString() {
    String string = "Client name " + clientname + " address " + clientaddress + " id " + clientid + " phone " + clientphone;
    return string;
  }
}