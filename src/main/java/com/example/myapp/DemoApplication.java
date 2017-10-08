package com.example.myapp;

import com.example.myapp.database.ProductTDG;
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
      return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}