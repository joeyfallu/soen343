package com.example.myapp;

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

import java.util.HashMap;
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

    /* Single page application routing */
    @RequestMapping({
            "/",
            "/login",
            "/register",
            "/catalog/desktops",
            "/catalog/monitors",
            "/catalog/laptops",
            "/catalog/tablets",
            "/cart",
            "/account",
            "/admin",
            "/addItems",
            "/viewItems",
            "/modifyItems",
            "/deleteItems",
            "/viewItems/{id}",
            "/viewUsers"
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

        try {
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
            } else if (e.getMessage().equals("Email not found")){
                return "{\"message\":\"Email not found\"}";
            } else if (e.getMessage().equals("User already logged in")) {
                return "{\"message\":\"User already logged in\"}";
            } else {
                e.printStackTrace();
                return "{\"message\":\"Error logging in\"}";
            }
        }
    }

    /* LOGOUT */
    @RequestMapping(value = "/post/logout", method = RequestMethod.POST)
    @ResponseBody
    public String logout(@RequestBody String json){
        JsonObject jobj = new Gson().fromJson(json, JsonObject.class);
        int id = jobj.get("id").getAsInt();

        if(store.getUserMapper().getUserCatalog().getUserById(id).getIsAdmin() == 0) {
            pointOfSale.cancelPurchase(id);
        }

        store.getUserMapper().getUserCatalog().removeActiveUserById(id);

        return "{\"message\":\"Logged Out\"}";
    }

    /* VIEW ITEMS */
    @RequestMapping("/get/products")
    @ResponseBody
    String getProducts(){
        Gson gson = new Gson();
        return gson.toJson(store.getProductCatalog().getProducts());
    }

    @RequestMapping(value = "/getItem/{id}", method = RequestMethod.GET)
    @ResponseBody

    public String getProductById(
            @PathVariable("id") int id) {

        Gson gson = new Gson();
        Map<Integer, Product> items = store.getProductCatalog().getProducts();
        if(items.get(id) != null){
            String productJson = gson.toJson(items.get(id));
            return productJson;
        } else {
            //product might be already sold
            Map<Integer, Purchase> soldItems = pointOfSale.getPurchaseMapper().getPurchaseHistory().getPurchases();
            String productJson = gson.toJson(soldItems.get(id).getProduct());
            return productJson;
        }
    }

    /* VIEW USERS */
    @RequestMapping(value = "/getUsers/", method = RequestMethod.GET)
    @ResponseBody
    public String getUsers(){
        Gson gson = new Gson();

        String json = gson.toJson(store.getUserMapper().getUserCatalog().getUsers());

        return json;
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
    public String addUser(@RequestBody String json){
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        String email = user.getEmail();
        boolean isDuplicateEmail = false;

        for (Map.Entry<Integer, User> entry : store.getUserMapper().getUserCatalog().getUsers().entrySet()) {
            if(entry.getValue().getEmail().equals(email)) {
                isDuplicateEmail = true;
                break;
            }
        }

        if (!isDuplicateEmail) {
            System.out.println("Adding user to database.");
            // Use a temporary ID to make a transaction while not logged in
            store.initiateTransaction(1000, Transaction.Type.add);
            store.addNewUser(1000, user);
            store.endTransaction(1000);
            return gson.toJson(json);
        } else {
            return "{\"message\":\"Duplicate\"}";
        }

    }

    /* DELETE USER */
    @RequestMapping(value = "/post/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser(@RequestBody String json, @CookieValue("SESSIONID") int cookieId){
        logout(json);

        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        store.initiateTransaction(cookieId, Transaction.Type.delete);
        store.deleteUser(cookieId, user);
        store.endTransaction(cookieId);
    }

    /* ADD ITEMS */
    @RequestMapping(value = "/post/addMonitor", method = RequestMethod.POST)
    @ResponseBody
    String addMonitor(@RequestBody String json, @CookieValue("SESSIONID") int cookieId){
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

        return gson.toJson(pointOfSale.viewCart(cookieId).getCartProducts());
    }

    @RequestMapping(value="/get/allCarts", method = RequestMethod.GET)
    @ResponseBody
    public String getAllCarts() {
        Gson gson = new Gson();
        return gson.toJson(pointOfSale.getCartCatalog().getCarts());
    }

    @RequestMapping(value="/post/removeFromCart", method = RequestMethod.POST)
    @ResponseBody
    String removeFromCart(@RequestBody int itemId, @CookieValue("SESSIONID") int cookieId){
        pointOfSale.removeCartItem(cookieId, itemId);

        store.getProductCatalog().addProduct(itemId, store.getProductMapper().get(itemId));

        store.addNewProduct(cookieId,store.getProductMapper().get(itemId));
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

    @RequestMapping(value="/get/purchaseHistory", method = RequestMethod.GET)
    @ResponseBody
    String getPurchaseHistory(@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Map<Integer, Purchase> userPurchases = new HashMap<>();
        for (Map.Entry<Integer, Purchase> purchase : pointOfSale.getPurchaseMapper().getPurchaseHistory().getPurchases().entrySet()) {
            //Code is potentially not secure
            //If the user uses a cookie manager, he could view other people's purchase history
            //by changing userId stored in the cookie to another user's cookie.
            //In a real production environment, proper cookie encryption is needed.
            if(purchase.getValue().getUserId() == cookieId) {
                userPurchases.put(purchase.getKey(), purchase.getValue());
            }
        }
        return gson.toJson(userPurchases);
    }

    @RequestMapping(value="/get/returnItem/{itemId}", method = RequestMethod.GET)
    @ResponseBody
    String returnItem(@PathVariable(value="itemId") int itemId, @CookieValue("SESSIONID") int cookieId){
        //Check that item exists
        if(pointOfSale.getPurchaseMapper().getPurchaseHistory().getPurchases().get(itemId) == null){
            //Error: Item not in purchase history
            return "{\"message\":\" Item not in purchase history\"}";
        } else {
            if(pointOfSale.getPurchaseMapper().getPurchaseHistory().getPurchases().get(itemId).getUserId() != cookieId){
                //Error: User trying to return is not the buyer
                return "{\"message\":\"User trying to return is not the buyer\"}";
            } else {
                //Item Returned Successfully
                pointOfSale.processReturn(cookieId, itemId);
                return "{\"message\":\"Item "+ itemId +" Returned Successfully\"}";
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("Done initializing");
    }
}