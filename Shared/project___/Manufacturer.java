import java.util.*;
import java.lang.*;
import java.io.*;

public class Manufacturer implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String MANUFACTURER_STRING = "M";
	private String Name;
	private String ID;
	private String address;
	private String phone;
	private String Billing;
	private List<Product> Products = new LinkedList<>();

	public Manufacturer(String Name, String address, String phone, String billing)
	{
		this.Name    = Name;
		this.address = address;
		this.phone   = phone;
		this.Billing = billing;
		ID = MANUFACTURER_STRING + (ManufacturerIDServer.instance()).getID();
	}

	public boolean assignProduct(Product P)
	{
		if (Products.add(P))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean removeProduct(Product P)
	{
		if (Products.remove(P))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public String getName()
	{
		return Name;
	}

	public String getAddress()
	{
		return address;
	}

	public String getPhone()
	{
		return phone;
	}

	public String getBilling()
	{
		return Billing;
	}

	public String getID()
	{
		return ID;
	}

	/*   //Still working on this
	public Iterator<Product> getProducts()
	{
		return Products.iterator();
	}*/

	public String toString()
	{
		return "Manufacturer: " + getName() + ". ID: " + getID() + ". Phone: " + getPhone();
	}

}
