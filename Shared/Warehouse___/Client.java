
import java.util.*;
import java.lang.*;
import java.io.*;

public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String CLIENT_STRING = "C";
	private String Client_name;
	private String Client_id;
	private String Client_phone;
	private String Client_address;
	private String Client_billing;
	public Client(String Name, String phone,  String address, String billing)
	{
		this.Client_name    = Name;
		this.Client_phone   = phone;
		this.Client_address = address;
		this.Client_billing = billing;

		Client_id = CLIENT_STRING + (ClientIDServer.instance()).getID();//generates id through the client id server
	}

	public String getName()
	{
		return Client_name;
	}

	public String getAddress()
	{
		return Client_address;
	}

	public String getPhone()
	{
		return Client_phone;
	}

	public String getBilling()
        {
		return Client_billing;
	}

	public String getID()
	{
		return Client_id;
	}


	public String toString()
	{
		return "Client: " + getName() + ".  Phone: " + getPhone()+ ".ID: "+ getID()+".Address: "+getAddress();
	}

}
