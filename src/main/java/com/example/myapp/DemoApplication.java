package com.example.myapp;

import com.example.myapp.database.ProductMapper;
import com.example.myapp.database.UserMapper;
import com.example.myapp.productCatalog.*;
import com.example.myapp.purchases.Purchase;
import com.example.myapp.transactions.Transaction;
import com.example.myapp.userCatalog.*;
import com.example.myapp.productCatalog.Desktop;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class DemoApplication {

    @Autowired
    private Store store;
    @Autowired
    private PointOfSale pointOfSale;



    //private static Store store;

    /* Single page application routing */
    // https://stackoverflow.com/questions/24837715/spring-boot-with-angularjs-html5mode/44850886#44850886
    @RequestMapping({
            "/",
            "/test",
            "/login",
            "/registerAdmin", // TODO change to /register
            "/catalog",
            "/register",
            "/catalog/desktops",
            "/catalog/monitors",
            "/catalog/laptops",
            "/catalog/tablets",
            "/cart",
            "/history",
            "/admin",
            "/addItems",
            "/addUsers",
            "/viewItems",
            "/modifyItems",
            "/deleteItems",
            "/viewItems/{id}"
    })
    public String redirectOnReload() {
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

            //Creates the users cart
            if(loggedInUser.getIsAdmin() == 0){
                pointOfSale.startPurchase(loggedInUser.getId());
            }

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


        pointOfSale.cancelPurchase(id);
        return "{\"message\":\"Logged Out\"}";
    }

    /* VIEW ITEMS */
    @RequestMapping("/get/products")
    @ResponseBody
    String getProducts(){
        Gson gson = new Gson();

        //TODO remove this piece of test code \/
        testPOS();
        String json = gson.toJson(store.getProductCatalog().getProducts());

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
    String deleteItemsForm(@PathVariable("id") int id,@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Map<Integer, Product> products = store.getProductCatalog().getProducts();
        String product = gson.toJson(products.get(id));
        if(product != null)
            store.deleteProduct(cookieId,id);
        return product;
    }

    /* ADD USER */
    @RequestMapping(value = "/post/addUser", method = RequestMethod.POST)
    @ResponseBody
    String addUser(@RequestBody String json,@CookieValue("SESSIONID") int cookieId){
        System.out.println(json);
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);

        boolean DuplicateEmail = false;
        String email = user.getEmail();

        for (Map.Entry<Integer, User> entry : store.getUserMapper().getUserCatalog().getUsers().entrySet()) {
            if(entry.getValue().getEmail().equals(email)) {
                DuplicateEmail = true;
                //Sets DuplicateEmail value to true if a duplicate email is detected in the database
            }
            //Continues to the next map entry
        }
        if (DuplicateEmail == true) {
            return "{\"message\":\"Duplicate\"}";
        }
        else {
            store.addNewUser(cookieId, user);
            return gson.toJson(json);
        }

    }

    /* ADD ITEMS */
    @RequestMapping(value = "/post/addMonitor", method = RequestMethod.POST)
    @ResponseBody
    String addMonitor(@RequestBody String json,@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product monitor = gson.fromJson(json, Monitor.class);
        store.addNewProduct(cookieId,monitor);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/addTablet", method = RequestMethod.POST)
    @ResponseBody
    String addTablet(@RequestBody String json,@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product tablet = gson.fromJson(json, Tablet.class);
        store.addNewProduct(cookieId,tablet);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/addDesktop", method = RequestMethod.POST)
    @ResponseBody
    String addDesktop(@RequestBody String json,@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product desktop = gson.fromJson(json, Desktop.class);
        store.addNewProduct(cookieId,desktop);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/addLaptop", method = RequestMethod.POST)
    @ResponseBody
    String addLaptop(@RequestBody String json,@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product laptop = gson.fromJson(json, Laptop.class);
        store.addNewProduct(cookieId,laptop);
        return gson.toJson(json);
    }

    /* Cart routes */
    @RequestMapping(value="/post/addToCart", method = RequestMethod.POST)
    @ResponseBody
    String addToCart(@RequestBody int itemId,@CookieValue("SESSIONID") int cookieId){

        //for now because on refresh cart wont get recreated until login
        if(pointOfSale.viewCart(cookieId) == null){
            pointOfSale.startPurchase(cookieId);
        }

        if(pointOfSale.viewCart(cookieId).getSize() >= 7){
            return "{\"message\":\"Too many items in the cart\"}";
        }

        pointOfSale.addCartItem(cookieId, itemId);
//        store.deleteProduct(cookieId, itemId);

        return "{\"message\":\"Added to cart\"}";
    }

    @RequestMapping(value="/get/cart", method = RequestMethod.GET)
    @ResponseBody
    String getCart(@CookieValue("SESSIONID") int cookieId){

        //for now because on refresh cart wont get recreated until login
        if(pointOfSale.viewCart(cookieId) == null){
            pointOfSale.startPurchase(cookieId);
        }

        Gson gson = new Gson();
        System.out.println(pointOfSale.viewCart(cookieId).getCartProducts());
        return gson.toJson(pointOfSale.viewCart(cookieId).getCartProducts());
    }

    @RequestMapping(value="/post/removeFromCart", method = RequestMethod.POST)
    @ResponseBody
    String removeFromCart(@RequestBody int itemId, @CookieValue("SESSIONID") int cookieId){
        pointOfSale.removeCartItem(cookieId, itemId);
        pointOfSale.addCartItem(cookieId, itemId);
        return "{\"message\":\"Item Removed\"}";
    }

    @RequestMapping(value="/get/purchaseCart", method = RequestMethod.GET)
    @ResponseBody
    String purchaseCart(@CookieValue("SESSIONID") int cookieId){

        pointOfSale.endPurchase(cookieId);

        return "{\"message\":\"Purchase Succesful\"}";
    }

/*--stuff for modify--*/


    @RequestMapping(value = "/post/modifyMonitor", method = RequestMethod.POST)
    @ResponseBody
    String modifyMonitor(@RequestBody String json,@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product monitor = gson.fromJson(json, Monitor.class);
        store.modifyProduct(cookieId,monitor.getId(), monitor);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/modifyTablet", method = RequestMethod.POST)
    @ResponseBody
    String modifyTablet(@RequestBody String json,@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product tablet = gson.fromJson(json, Tablet.class);
        store.modifyProduct(cookieId,tablet.getId(), tablet);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/modifyDesktop", method = RequestMethod.POST)
    @ResponseBody
    String modifyDesktop(@RequestBody String json,@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product desktop = gson.fromJson(json, Desktop.class);
        store.modifyProduct(cookieId,desktop.getId(), desktop);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/modifyLaptop", method = RequestMethod.POST)
    @ResponseBody
    String modifyLaptop(@RequestBody String json,@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product laptop = gson.fromJson(json, Laptop.class);
        store.modifyProduct(cookieId,laptop.getId(), laptop);
        return gson.toJson(json);
    }



    /* TRANSACTIONS */
    // TODO move to different controller files
    @RequestMapping(value = "/get/endTransaction", method = RequestMethod.GET)
    @ResponseBody
    public void endTransaction(@CookieValue("SESSIONID") int cookieId) {
        System.out.println("Ending transaction");
        store.endTransaction(cookieId);
    }

    @RequestMapping(value = "/get/startAddTransaction", method = RequestMethod.GET)
    @ResponseBody
    public void startStoreAddTransaction(@CookieValue("SESSIONID") int cookieId) {
        System.out.println("Starting add transaction");
        store.initiateTransaction(cookieId, Transaction.Type.add);
    }

    @RequestMapping(value = "/get/startModifyTransaction", method = RequestMethod.GET)
    @ResponseBody
    public void startStoreModifyTransaction(@CookieValue("SESSIONID") int cookieId) {
        System.out.println("Starting modify transaction");
        store.initiateTransaction(cookieId, Transaction.Type.modify);
    }

    @RequestMapping(value = "/get/startDeleteTransaction", method = RequestMethod.GET)
    @ResponseBody
    public void startStoreDeleteTransaction(@CookieValue("SESSIONID") int cookieId) {
        System.out.println("Starting delete transaction");
        store.initiateTransaction(cookieId, Transaction.Type.delete);

    }





    public static void main(String[] args) {
        //start the server
        SpringApplication.run(DemoApplication.class, args);
        //aop test


        System.out.println("Done initializing");
    }

    public void testPOS()
    {
       // pointOfSale.setStore(store);
//        System.out.println(store.toString());
//        System.out.println(pointOfSale.getStore().toString());
       /* pointOfSale.getStore().initiateTransaction(99,Transaction.Type.purchase);
        Monitor mn = new Monitor(69,"sony",69,69,"sony",69,2);
        Purchase p = new Purchase(99,"never",mn);
        pointOfSale.getPurchaseMapper().purchase(p);
        pointOfSale.getPurchaseMapper().commit();
        pointOfSale.getStore().endTransaction(99);*/
//       pointOfSale.getStore().initiateTransaction(99,Transaction.Type.purchase);
//        Monitor mn = new Monitor(69,"sony",69,69,"sony",69,2);
//        Purchase p = new Purchase(99,"never",mn);
//        pointOfSale.getPurchaseMapper().returnItem(69);
//        pointOfSale.getPurchaseMapper().commit();
//        pointOfSale.getStore().endTransaction(99);
//        pointOfSale.processReturn(99,84);


    }
}
