package com.example.myapp;

import com.example.myapp.database.ProductMapper;
import com.example.myapp.database.UserMapper;
import com.example.myapp.productCatalog.*;
import com.example.myapp.transactions.Transaction;
import com.example.myapp.userCatalog.*;
import com.example.myapp.productCatalog.Desktop;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@SpringBootApplication
public class DemoApplication {

    static Store store;
    static int TempUserID=99099;

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
        store.initiateTransaction(TempUserID,Transaction.Type.modify);
        return "admin/modifyItems";
    }

    @RequestMapping(value="/deleteItems")
    public String deleteItems(){
        store.initiateTransaction(TempUserID,Transaction.Type.delete);
        return "admin/deleteItems";
    }

    @RequestMapping(value="/viewItems", method = RequestMethod.GET)
    public String viewItems(){
        return "admin/viewItems";
    }

    @RequestMapping(value="/addItems")
    public String addItems() {
        store.initiateTransaction(TempUserID,Transaction.Type.add);
        return "admin/addItems";
    }


    @RequestMapping(value="/addUsers")
    public String addUsers() {
        store.initiateTransaction(TempUserID,Transaction.Type.add);
        return "admin/addUsers";
    }

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

        String json = gson.toJson(store.getProductMapper().getProductCatalog().getProducts());

        return json;
    }

    @RequestMapping(value = "/getItem/{id}", method = RequestMethod.GET)
    @ResponseBody
    /*
    Returns item info as json
     */
    public String getProductById(
            @PathVariable("id") int id) {
        Gson gson = new Gson();
        Map<Integer, Product> items = store.getProductCatalog().getProducts();
        String productJson = gson.toJson(items.get(id));
        System.out.println(productJson);
        return productJson;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    /*
    Returns modify Page
     */
    public String modifyPage() {
        return "modify";
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
    String deleteItemsForm(@RequestBody String json){
        Gson gson = new Gson();
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        int id = jsonObject.get("deleteId").getAsInt();
        Map<Integer, Product> products = store.viewProductCatalog();
        if(!products.containsKey(id))
            System.out.println("Item does not exist");
        else
            store.deleteProduct(TempUserID,id);
        return json;
    }

    @RequestMapping(value = "/post/addUser", method = RequestMethod.POST)
    @ResponseBody
    String addUser(@RequestBody String json){
        System.out.println(json);
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        store.addNewUser(TempUserID,user);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/addMonitor", method = RequestMethod.POST)
    @ResponseBody
    String addMonitor(@RequestBody String json){
        Gson gson = new Gson();
        Product monitor = gson.fromJson(json, Monitor.class);
        store.addNewProduct(TempUserID,monitor);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/addTablet", method = RequestMethod.POST)
    @ResponseBody
    String addTablet(@RequestBody String json){
        Gson gson = new Gson();
        Product tablet = gson.fromJson(json, Tablet.class);
        store.addNewProduct(TempUserID,tablet);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/addDesktop", method = RequestMethod.POST)
    @ResponseBody
    String addDesktop(@RequestBody String json){
        Gson gson = new Gson();
        Product desktop = gson.fromJson(json, Desktop.class);
        store.addNewProduct(TempUserID,desktop);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/addLaptop", method = RequestMethod.POST)
    @ResponseBody
    String addLaptop(@RequestBody String json){
        Gson gson = new Gson();
        Product laptop = gson.fromJson(json, Laptop.class);
        store.addNewProduct(TempUserID,laptop);
        return gson.toJson(json);
    }

/*--stuff for modify--*/


    @RequestMapping(value = "/post/modifyMonitor", method = RequestMethod.POST)
    @ResponseBody
    String modifyMonitor(@RequestBody String json){
        Gson gson = new Gson();
        Product monitor = gson.fromJson(json, Monitor.class);
        store.modifyProduct(TempUserID,monitor.getId(), monitor);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/modifyTablet", method = RequestMethod.POST)
    @ResponseBody
    String modifyTablet(@RequestBody String json){
        Gson gson = new Gson();
        Product tablet = gson.fromJson(json, Tablet.class);
        store.modifyProduct(TempUserID,tablet.getId(), tablet);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/modifyDesktop", method = RequestMethod.POST)
    @ResponseBody
    String modifyDesktop(@RequestBody String json){
        Gson gson = new Gson();
        Product desktop = gson.fromJson(json, Desktop.class);
        store.modifyProduct(TempUserID,desktop.getId(), desktop);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/modifyLaptop", method = RequestMethod.POST)
    @ResponseBody
    String modifyLaptop(@RequestBody String json){
        Gson gson = new Gson();
        Product laptop = gson.fromJson(json, Laptop.class);
        store.modifyProduct(TempUserID,laptop.getId(), laptop);
        return gson.toJson(json);
    }

    @RequestMapping(value="/post/endTransaction", method = RequestMethod.POST)
    @ResponseBody
    String endTransaction(){

        store.endTransaction(TempUserID);
        System.out.println("HELLLO");
        return "hello";
    }

    public static void main(String[] args) {
        //start the server
        SpringApplication.run(DemoApplication.class, args);
        //initialize catalogs
        UserCatalog userInit = new UserCatalog();
        ProductCatalog productInit = new ProductCatalog();
        UserMapper userMap = new UserMapper(userInit);
        ProductMapper productMap = new ProductMapper(productInit);

        //initialize the store with the current catalog from the db
        store = new Store(userMap,productMap);
        store.getProductCatalog().setProducts(store.getProductMapper().getAll());

        /*//start of test
        Monitor mn1 = new Monitor(0,"toshiba", 47,99,"sony",24,2);
        store.initiateTransaction(5, Transaction.Type.add);
        store.addNewProduct(5,mn1);
        store.endProductTransaction(5);
        store.endTransaction(5);
        //start of modify test
        Tv tv1 = new Tv(0,"sony",99,35,"toshiba","34x32",1);
        store.initiateTransaction(6,Transaction.Type.add);
        store.modifyProduct(6,43,tv1);
        store.endProductTransaction(6);
        store.endTransaction(6);
        //start of delete test
        store.initiateTransaction(7,Transaction.Type.delete);
        store.deleteProduct(7,12);
        store.deleteProduct(7,14);
        store.endProductTransaction(7);
        store.endTransaction(7);*/
//
//        User test = new User(0,"jim","billy","1234 fake","2311233322","billy@jim.com","yolo",0);
//        store.initiateTransaction(TempUserID,Transaction.Type.add);
//        store.addNewUser(TempUserID,test);
//        store.endTransaction(TempUserID);

    }
}