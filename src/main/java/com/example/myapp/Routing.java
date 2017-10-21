package com.example.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Routing {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomeTemplate() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginTemplate() {
        return "login";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPageTemplate() {
        return "admin/admin";
    }

    @RequestMapping(value="/addItems", method = RequestMethod.GET)
    public String getAddItemsTemplate() {
        return "admin/addItems";
    }

    @RequestMapping(value="/viewItems", method = RequestMethod.GET)
    public String getViewItemsTemplate(){
        return "admin/viewItems";
    }

    @RequestMapping(value="/modifyItems", method = RequestMethod.GET)
    public String getModifyItemsTemplate(){
        return "admin/modifyItems";
    }

    @RequestMapping(value="/deleteItems", method = RequestMethod.GET)
    public String getDeleteItemsTemplate(){
        return "admin/deleteItems";
    }

    @RequestMapping(value = "/registerAdmin", method = RequestMethod.GET)
    public String getRegisterAdminTemplate() {
        return "registerAdmin";
    }

    @RequestMapping(value="/addUsers", method = RequestMethod.GET)
    public String getAddUsersTemplate() {
        return "admin/addUsers";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTestPageTemplate() {
        return "testPage";
    }

}
