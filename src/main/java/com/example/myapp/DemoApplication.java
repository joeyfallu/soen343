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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

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
            "/viewItems/{id}"
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
    public String loginSubmit(@RequestBody String body, HttpServletResponse response) {

        Gson gson = new Gson();
        User tempUser = gson.fromJson(body, User.class);
        String email = tempUser.getEmail();
        String password = tempUser.getPassword();
        tempUser = null;                            //This object isn't needed anymore
        try{
            User loggedInUser = store.getUserMapper().getUserCatalog().login(email, password);
            System.out.println("Successful login by: " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + " "+ loggedInUser.getEmail());
            //Send two cookies which store the user's id and info as json
            Cookie userIdCookie = new Cookie("SESSIONID", ""+loggedInUser.getId());
            userIdCookie.setMaxAge(24*60*60);
            userIdCookie.setPath("/");
            userIdCookie.setHttpOnly(false);
            response.addCookie(userIdCookie);
            //the json needs to be URL encoded in order to be stored in a cookie.
            String userInfoStr = URLEncoder.encode(gson.toJson(loggedInUser), "UTF-8");
            Cookie userInfoCookie = new Cookie("USERINFO", userInfoStr);
            userInfoCookie.setPath("/");
            userInfoCookie.setHttpOnly(false);
            userInfoCookie.setMaxAge(24*60*60);
            response.addCookie(userInfoCookie);
            //Could be encrypted for security
            String userJsonResponse = gson.toJson(loggedInUser);
            //Responds with a User Object in Json format
            return userJsonResponse;
        } catch(Exception e) {
            if(e.getMessage().equals("Wrong password")){
                return "{\"message\":\"Wrong password\"}";
            }
            if (e.getMessage().equals("Email not found")){
                return "{\"message\":\"Email not found\"}";
            } else {
                e.printStackTrace();
                return "{\"message\":\"Error logging in\"}";
            }
        }
    }

    /* LOGOUT */
    @RequestMapping(value = "/post/logout", method = RequestMethod.POST)
    @ResponseBody
    String logout(@RequestBody String json){
        JsonObject jobj = new Gson().fromJson(json, JsonObject.class);
        int id = jobj.get("id").getAsInt();
        store.getUserMapper().getUserCatalog().removeActiveUserById(id);
        return "{\"message\":\"Logged Out\"}";
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

        boolean DuplicateEmail = false;
        String email = user.getEmail();

        for (Map.Entry<Integer, User> entry : store.getUserMapper().getUserCatalog().getUsers().entrySet()) {
            if(entry.getValue().getEmail().equals(email)) {
                DuplicateEmail = true;
            }
            //Continues to the next map entry
        }
        if (DuplicateEmail == false) {
            store.addNewUser(TempUserID, user);
            return gson.toJson(json);
        }
        else if (DuplicateEmail == true) {
            return "{\"message\":\"Duplicate\"}";
        }
        else {
            return " ";
        }

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
        store.getUserMapper().getUserCatalog().setUsers(store.getUserMapper().getAll());

        System.out.println("Done initializing");
    }
}
