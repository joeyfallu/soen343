package com.example.myapp;

import com.example.myapp.productCatalog.*;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;


@Controller
@SpringBootApplication
public class DemoApplication {

    static Store store = new Store();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
      return "index";
    }

    /* ROUTING */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/registerAdmin", method = RequestMethod.GET)
    public String registerAdmin() {
        return "registerAdmin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin/admin";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "testPage";
    }

    @RequestMapping(value="/modifyItems", method = RequestMethod.GET)
    public String modifyItems(){
        return "admin/modifyItems";
    }

    @RequestMapping(value="/deleteItems", method = RequestMethod.GET)
    public String deleteItems(){
        return "admin/deleteItems";
    }

    @RequestMapping(value="/viewItems", method = RequestMethod.GET)
    public String viewItems(){
        return "admin/viewItems";
    }

    @RequestMapping(value="/addItems")
    public String addItems() { return "admin/addItems"; }

    /* LOGIN */
    @RequestMapping(value = "/post/login", method = RequestMethod.POST)
    @ResponseBody
    public String loginSubmit(@RequestBody String body) {
        System.out.println(body);
        return "{data: 'Successful login'}";
    }

    /* VIEW ITEMS */
    @RequestMapping("/get/products")
    @ResponseBody
    String getProducts(){
        Gson gson = new Gson();
        System.out.println(store.getProductCatalog().getProducts());
        String json = gson.toJson(store.getProductCatalog().getProducts());
        System.out.println(json);
        return json;
    }

    /* MODIFY ITEMS */
    @RequestMapping("/post/modifyItems")
    @ResponseBody
    String modifyItemsForm(){
        System.out.println("Backend modify items");
        return "{}";
    }

    /* DELETE ITEMS */
    @RequestMapping("/post/deleteItems")
    @ResponseBody
    String deleteItemsForm(){
        System.out.println("Backend delete items");
        return "{}";
    }

    @RequestMapping(value = "/post/addTv", method = RequestMethod.POST)
    @ResponseBody
    String addTv(@RequestBody String json){
        Gson gson = new Gson();
        Product tv = gson.fromJson(json, Tv.class);
        store.addNewProduct(tv);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/addMonitor", method = RequestMethod.POST)
    @ResponseBody
    String addMonitor(@RequestBody String json){
        Gson gson = new Gson();
        Product monitor = gson.fromJson(json, Monitor.class);
        store.addNewProduct(monitor);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/addTablet", method = RequestMethod.POST)
    @ResponseBody
    String addTablet(@RequestBody String json){
        Gson gson = new Gson();
        Product tablet = gson.fromJson(json, Tablet.class);
        store.addNewProduct(tablet);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/addDesktop", method = RequestMethod.POST)
    @ResponseBody
    String addDesktop(@RequestBody String json){
        Gson gson = new Gson();
        Product desktop = gson.fromJson(json, Desktop.class);
        store.addNewProduct(desktop);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/addLaptop", method = RequestMethod.POST)
    @ResponseBody
    String addLaptop(@RequestBody String json){
        Gson gson = new Gson();
        Product laptop = gson.fromJson(json, Laptop.class);
        store.addNewProduct(laptop);
        return gson.toJson(json);
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        store.getProductCatalog().setProducts(store.getProductCatalog().getProductMapper().getAll());
    }
}