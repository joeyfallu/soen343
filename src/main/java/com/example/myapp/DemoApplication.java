package com.example.myapp;

import com.example.myapp.productCatalog.*;
import com.example.myapp.userCatalog.*;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

import java.util.Map;

@Controller
@SpringBootApplication
public class DemoApplication {

    static Store store = new Store();


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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "testPage";
    }

    @RequestMapping(value="/deleteItems", method = RequestMethod.GET)
    public String deleteItems(){
        return "deleteItems";
    }

    @RequestMapping(value="/viewItems", method = RequestMethod.GET)
    public String viewItems(){
        return "viewItems";
    }

    @RequestMapping(value="/addItems")
    public String addItems() { return "addItems"; }

    @RequestMapping(value="/addUsers")
    public String addUsers() { return "addUsers"; }

    @RequestMapping("/get/products")
    @ResponseBody
    String getProducts(){
        Gson gson = new Gson();
        System.out.println(store.getProductCatalog().getProducts());
        String json = gson.toJson(store.getProductCatalog().getProducts());
        System.out.println(json);
        return json;
    }

    //TODO: Fix adds
    @RequestMapping(value = "/post/addTv", method = RequestMethod.POST)
    String addTv(@RequestBody String json){
        ProductCatalog productCatalog = new ProductCatalog();
        Gson gson = new Gson();
        Product tv = gson.fromJson(json, Tv.class);
        productCatalog.addProduct(tv);
        //AddItemsGoes here
        return json;
    }

    @RequestMapping(value = "/post/addUser", method = RequestMethod.POST)
    String addUser(@RequestBody String json){
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        store.addNewUser(user);
        java.lang.System.out.println("post request sent successfully");
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/addMonitor", method = RequestMethod.POST)
    String addMonitor(@RequestBody String json){
        ProductCatalog productCatalog = new ProductCatalog();
        Gson gson = new Gson();
        Product monitor = gson.fromJson(json, Monitor.class);
        productCatalog.addProduct(monitor);
        //AddItemsGoes here
        return json;
    }

    @RequestMapping(value = "/post/addTablet", method = RequestMethod.POST)
    String addTablet(@RequestBody String json){
        ProductCatalog productCatalog = new ProductCatalog();
        Gson gson = new Gson();
        Product tablet = gson.fromJson(json, Tablet.class);
        productCatalog.addProduct(tablet);
        //AddItemsGoes here
        return json;
    }

    @RequestMapping(value = "/post/addDesktop", method = RequestMethod.POST)
    String addDesktop(@RequestBody String json){
        ProductCatalog productCatalog = new ProductCatalog();
        Gson gson = new Gson();
        Product desktop = gson.fromJson(json, Desktop.class);
        productCatalog.addProduct(desktop);
        //AddItemsGoes here
        return json;
    }

    @RequestMapping(value = "/post/addLaptop", method = RequestMethod.POST)
    String addLaptop(@RequestBody String json){
        ProductCatalog productCatalog = new ProductCatalog();
        Gson gson = new Gson();
        Product desktop = gson.fromJson(json, Laptop.class);
        productCatalog.addProduct(desktop);
        //AddItemsGoes here
        return json;
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        store.getProductCatalog().setProducts(store.getProductCatalog().getProductMapper().getAll());

   // The Two lines bellow were a test that succeeded in adding users to the database
       // User user = new User(0,"hanna","georgi", "123 street", "1234567890", "hanna@hotmail", "12345", 1);
        // store.addNewUser(user);
    }
}