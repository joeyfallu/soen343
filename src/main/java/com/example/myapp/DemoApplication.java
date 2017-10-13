package com.example.myapp;

import com.example.myapp.productCatalog.*;
import com.example.myapp.userCatalog.*;
import com.example.myapp.productCatalog.Desktop;
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

    @RequestMapping(value="/deleteItems")
    public String deleteItems(){
        return "admin/deleteItems";
    }
    
    @RequestMapping(value="/viewItems", method = RequestMethod.GET)
    public String viewItems(){
        return "admin/viewItems";
    }

    @RequestMapping(value="/addItems")
    public String addItems() { return "admin/addItems"; }


    @RequestMapping(value="/addUsers")
    public String addUsers() { return "addUsers"; }

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
    @RequestMapping(value = "/post/deleteItems", method = RequestMethod.POST)
    @ResponseBody
    String deleteItemsForm(@RequestBody String Json){

        ProductCatalog pC = new ProductCatalog();

        //Delete items with single digit IDs
        if(Json.length() == 16 ){
            String sid = Json.substring(13, 14);
            int id = Integer.parseInt(sid);

            try{
                pC.deleteProduct(id);
            }
            catch(Exception e){
                System.out.println("Item deletion failed: " + e.toString());
            }

            System.out.println("Item : " + id + " successfully deleted from database");

        }
        //Delete items with double digit IDs
        if(Json.length() == 17 ){
            String sid = Json.substring(13, 15);
            int id = Integer.parseInt(sid);

            try{
                pC.deleteProduct(id);
            }
            catch(Exception e){
                System.out.println("Item deletion failed: " + e.toString());
            }

            System.out.println("Item : " + id + " successfully deleted from database");
        }
        //Delete items with triple digits IDs
        if(Json.length() == 18 ){
            String sid = Json.substring(13, 16);
            int id = Integer.parseInt(sid);

            try{
                pC.deleteProduct(id);
            }
            catch(Exception e){
                System.out.println("Item deletion failed: " + e.toString());
            }

            System.out.println("Item : " + id + " successfully deleted from database");
        }
        //Delete items with quadruple digits IDs
        if(Json.length() == 19 ){
            String sid = Json.substring(13, 17);
            int id = Integer.parseInt(sid);

            try{
                pC.deleteProduct(id);
            }
            catch(Exception e){
                System.out.println("Item deletion failed: " + e.toString());
            }

            System.out.println("Item : " + id + " successfully deleted from database");
        }




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

    @RequestMapping(value = "/post/addUser", method = RequestMethod.POST)
    String addUser(@RequestBody String json){
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        store.addNewUser(user);
        java.lang.System.out.println("post request sent successfully");
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

   // The Two lines bellow were a test that succeeded in adding users to the database
        User user = new User(0,"hanna","georgi", "123 street", "1234567890", "hanna@hotmail", "12345", 1);
        store.addNewUser(user);
    }
}