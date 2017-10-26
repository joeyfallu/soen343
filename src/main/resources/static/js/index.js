'use strict';

angular.module('app', ['ngRoute'])
    .controller("mainController", function mainController($scope) {

    })

    .config(function ($routeProvider, $locationProvider) {
        $locationProvider.html5Mode(true);

        $routeProvider
            .when('/', {templateUrl: "view/frontPage.html"}) // TODO controller
            .when('/test', {templateUrl: "view/testPage.html", controller: "testPageController"})
            .when('/login', {templateUrl: "view/login.html", controller: "loginController"})
            .when('/registerAdmin', {templateUrl: "view/registerAdmin.html", controller: "RegisterAdminCtrl"})
            .when('/admin', {templateUrl: "view/admin/admin.html"}) // TODO controller
            .when('/addItems', {templateUrl: "view/admin/addItems.html", controller: "addItemsCtrl"})
            .when('/addUsers', {templateUrl: "view/admin/addUsers.html", controller: "addUsersCtrl"})
            .when('/viewItems', {templateUrl: "view/admin/viewItems.html", controller: "viewItemsCtrl"})
            .when('/modifyItems', {templateUrl: "view/admin/modifyItems.html", controller: "modifyItemsController"})
            .when('/deleteItems', {templateUrl: "view/admin/deleteItems.html", controller: "deleteItemsController"})

            .otherwise({redirectTo: '/'});
    });