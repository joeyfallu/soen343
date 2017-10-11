package com.example.myapp;

import com.example.myapp.database.*;
import com.example.myapp.userCatalog.*;
import com.example.myapp.productCatalog.Tv;
import com.example.myapp.productCatalog.Product;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

import java.util.Map;

@Controller
@SpringBootApplication
public class DemoApplication {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
//        //Test code
//        Store store = new Store();
//        Tv t1 = new Tv(12,"wj123",40,99,"sony","40x40");
//        ProductTdg test = new ProductTdg();
//        int k =990;
//        try{k=test.dbInsert(t1);}catch(Exception e){e.printStackTrace();}
//        System.out.println(k+"is the ID");
//        try{ System.out.println(test.dbGet(9));}catch(Exception e){e.printStackTrace();}
//        //////////////////////////////
//        User clod = new User(0,"clo","dia","123 fake street","5552225555","c@c.ca","kappapride",1);
//        UserTDG test2 = new UserTDG();
//        try{k=test2.dbInsert(clod);}catch(Exception e){e.printStackTrace();}
//        try{ System.out.println(test2.dbGet(7));}catch(Exception e){e.printStackTrace();}
      return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/testPage", method = RequestMethod.GET)
    public String test() {
        return "testPage";
    }

    @RequestMapping(value="/viewItems", method = RequestMethod.GET)
    public String viewItems(){
        return "viewItems";
    }

    @RequestMapping(value="/addItems")
    public String addItems() { return "addItems"; }

    @RequestMapping("/get/products")
    @ResponseBody
    String getProducts(){
        Store store = new Store();
        Gson gson = new Gson();
        Map<Integer, Product> items = store.viewProductCatalog();
        String json = gson.toJson(items);
        System.out.println(json);
        return json;
    }

    @RequestMapping(value = "/post/addTv", method = RequestMethod.POST)
    String addTv(@RequestBody String json){
        System.out.println(json);
        return "test";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}