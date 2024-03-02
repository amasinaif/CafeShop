package cafeshop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer {

    private int ID;
    private String password;
    private String fName;
    private String lName;
    private String phone;

    public Customer(int ID, String password, String fName, String lName, String phone) {
        this.setPassword(password);
        this.setfName(fName);
        this.setlName(lName);
        this.setPhone(phone);
        this.setID(ID);
    }

    public Customer(String password, String fName, String lName, String phone) {
        this.setPassword(password);
        this.setfName(fName);
        this.setlName(lName);
        this.setPhone(phone);
        this.setID(0);

    }

    public Customer() {
        this.setPassword("");
        this.setfName("");
        this.setlName("");
        this.setPhone("");
        this.setID(0);
    }

    public String getfName() {
        return this.fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return this.lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // get data object as string
    @Override
    public String toString() {
        return "id= " + ID + "\npassword= " + password + "\nfName= " + fName + "\nlName= " + lName + "\nphone= " + phone;
    }

    // append the object to file
    // if save successfully retuen true, otherwise return false
    public boolean PrintToFile() {
        try (FileWriter f = new FileWriter("Customers.txt", true);
                BufferedWriter b = new BufferedWriter(f);
                PrintWriter p = new PrintWriter(b);) {

            p.println(toString());
            p.println("-----------------------");
            p.close();
            f.close();
            b.close();
            return true;
        } catch (IOException i) {
            return false;
        }

    }

    // get all saved file objects as array list
    public static ArrayList<Customer> GetAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            File cFile = new File("Customers.txt");
            if (!cFile.exists()) {
                return customers;
            }
            Scanner scanner = new Scanner(cFile);
            int i = 0;
            while (scanner.hasNext()) {

                int id = Integer.valueOf(scanner.nextLine().split("= ")[1]);
                String password = scanner.nextLine().split("= ")[1];
                String fName = scanner.nextLine().split("= ")[1];
                String lName = scanner.nextLine().split("= ")[1];
                String phone = scanner.nextLine().split("= ")[1];

                customers.add(new Customer(Integer.valueOf(id), password, fName, lName, phone));

                scanner.nextLine();

            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }

    // get all saved objects in file, 
    // then compare their id with id pararmeter
    public static Customer SearchCustomerByID(int id) {
        ArrayList<Customer> customers = GetAllCustomers();
        for (Customer customer : customers) {
         if (customer.getID() == id) {
                return customer;
            }}
        return null;
    }

    //get id of last object saved in file;
    // if file is empty then id = 0
    public static int GetLastID() {
        ArrayList<Customer> customers = GetAllCustomers();
        if (!customers.isEmpty()) {
            return customers.get(customers.size() - 1).getID();
        } else {
            return 0;
        }
    }
}
