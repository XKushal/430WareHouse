import java.util.*;
import java.lang.*;
import java.io.*;
public class Product{
	private String Name;
	private Manufacturer SUPPLIER;
	private String ID;
	private double UnitPrice;
	
	
	public Product(String Name, String ID)
	{
		this.Name = Name;
		this.ID   = ID;
		this.SUPPLIER = null;
	}
	
	public Manufacturer AssignSupplier(Manufacturer manu, double Price)
	{
		SUPPLIER  = manu;
		UnitPrice = Price;
		return SUPPLIER;
	}
	
	public Manufacturer unnassignSupplier()
	{
		if (SUPPLIER == null)
		{
			return null;
		}
		else
		{
			Manufacturer supplier = SUPPLIER;
			SUPPLIER = null;
			return supplier;
		}
	}
	
	//GET functions
	public String getName()
	{
		return Name;
	}
	
	public String getID()
	{
		return ID;
	}
	
	public double getUnitPrice()
	{
		return UnitPrice;
	}
	
	public Manufacturer getManu()
	{
		return SUPPLIER;
	}
	
	public String toString()
	{
		return "Name: " + Name + ". ID: " + ID + ". Unit Price: " + UnitPrice + ". Supplied By: " + SUPPLIER.getName();
	}
}
		