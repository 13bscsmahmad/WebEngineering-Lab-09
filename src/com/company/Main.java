package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	// write your code here

        Scanner scanner = new Scanner(System.in);
        int option;

        do {

            System.out.println("1) Display All\n2) Delete record\n3) Search record\n0) Exit\n\nOption: ");
            option = scanner.nextInt();

            switch (option){

                case 1:
                    displayAll();
                    break;

                case 2:
                    delete();
                    break;

                case 3:
                    search();
                    break;

                default:
                    if (option != 0){
                        System.out.println("Invalid option. Try again.");
                    } else {

                        break;

                    }

            }

        } while (option != 0);

        return;

    }

    public static void displayAll(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/University?" + "user=root&password=moaaz@dell");
            Statement st = conn.createStatement();
            ResultSet mar = st.executeQuery("SELECT * FROM student");

            System.out.println("ID - Registration_Number - Name - Class - Section - Contact - Address");
            System.out.println("");


            while(mar.next())
            {

                System.out.println(mar.getInt(1) + " -  " + mar.getString(2) + " - " + mar.getString(3) + " - " + mar.getString(4) + " - " + mar.getString(5) + " - " + mar.getString(6) + " - " + mar.getString(7));

            }



        }catch (Exception ex){
            System.out.println("Exception..."+ex.getMessage());

        }


    }

    public static void delete(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name to be deleted: ");
        String delete_name = scanner.nextLine();
        //System.out.println(delete_name);

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/University?" + "user=root&password=moaaz@dell");
            Statement st = conn.createStatement();

            String sql = "DELETE FROM student WHERE Name = '" + delete_name + "';";
            st.executeUpdate(sql);
            System.out.println(delete_name + " record deleted!");



        }catch (Exception ex){
            System.out.println("Exception..."+ex.getMessage());

        }

    }

    public static void search(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name to search: ");
        String search_name = scanner.nextLine();
        //System.out.println(delete_name);

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/University?" + "user=root&password=moaaz@dell");
            Statement st = conn.createStatement();

            String sql = "Select * FROM student WHERE Name = '" + search_name + "';";
            ResultSet mar = st.executeQuery(sql);
            //System.out.println(delete_name + " record deleted!");

            while (mar.next()){

                if (mar.getString(3).equals(search_name)){
                    System.out.println(mar.getInt(1) + " -  " + mar.getString(2) + " - " + mar.getString(3) + " - " + mar.getString(4) + " - " + mar.getString(5) + " - " + mar.getString(6) + " - " + mar.getString(7));
                }

            }



        }catch (Exception ex){
            System.out.println("Exception..."+ex.getMessage());

        }


    }
}
