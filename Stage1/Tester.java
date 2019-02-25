

import java.util.*;
import java.text.*;
import java.io.*;
/**
 *
 * @author kushal singh
 */
public class Tester {
    public static void main(String[] s) {
     Manufacturer M1 = new Manufacturer("kushal","310 8th Ave S","123-456-7890");
     Manufacturer M2 = new Manufacturer("charlie","311 8th Ave S","098-765-4321");
     Manufacturer M3 = new Manufacturer("Ashish","312 8th Ave S","135-793-5791");
     Manufacturer M4 = new Manufacturer("Linh","313 8th Ave S","024-680-2468");

     System.out.println("------------------------------------");
     System.out.println(M1.getName() + " should be kushal");
     System.out.println(M2.getName() + " should be charlie");
     System.out.println(M3.getName() + " should be Ashish");
     System.out.println(M4.getName() + " should be Linh");
     System.out.println("------------------------------------");
     
     System.out.println(M1.getAddress() + " should be 310 8th Ave S");
     System.out.println(M2.getAddress() + " should be 311 8th Ave S");
      System.out.println(M3.getAddress() + " should be 312 8th Ave S");
     System.out.println(M4.getAddress() + " should be 313 8th Ave S");
     System.out.println("------------------------------------");

     System.out.println(M1.getPhone() + " should be 123-456-7890");
     System.out.println(M2.getPhone() + " should be 098-765-4321");
     System.out.println(M3.getPhone() + " should be 135-793-5791");
     System.out.println(M4.getPhone() + " should be 024-680-2468");
     System.out.println("------------------------------------");
     
  }
}