package JDBC.Project;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class FuelStation {
    private static final String url = "jdbc:mysql://localhost:3306/db3";
    private static final String user = "root";
    private static final String password = "Password007";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        double petrolprice = 95.0;
        double dieselprice = 88.0;
        double cngprice = 77.0;

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url,user,password);
            while(true) {
                Scanner sc = new Scanner(System.in);
                System.out.println();
                System.out.println("WELCOME TO FUEL STATION ");
                System.out.println("Enter option 1 for petrol");
                System.out.println("Enter option 2 for diesel");
                System.out.println("Enter option 3 for CNG");
                System.out.println("Enter option 0 for exit");
                int option = sc.nextInt();
                switch (option){
                    case 1:
                        petrol(con,sc,petrolprice);
                        break;
                    case 2:
                        diesel(con,sc,dieselprice);
                        break;
                    case 3:
                        CNG(con,sc,cngprice);
                        break;
                    case 0:
                        exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("please enter a valid option ");
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private static void petrol(Connection con, Scanner sc,double price) {
        try {
            double mileage = 40.0;
            price = 95.0;
            String query = ("INSERT INTO FUEL(Fuel,price,AMOUNT_PAYED) VALUES(?,?,?)");
            System.out.println("How much do you need to fill petrol ");
            int withdraw = 0;
            int amount = 0;
            int exchange = 0;
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(query)) {

                int payment = sc.nextInt();
                int rupees = payment ;
                System.out.println("please pay the amount "+ rupees);
                exchange = sc.nextInt();
                if(exchange>rupees){
                    withdraw = exchange - rupees;
                    amount = rupees;
                    System.out.println("keep the change "+ withdraw);
                    System.out.println(amount + " deposited");
                } else if (exchange<rupees) {
                    amount = exchange;
                    withdraw = rupees- exchange;
                    System.out.println("please pay rest amount "+withdraw);
                    exchange = sc.nextInt();
                    amount = amount +exchange;
                    if(amount ==rupees){
                        System.out.println(amount +" deposited");

                    } else if (amount <rupees) {
                        System.out.println("Please try again");
                        con.rollback();
                        return;
                    }else{
                        withdraw = amount - rupees;
                        amount = rupees;
                        System.out.println("keep the change" + withdraw);
                        System.out.println(amount + " deposited");

                    }
                }else{
                    amount = rupees;
                    System.out.println(amount + " deposited");
                }
                double litres = amount/price;
                double estimatedRange = litres*mileage;
                ps.setString(1,"petrol");
                ps.setInt(2,95);
                ps.setInt(3, amount);
                System.out.println("Tank is full");
                System.out.println("you have filled "+litres+" of petrol");
                System.out.println("estimated travel distance : "+estimatedRange+" km");

                int rowsaffect = ps.executeUpdate();
                if(rowsaffect>0) {
                    con.commit();
                    System.out.println("Payment successfull");
                }else{
                    con.rollback();
                    System.out.println("Payment failed");
                }
                System.out.println("Thanks for visit");



            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void diesel(Connection con, Scanner sc, double price) {
        try {
            double mileage = 45.0;
            price = 88.0;
            String query = ("INSERT INTO FUEL(Fuel,price,AMOUNT_PAYED) VALUES(?,?,?)");
            System.out.println("How much do you need to fill diesel");
            int withdraw = 0;
            int amount = 0;
            int exchange = 0;
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(query)) {

                int payment = sc.nextInt();
                int rupees = payment ;
                System.out.println("please pay the amount "+ rupees);
                exchange = sc.nextInt();
                if(exchange>rupees){
                    withdraw = exchange - rupees;
                    amount = rupees;
                    System.out.println("keep the change "+ withdraw);
                    System.out.println(amount + " deposited");
                } else if (exchange<rupees) {
                    amount = exchange;
                    withdraw = rupees- exchange;
                    System.out.println("please pay rest amount "+withdraw);
                    exchange = sc.nextInt();
                    amount = amount +exchange;
                    if(amount ==rupees){
                        System.out.println(amount +" deposited");

                    } else if (amount <rupees) {
                        System.out.println("Please try again");
                        con.rollback();
                        return;
                    }else{
                        withdraw = amount - rupees;
                        amount = rupees;
                        System.out.println("keep the change " + withdraw);
                        System.out.println(amount + " deposited");

                    }
                }else{
                    amount = rupees;
                    System.out.println(amount + " deposited");
                }
                double litres = amount/price;
                double estimatedRange = litres * mileage;
                ps.setString(1,"diesel");
                ps.setInt(2,88);
                ps.setInt(3, amount);
                System.out.println("Tank is full");
                System.out.println("you have filled "+litres+" of diesel");
                System.out.println("estimated travel distance : "+estimatedRange+" km");
                int rowsaffect = ps.executeUpdate();
                if(rowsaffect>0) {
                    con.commit();
                    System.out.println("Payment successfull");
                }else{
                    con.rollback();
                    System.out.println("Payment failed");
                }
                System.out.println("Thanks for visit");



            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void CNG(Connection con, Scanner sc, double price) {
        try {
            price = 95.0;
            double mileage =60.0;
            String query = ("INSERT INTO FUEL(Fuel,price,AMOUNT_PAYED) VALUES(?,?,?)");
            System.out.println("How much do you need to fill petrol ");
            int withdraw = 0;
            int amount = 0;
            int exchange = 0;
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(query)) {

                int payment = sc.nextInt();
                int rupees = payment ;
                System.out.println("please pay the amount "+ rupees);
                exchange = sc.nextInt();
                if(exchange>rupees){
                    withdraw = exchange - rupees;
                    amount = rupees;
                    System.out.println("keep the change "+ withdraw);
                    System.out.println(amount + " deposited");
                } else if (exchange<rupees) {
                    amount = exchange;
                    withdraw = rupees- exchange;
                    System.out.println("please pay rest amount "+withdraw);
                    exchange = sc.nextInt();
                    amount = amount +exchange;
                    if(amount ==rupees){
                        System.out.println(amount +" deposited");

                    } else if (amount <rupees) {
                        System.out.println("Please try again");
                        con.rollback();
                        return;
                    }else{
                        withdraw = amount - rupees;
                        amount = rupees;
                        System.out.println("keep the change " + withdraw);
                        System.out.println(amount + " deposited");

                    }
                }else{
                    amount = rupees;
                    System.out.println(amount + " deposited");
                }

                ps.setString(1,"CNG");
                ps.setInt(2,77);
                ps.setInt(3, amount);
                System.out.println("Tank is full");
                double kg = amount/price;
                double estimatedRange = kg * mileage;
                System.out.println("you have filled "+kg+" kg of CNG");
                System.out.println("estimated travel distance : "+estimatedRange+" km");
                int rowsaffect = ps.executeUpdate();
                if(rowsaffect>0) {
                    con.commit();
                    System.out.println("Payment successfull");
                }else{
                    con.rollback();
                    System.out.println("Payment failed");
                }
                System.out.println("Thanks for visit");



            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void exit() throws InterruptedException {
        System.out.print("Exiting fuel station");
        int i = 5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(1000);
            i--;
        }
        System.out.println();
        System.out.println("Thanks for visit ");
    }
}
