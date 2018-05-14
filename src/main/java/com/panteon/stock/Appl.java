package com.panteon.stock;

import com.panteon.stock.tools.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;

public class Appl {
    private static Connection con = null;
    // private static String username = "ssu-oms";
    // private static String password = "ssu-oms";
    //private static String username = "db304";
    //private static String password = "db304";
    private static String username = "root";
    private static String password = "toor";
    // private static String username = "postgres";
    // private static String password = "postgres";
    //
    // Microsoft
    //private static String URL = "jdbc:sqlserver://CLASS02.training.local\\SQLEXPRESS";
    // private static String URL =
    //		 "jdbc:sqlserver://CLASS02.training.local\\SQLEXPRESS;databasename=lv304test;";
    //// private static String URL =
    // "jdbc:sqlserver://CLASS02.training.local\\SQLEXPRESS;";
    //// private static String URL =
    // "jdbc:sqlserver://CLASS02.training.local\\SQLEXPRESS;databasename=lv215Test;";
    // private static String URL =
    // "jdbc:sqlserver://CLASS02.training.local\\SQLEXPRESS;databasename=lv196first;";
    // private static String URL =
    // "jdbc:sqlserver://CLASS02.training.local\\SQLEXPRESS;databasename=Lv169OMS;";
    // private static String URL =
    // "jdbc:sqlserver://ssu-sql12\\tc;databasename=ssu-oms;";
    //
    // Sybase
    //// private static String URL = "jdbc:jtds:sqlserver://CLASS02/lv304test;instance=SQLEXPRESS;";
    // private static String URL =
    // "jdbc:jtds:sqlserver://CLASS02/lv257Test;instance=SQLEXPRESS;";
    // private static String URL =
    // "jdbc:jtds:sqlserver://CLASS02/lv235Test;instance=SQLEXPRESS;";
    // private static String URL =
    // "jdbc:jtds:sqlserver://CLASS02/lv215Test;instance=SQLEXPRESS;";
    // private static String URL =
    // "jdbc:jtds:sqlserver://CLASS02;instance=SQLEXPRESS;";
    //// private static String URL =
    // "jdbc:jtds:sqlserver://CLASS02/lv196first;instance=SQLEXPRESS;";
    // private static String URL =
    // "jdbc:jtds:sqlserver://ssu-sql12/ssu-oms;instance=tc;";
    // private static String URL =
    // "jdbc:jtds:sqlserver://CLASS02/Lv169OMS;instance=SQLEXPRESS;";
    //
    // MySQL
    private static String URL = PropertiesReader.getPropertyFromFile("db"+".connectionUrl");
    // private static String URL = "jdbc:mysql://localhost:3306/lv257Test";
    // private static String URL = "jdbc:mysql://localhost:3306/lv235Test";
    // private static String URL = "jdbc:mysql://localhost:3306/lv215Test";
    // private static String URL =
    // "jdbc:mysql://localhost:3306/measurement_devices";
    // private static String URL = "jdbc:mysql://localhost:3306/registrator_db";
    //// private static String URL = "jdbc:mysql://localhost:3306/lv196first";
    //// private static String URL = "jdbc:mysql://localhost:3306";
    //
    // PostgresSQL
    // private static String URL =
    // "jdbc:postgresql://192.168.195.250:5432/registrator_db";

    public static void main(String[] args) throws Exception {
        System.out.println("Start...");
        // Microsoft
        ////DriverManager.registerDriver(new
        ////		com.microsoft.sqlserver.jdbc.SQLServerDriver());
        //// DriverManager.registerDriver(new
        //// net.sourceforge.jtds.jdbc.Driver());
        //
        // DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
        // Class.forName("net.sourceforge.jtds.jdbc.Driver");
        //
        // DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        // Class.forName("com.mysql.jdbc.Driver");
        //
        // DriverManager.registerDriver(new org.postgresql.Driver());
        //
        // Load the driver
        con = DriverManager.getConnection(URL, username, password);
        if (con != null) {
            System.out.println("Connection Successful! \n");
        } else {
            System.out.println("Connection ERROR \n");
            System.exit(1);
        }
    }
}
