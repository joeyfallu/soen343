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
import java.util.Map;

@Controller
@SpringBootApplication
public class DemoApplication {

    private static Store store;

    // TODO replace with current user ID
    private static int TempUserID = 99099;

    /* Single page application routing */
    // https://stackoverflow.com/questions/24837715/spring-boot-with-angularjs-html5mode/44850886#44850886
    @RequestMapping({
            "/",
            "/test",
            "/login",
            "/registerAdmin",
            "/admin",
            "/addItems",
            "/addUsers",
            "/viewItems",
            "/modifyItems",
            "/viewItems/:id"
    })
    public String redirectOnReload() {return "forward:/index.html";}

    // TODO remove
    @RequestMapping({"/deleteItems"})
    public String redirectForDeleteTransaction() {
        store.initiateTransaction(TempUserID,Transaction.Type.delete);
        return "forward:/index.html";
    }


    /* LOGIN */
    @RequestMapping(value = "/post/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String loginSubmit(@RequestBody String body) {
        System.out.println(body);
        return body;
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


    /* MODIFY ITEMS */
    @RequestMapping("/post/modifyItems")
    @ResponseBody
    String modifyItemsForm(){
        System.out.println("Backend modify items");
        return "{}";
    }

    /* DELETE ITEMS */
    @RequestMapping(value = "/deleteItem/{id}", method = RequestMethod.GET)
    @ResponseBody
    String deleteItemsForm(@PathVariable("id") int id){
        Gson gson = new Gson();
        Map<Integer, Product> products = store.getProductCatalog().getProducts();
        String product = gson.toJson(products.get(id));
        if(product != null)
            store.deleteProduct(TempUserID,id);
        return product;
    }

    /* ADD USER */
    @RequestMapping(value = "/post/addUser", method = RequestMethod.POST)
    @ResponseBody
    String addUser(@RequestBody String json){
        System.out.println(json);
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        store.addNewUser(TempUserID,user);
        return gson.toJson(json);
    }

    /* ADD ITEMS */
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


    /* TRANSACTIONS */
    // TODO move to different controller files
    @RequestMapping(value = "/get/endTransaction", method = RequestMethod.GET)
    @ResponseBody
    public void endTransaction() {
        System.out.println("Ending transaction");
        store.endTransaction(TempUserID);
    }

    @RequestMapping(value = "/get/startAddTransaction", method = RequestMethod.GET)
    @ResponseBody
    public void startStoreAddTransaction() {
        System.out.println("Starting add transaction");
        store.initiateTransaction(TempUserID, Transaction.Type.add);
    }

    @RequestMapping(value = "/get/startModifyTransaction", method = RequestMethod.GET)
    @ResponseBody
    public void startStoreModifyTransaction() {
        System.out.println("Starting modify transaction");
        store.initiateTransaction(TempUserID, Transaction.Type.modify);
    }

    @RequestMapping(value = "/get/startDeleteTransaction", method = RequestMethod.GET)
    @ResponseBody
    public void startStoreDeleteTransaction() {
        System.out.println("Starting delete transaction");
        store.initiateTransaction(TempUserID, Transaction.Type.delete);
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
