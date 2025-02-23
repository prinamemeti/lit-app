package org.example.letersi;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends Application {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}