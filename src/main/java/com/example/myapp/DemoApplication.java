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

import java.util.*;
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
            "/viewItems/{id}",
            "/viewUsers",
            "/modifyItems",
            "/deleteItems",
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
    public String logout(@RequestBody String json, @CookieValue("SESSIONID") int cookieId){
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);
        int id = jObject.get("id").getAsInt();

        if(store.getUserMapper().getUserCatalog().getUserById(id).getIsAdmin() == 0) {
            pointOfSale.cancelPurchase(id);
        }

        store.getUserMapper().getUserCatalog().removeActiveUserById(id);
        if(!store.getTransaction().isComplete() && store.getTransaction().getUserId() == cookieId ) {
            store.endTransaction();
            System.out.println(cookieId);
        }
        return "{\"message\":\"Logged Out\"}";
    }

    /* VIEW ITEMS */
    @RequestMapping("/get/products")
    @ResponseBody
    public String getProducts(){
        Gson gson = new Gson();
        return gson.toJson(store.getProductCatalog().getProducts());
    }

    /* ITEM DETAILS */
    @RequestMapping(value = "/getItem/{serialNumber}", method = RequestMethod.GET)
    @ResponseBody
    public String getProductBySerialNumber(@PathVariable("serialNumber") String serialNumber) {
        Gson gson = new Gson();
        Map<String, Product> items = store.getProductCatalog().getProducts();
        if (items.get(serialNumber) != null) {
            return gson.toJson(items.get(serialNumber));
        } else {
            //product might be already sold
            Map<String, Purchase> soldItems = pointOfSale.getPurchaseMapper().getPurchaseHistory().getPurchases();
            return gson.toJson(soldItems.get(serialNumber).getProduct());
        }
    }

    @RequestMapping(value = "/getItemModify/{serialNumber}", method = RequestMethod.GET)
    @ResponseBody
    public String getModifiedProductSerialNumber(@PathVariable("serialNumber") String serialNumber) {
        Gson gson = new Gson();
        Map<String, Product> items = store.getProductCatalog().getProducts();
        if (items.get(serialNumber) != null) {
            return gson.toJson(items.get(serialNumber));
        }
        else{
            return "{\"message\":\"Item Does Not Exist\"}";
        }
    }

    /* VIEW USERS */
    @RequestMapping(value = "/getUsers/", method = RequestMethod.GET)
    @ResponseBody
    public String getUsers(){
        Gson gson = new Gson();
        return gson.toJson(store.getUserMapper().getUserCatalog().getUsers());
    }


    /* DELETE ITEMS */
    @RequestMapping(value = "/deleteItem/{serialNumber}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteItemsForm(@PathVariable("serialNumber") String serialNumber,@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Map<String, Product> products = store.getProductCatalog().getProducts();
        String product = gson.toJson(products.get(serialNumber));
        if(product != null && store.getTransaction().getUserId() == cookieId)
            store.deleteProduct(serialNumber);
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
            if(store.getTransaction().getUserId() == 1000) {
                store.addNewUser(user);
                store.endTransaction();
            }
            return gson.toJson(json);
        } else {
            return "{\"message\":\"Duplicate\"}";
        }

    }

    /* DELETE USER */
    @RequestMapping(value = "/post/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser(@RequestBody String json, @CookieValue("SESSIONID") int cookieId){
        logout(json, cookieId);
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        store.initiateTransaction(cookieId, Transaction.Type.delete);
        if(store.getTransaction().getUserId() == cookieId) {
            store.deleteUser(user);
            store.endTransaction();
        }
    }

    /* ADD ITEMS */
    @RequestMapping(value = "/post/addMonitor", method = RequestMethod.POST)
    @ResponseBody
    public String addMonitor(@RequestBody String json, @CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product monitor = gson.fromJson(json, Monitor.class);
        if(store.getTransaction().getUserId() == cookieId)
            store.addNewProduct(monitor);
        return json;
    }

    @RequestMapping(value = "/post/addTablet", method = RequestMethod.POST)
    @ResponseBody
    public String addTablet(@RequestBody String json, @CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product tablet = gson.fromJson(json, Tablet.class);
        if(store.getTransaction().getUserId() == cookieId)
            store.addNewProduct(tablet);
        return json;
    }

    @RequestMapping(value = "/post/addDesktop", method = RequestMethod.POST)
    @ResponseBody
    public String addDesktop(@RequestBody String json, @CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product desktop = gson.fromJson(json, Desktop.class);
        if(store.getTransaction().getUserId() == cookieId)
            store.addNewProduct(desktop);
        return json;
    }

    @RequestMapping(value = "/post/addLaptop", method = RequestMethod.POST)
    @ResponseBody
    public String addLaptop(@RequestBody String json, @CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product laptop = gson.fromJson(json, Laptop.class);
        if(store.getTransaction().getUserId() == cookieId)
            store.addNewProduct(laptop);
        return json;
    }

    /* Cart routes */
    @RequestMapping(value="/post/addToCart", method = RequestMethod.POST)
    @ResponseBody
    String addToCart(@RequestBody String serialNumber,@CookieValue("SESSIONID") int cookieId){
        //for now because on refresh cart wont get recreated until login
        if(pointOfSale.viewCart(cookieId) == null){
            pointOfSale.startPurchase(cookieId);
        }
        if(pointOfSale.viewCart(cookieId).getSize() >= 7){
            return "{\"message\":\"Too many items in the cart\"}";
        }

        pointOfSale.addCartItem(cookieId, serialNumber);
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

    @RequestMapping(value="/get/cancelPurchase", method = RequestMethod.GET)
    @ResponseBody
    public String cancelPurchase(@CookieValue("SESSIONID") int cookieId){
        pointOfSale.cancelPurchase(cookieId);

        return "{\"message\":\"Purchase Canceled\"}";
    }

    @RequestMapping(value="/get/allCarts", method = RequestMethod.GET)
    @ResponseBody
    public String getAllCarts() {
        Gson gson = new Gson();
        return gson.toJson(pointOfSale.getCartCatalog().getCarts());
    }

    @RequestMapping(value="/post/removeFromCart", method = RequestMethod.POST)
    @ResponseBody
    String removeFromCart(@RequestBody String serialNumber, @CookieValue("SESSIONID") int cookieId){
        pointOfSale.removeCartItem(cookieId, serialNumber);
        return "{\"message\":\"Item Removed\"}";
    }

    @RequestMapping(value="/get/purchaseCart", method = RequestMethod.GET)
    @ResponseBody
    String purchaseCart(@CookieValue("SESSIONID") int cookieId){

        store.initiateTransaction(cookieId, Transaction.Type.purchase);
        List<String> productsToPurchase = pointOfSale.endPurchase(cookieId);
        store.endTransaction();
        store.initiateTransaction(cookieId, Transaction.Type.delete);
        for (String product : productsToPurchase)
            store.deleteProduct(product);
        store.endTransaction();
        return "{\"message\":\"Purchase Succesful\"}";
    }

/*--stuff for modify--*/

    @RequestMapping(value = "/post/modifyMonitor", method = RequestMethod.POST)
    @ResponseBody
    String modifyMonitor(@RequestBody String json,@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product monitor = gson.fromJson(json, Monitor.class);
        if(store.getTransaction().getUserId() == cookieId) {
            Map products = store.getProductCatalog().getProducts();
            for(Map.Entry<String, Product> entry : store.getProductCatalog().getProducts().entrySet())
            {
                if (entry.getValue().getModel()== monitor.getModel())
                {
                    store.modifyProduct(entry.getValue().getSerialNumber(),monitor);
                }
            }

        }
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/modifyTablet", method = RequestMethod.POST)
    @ResponseBody
    String modifyTablet(@RequestBody String json,@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product tablet = gson.fromJson(json, Tablet.class);
        if(store.getTransaction().getUserId() == cookieId)
            store.modifyProduct(tablet.getSerialNumber(), tablet);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/modifyDesktop", method = RequestMethod.POST)
    @ResponseBody
    String modifyDesktop(@RequestBody String json,@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product desktop = gson.fromJson(json, Desktop.class);
        if(store.getTransaction().getUserId() == cookieId)
            store.modifyProduct(desktop.getSerialNumber(), desktop);
        return gson.toJson(json);
    }

    @RequestMapping(value = "/post/modifyLaptop", method = RequestMethod.POST)
    @ResponseBody
    String modifyLaptop(@RequestBody String json,@CookieValue("SESSIONID") int cookieId){
        Gson gson = new Gson();
        Product laptop = gson.fromJson(json, Laptop.class);
        if(store.getTransaction().getUserId() == cookieId)
            store.modifyProduct(laptop.getSerialNumber(), laptop);
        return gson.toJson(json);
    }

    /* TRANSACTIONS */
    @RequestMapping(value = "/get/endTransaction", method = RequestMethod.GET)
    @ResponseBody
    public void endTransaction(@CookieValue("SESSIONID") int cookieId) {
        if(store.getTransaction().getUserId() == cookieId) {
            System.out.println("Ending transaction");
            store.endTransaction();
        }
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
        Map<String, Purchase> userPurchases = new HashMap<>();
        for (Map.Entry<String, Purchase> purchase : pointOfSale.getPurchaseMapper().getPurchaseHistory().getPurchases().entrySet()) {
            if(purchase.getValue().getUserId() == cookieId) {
                userPurchases.put(purchase.getKey(), purchase.getValue());
            }
        }
        return gson.toJson(userPurchases);
    }

    @RequestMapping(value="/get/returnItem/{serialNumber}", method = RequestMethod.GET)
    @ResponseBody
    String returnItem(@PathVariable(value="serialNumber") String serialNumber, @CookieValue("SESSIONID") int cookieId){
        Purchase purchase = pointOfSale.getPurchaseMapper().getPurchaseHistory().getPurchases().get(serialNumber);
        //Check that item exists
        if(purchase == null){
            //Error: Item not in purchase history
            return "{\"message\":\" Item not in purchase history\"}";
        } else {
            if(purchase.getUserId() != cookieId){
                //Error: User trying to return is not the buyer
                return "{\"message\":\"User trying to return is not the buyer\"}";
            } else {
                //Item Returned Successfully
                Product productToReturn = purchase.getProduct();
                store.initiateTransaction(cookieId, Transaction.Type.returnItem);
                pointOfSale.processReturn(cookieId, serialNumber);
                store.endTransaction();
                store.initiateTransaction(cookieId, Transaction.Type.add);
                store.addNewProduct(productToReturn);
                store.endTransaction();
                return "{\"message\":\"Item "+ serialNumber +" Returned Successfully\"}";
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("Done initializing");
    }
}