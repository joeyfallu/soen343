package com.example.myapp;

import com.example.myapp.database.*;
import com.example.myapp.userCatalog.*;
import com.example.myapp.productCatalog.Tv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class DemoApplication {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        //Test code
        Store store = new Store();
        Tv t1 = new Tv(12,"wj123",40,99,"sony","40x40");
        ProductTDG test = new ProductTDG();
        int k =990;
        try{k=test.dbInsert(t1);}catch(Exception e){e.printStackTrace();}
        System.out.println(k+"is the ID");
        try{ System.out.println(test.dbGet(9));}catch(Exception e){e.printStackTrace();}
        //////////////////////////////
        User clod = new User(0,"clo","dia","123 fake street","5552225555","c@c.ca","kappapride",1);
        UserTDG test2 = new UserTDG();
        try{k=test2.dbInsert(clod);}catch(Exception e){e.printStackTrace();}
        try{ System.out.println(test2.dbGet(7));}catch(Exception e){e.printStackTrace();}
      return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}