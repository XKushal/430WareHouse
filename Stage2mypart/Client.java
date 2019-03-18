
import java.util.*;
import java.lang.*;
import java.io.*;

public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String CLIENT_STRING = "C";
	private String clientname;
	private String clientid;
	private String clientphone;
	private String clientaddress;
	public Client(String Name, String phone,  String address)
	{
		this.clientname    = Name;
		this.clientphone   = phone;
		this.clientaddress = address;

		clientid = CLIENT_STRING + (ClientIDServer.instance()).getID();//generates id through the client id server
	}

	public String getName()
	{
		return clientname;
	}

	public String getAddress()
	{
		return clientaddress;
	}

	public String getPhone()
	{
		return clientphone;
	}

	

	public String getID()
	{
		return clientid;
	}

	public String toString()
	{
		return "Client: " + getName() + ".  Address: " + getPhone()+ ". ID: "+ getID()+". Phone: "+getAddress() ;
	}

}
