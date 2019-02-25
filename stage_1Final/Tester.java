/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse;
import java.util.*;
import java.text.*;
import java.io.*;
/**
 *
 * @author 21cha
 */
public class Tester {
    public static void main(String[] s) {
     Client C1 = new Client("John","123 Computer Street","123-456-7890");
     Client C2 = new Client("Jane","456 Science Street","098-765-4321");

     
     System.out.println(C1.getClientName() + " should be John");
     System.out.println(C2.getClientName() + " should be Jane");
     
     System.out.println(C1.getClientAddress() + " should be 123 Computer Street");
     System.out.println(C2.getClientAddress() + " should be 456 Science Street");
     
     System.out.println(C1.getClientPhone() + " should be 123-456-7890");
     System.out.println(C2.getClientPhone() + " should be 098-765-4321");
     
  }
}
