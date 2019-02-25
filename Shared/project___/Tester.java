import java.util.*;
import java.text.*;
import java.io.*;
import java.util.Iterator;

public class Tester{

	public static void main(String[] s){

		Product P1 = new Product("iPhone", "P1"); //create dummy Products
		Product P2 = new Product("Galaxy s6", "P2");
		ManufacturerList MANUS = ManufacturerList.instance(); //instantiate a ManufacturerList

		boolean success = false;
		String LISTMANUS;

		//create some manufacturers
		Manufacturer M_temp;
		Manufacturer m1 = new Manufacturer("Apple", "1600 Pennsylvania Ave", "320-555-4141", "billingInfo");
		Manufacturer m2 = new Manufacturer("Android", "1900 Android Ave", "320-777-1818", "billingINFO");

		//insert Manufacturers into ManufacturerList MANUS
		success = MANUS.insertManufacturer(m1);
		success = MANUS.insertManufacturer(m2);

		//manufacturers added successfully
		if (success == true)
		{
			System.out.println("Success!\n");

			//print list using toString
			LISTMANUS = MANUS.toString();
			System.out.println("Manufacturers are: " + LISTMANUS + "\n");

			//print IDs to test ID server
			System.out.println("Manu 1 ID: " + m1.getID());
			System.out.println("Manu 2 ID: " + m2.getID());
			System.out.println("\n");

			//print manufacturers to test their toString method
			System.out.println(m1.toString());
			System.out.println(m2.toString());
			System.out.println("\n");

			//print Manufacturers using an iterator
			System.out.println("Now with iterators: ");
			Iterator<Manufacturer>  Manu_Traversal = MANUS.getManufacturers(); //MUST declare this after all manufacturers have been added
			M_temp = Manu_Traversal.next();
			System.out.println("Man1: " + M_temp);
			M_temp = Manu_Traversal.next();
			System.out.println("Man2: " + M_temp);
			System.out.println("\n");

			//attempt to assign a product to a manufacturer
			System.out.println("Attempting to assign product to M1..");
			success = m1.assignProduct(P1);
			if (success == true)
			{
				System.out.println("Product added successfully! \n");

				/* //Still working on this
				System.out.println("Now attempting to print products with iterator.");
				Product P_temp;
				Iterator<Product>  Prod_Traversal = m1.getProducts();
				P_temp = Prod_Traversal.next();
				System.out.println("Prod1: " + P_temp);
				*/

			}
			else
			{
				System.out.println("Product not added.");
			}
		}
		else //manufacturers not added successfully
		{
			System.out.println("You fail!\n");
		}



	}
}
