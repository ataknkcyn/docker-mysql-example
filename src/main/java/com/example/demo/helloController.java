package com.example.demo;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class deneme {
    int id;
    int x;

    public deneme(int id, int x) {
        this.id = id;
        this.x = x;
    }

}

@RestController
public class helloController {

    public Connection DatabaseConnect() {
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://demo-mysql:3306/Deneme", "demo", "123");
            return con;
        } catch (Exception e) {
            System.out.println("ONCE BURAYA GIRDI");
            System.out.println(e.getMessage());
            return null;

        }
    }

    @RequestMapping("/hello")
    public String index() {
        System.out.println();
        try {
            Connection con = DatabaseConnect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from bomba");
            String x = "";
            while(rs.next()){
                x += rs.getInt(1) + " ";

            }
            System.out.println(x);
            con.close();
            return x;
        } catch (Exception e) {
            System.out.println("ONCE BURAYA GIRDI : " + 2);
            System.out.println(e.getMessage());
            return "BOS GELDI AAAA";
        }

    }

    public static void main(String[] args) {
        helloController x = new helloController();
        x.index();
    }
}
