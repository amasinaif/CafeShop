package cafeshop;

import java.util.ArrayList;
import java.util.Scanner;

public class Cafeshop {
    
    private static Table AddANewTable(Scanner scanner) {
        Table table = new Table();
        System.out.print("Number of seats: ");
        int nos = scanner.nextInt();
        
        table.setNumberOfSeats(nos);
        
        return table;
        
    }
    
    private static Table SearchTableByID(Scanner scanner, ArrayList<Table> tables) {
        System.out.println("ID of table: ");
        int id = scanner.nextInt();
        for (Table table : tables) {
            if (table.getID() == id) {
                return table;
            }
        }
        return null;
    }
    
    private static ArrayList<Table> SearchTablesByNumberOfSeats(Scanner scanner, ArrayList<Table> tables) {
        ArrayList<Table> copyTables = new ArrayList<>();
        System.out.println("Number of seats: ");
        int nos = scanner.nextInt();
        for (Table table : tables) {
            if (table.getNumberOfSeats() == nos) {
                copyTables.add(table);
            }
        }
        return copyTables;
    }
    
    private static ArrayList<Reservation> SearchReservationsOfTable(Scanner scanner, ArrayList<Reservation> reservations) {
        System.out.println("ID of Table: ");
        int id = scanner.nextInt();
        
        ArrayList<Reservation> reservationsTable = new ArrayList<>();
        
        boolean checkingExicted = true;
        for (Reservation reservation : reservations) {
            if (reservation.getTable().getID() == id) {
                reservationsTable.add(reservation);
            }
        }
        return reservationsTable;
    }
    
    private static void PrintAllReservation(ArrayList<Reservation> reservations) {
        if (!reservations.isEmpty()) {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
                System.out.println("*-*-*-*-*-*-*-*-*");
            }
        } else {
            System.out.println("There is not any reservation!");
        }
    }
    
    private static void PrintAllTables(ArrayList<Table> tables) {
        if (!tables.isEmpty()) {
            for (Table table : tables) {
                System.out.println(table);
                System.out.println("*-*-*-*-*-*-*-*-*");
            }
        } else {
            System.out.println("There is not any table!");
        }
    }
    
    public static void main(String[] args) {
        
        ArrayList<Reservation> reservations = new ArrayList<>();
        ArrayList<Table> tables = new ArrayList<>();
        while (true) {
            System.out.println("Act as (a)dmin or (u)ser? or (e)xit");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.next().charAt(0);
            if (choice == 'a') {
                System.out.println("1- add a new table");
                System.out.println("2- search tables");
                System.out.println("3- print all tables");
                System.out.println("4- search for reservations of a table");
                System.out.println("5- print all reservations");
                
                choice = scanner.nextInt();
                if (choice == 1) {
                    Table newTable = AddANewTable(scanner);
                    tables.add(newTable);
                } else if (choice == 2) {
                    System.out.println("a- search table by id of table");
                    System.out.println("b- serach tables by number of seats");
                    choice = scanner.next().charAt(0);
                    switch (choice) {
                        case 'a': {
                            Table table = SearchTableByID(scanner, tables);
                            if (table == null) {
                                System.out.println("There is not any table with this id!");
                            } else {
                                System.out.println(table);
                            }
                            break;
                        }
                        case 'b': {
                            ArrayList<Table> tables1 = SearchTablesByNumberOfSeats(scanner, tables);
                            if (tables1.isEmpty()) {
                                System.out.println("there is not any table has this number of seats");
                            } else {
                                for (Table table : tables1) {
                                    System.out.println(table);
                                }
                            }
                            break;
                        }
                        
                    }
                } else if (choice == 3) {
                    PrintAllTables(tables);
                } else if (choice == 4) {
                    ArrayList<Reservation> reservations1 = SearchReservationsOfTable(scanner, reservations);
                    
                    if (reservations1.isEmpty()) {
                        System.out.println("there is not any reservations for a table with this id!!");
                    } else {
                        for (Reservation reservation : reservations1) {
                            System.out.println(reservation);
                            System.out.println("*-*-*-*-*-*-*-*-*");
                        }
                    }
                } else if (choice == 5) {
                    PrintAllReservation(reservations);
                } else {
                    System.out.println("error!");
                }
                
            } else if (choice == 'u') {
                System.out.println("1- login");
                System.out.println("2- signup");
                
                choice = scanner.nextInt();
                
                if (choice == 1) {
                    Customer customer = Login(scanner);
                    if (customer == null) {
                        System.out.println("customer id or password incorrect!");
                    } else {
                        System.out.println("1- reserve a table");
                        System.out.println("2- print all my previous reservations");
                        choice = scanner.nextInt();
                        if (choice == 1) {
                            System.out.println("enter number of seats that you want");
                            int nos = scanner.nextInt();
                            Table table = SearchUnbookedTable(tables, nos);
                            if (table == null) {
                                System.out.println("there is not any available table has this number of seats");
                            } else {
                                Reservation reservation = BookingATable(scanner, table, customer);
                                if (CheckingReservation(reservation, reservations)) {
                                    System.out.println("sorry, you can not reserve in this time");
                                } else {
                                    reservations.add(reservation);
                                    System.out.println("your reservation has completed successfully!");
                                }
                            }
                        } else if (choice == 2) {
                            PrintAllReservation(SearchReservationsOfCustomer(reservations, customer.getID()));
                            
                        } else {
                            System.out.println("error!");
                        }
                        
                    }
                } else if (choice == 2) {
                    Customer customer = Signup(scanner);
                    customer.setID(Customer.GetLastID() + 1);
                    System.out.println("your id is: " + customer.getID() + ". remember it!");
                    customer.PrintToFile();
                } else {
                    System.out.println("error!");
                }
            } else if (choice == 'e') {
                System.out.println("Thank you for using our system");
                System.exit(0);
            } else {
                System.out.println("error.. try again");
            }
            
        }
        
    }
    
    private static Customer Login(Scanner scanner) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        System.out.print("Password: ");
        String pw = scanner.next();
        
        Customer customer = Customer.SearchCustomerByID(id);
        if (customer == null) {
            return null;
        } else {
            if (!customer.getPassword().equals(pw)) {
                return null;
            } else {
                return customer;
            }
        }
    }
    
    private static Customer Signup(Scanner scanner) {
        
        Customer customer = new Customer();
        
        System.out.print("First name: ");
        String fName = scanner.next();
        System.out.print("Last name: ");
        String lName = scanner.next();
        System.out.print("Password: ");
        String pw = scanner.next();
        System.out.print("Phone: ");
        String phone = scanner.next();
        customer.setfName(fName);
        customer.setlName(lName);
        customer.setPassword(pw);
        customer.setPhone(phone);
        return customer;
    }
    
    private static Reservation BookingATable(Scanner scanner, Table table, Customer customer) {
        
        String date = "";
        System.out.println("enter date of booking:");
        System.out.print("day: ");
        date += scanner.next() + "-";
        System.out.print("month: ");
        date += scanner.next() + "-";
        System.out.print("year: ");
        date += scanner.next();
        
        String fromTime = "";
        System.out.println("enter from time (24):");
        System.out.print("hour: ");
        fromTime += scanner.next() + ":";
        System.out.print("minutes: ");
        fromTime += scanner.next();
        
        String endTime = "";
        do {
            System.out.println("enter end time (24):");
            System.out.print("hour: ");
            endTime += scanner.next() + ":";
            System.out.print("minutes: ");
            endTime += scanner.next();
        } while (endTime.compareTo(fromTime) <= 0);
        return new Reservation(date, fromTime, endTime, customer, table);
        
    }
    
    private static boolean CheckingReservation(Reservation reservation, ArrayList<Reservation> reservations) {
        for (Reservation r : reservations) {
            if (r.getTable().equals(reservation) && r.getDate().equals(reservation.getDate())) {
                if (reservation.getStartTime().compareTo(r.getStartTime()) >= 0
                        && reservation.getEndTime().compareTo(r.getEndTime()) <= 0) {
                    return true;
                } else if (reservation.getStartTime().compareTo(r.getStartTime()) < 0
                        && reservation.getEndTime().compareTo(r.getStartTime()) > 0) {
                    return true;
                } else if (reservation.getStartTime().compareTo(r.getStartTime()) > 0
                        && reservation.getStartTime().compareTo(r.getEndTime()) < 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static Table SearchUnbookedTable(
            ArrayList<Table> tables, int numberOfSeats) {
        
        for (Table table : tables) {
            if (table.getNumberOfSeats() == numberOfSeats) {
                return table;
            }
        }
        return null;
    }
    
    private static ArrayList<Reservation> SearchReservationsOfCustomer(
            ArrayList<Reservation> reservations, int customerID) {
        ArrayList<Reservation> userReservations = new ArrayList<>();
        
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().getID() == customerID) {
                userReservations.add(reservation);
            }
        }
        return userReservations;
    }
}
