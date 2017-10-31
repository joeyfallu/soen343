'use strict';

angular.module('app', ['ngRoute', 'ngCookies'])

    .controller("mainController", function mainController($scope, $cookies, $http, $location) {

        $scope.authenticate = function () {
            return typeof $cookies.get("SESSIONID") !== 'undefined';
        };

        $scope.logout = function () {
            const url = '/post/logout';
            let data = {
                id: $cookies.get("SESSIONID")
            };

            $http.post(url, data).then((res) => {
                if (res.data.message)
                    $scope.errorMsg = res.data.message;
                $cookies.remove("SESSIONID");
                $cookies.remove("USERINFO");
                $location.path('/');
            });
        };

    })

    .config(function ($routeProvider, $locationProvider) {
        $locationProvider.html5Mode(true);

        $routeProvider
            .when('/', {
                templateUrl: "view/frontPage.html",
                controller: "frontPageController",
                controllerAs: "frontPageController"
            })
            .when('/test', {templateUrl: "view/testPage.html", controller: "testPageController"})
            .when('/login', {templateUrl: "view/login.html", controller: "loginController"})
            .when('/registerAdmin', {templateUrl: "view/registerAdmin.html", controller: "RegisterAdminCtrl"})
            .when('/catalog', {templateUrl: "view/catalog.html", controller: "catalogController"})
            .when('/cart', {templateUrl: "view/cart.html", controller: "cartController"})
            .when('/history', {templateUrl: "view/PurchaseHistory.html", controller: "purchaseHistoryController"})
            .when('/admin', {templateUrl: "view/admin/admin.html"}) // TODO controller
            .when('/addItems', {templateUrl: "view/admin/addItems.html", controller: "addItemsCtrl"})
            .when('/addUsers', {templateUrl: "view/admin/addUsers.html", controller: "addUsersCtrl"})
            .when('/viewItems', {templateUrl: "view/admin/viewItems.html", controller: "viewItemsCtrl"})
            .when('/modifyItems', {templateUrl: "view/admin/modifyItems.html", controller: "modifyItemsController"})
            .when('/deleteItems', {templateUrl: "view/admin/deleteItems.html", controller: "deleteItemsController"})
            .when('/viewItems/:id', {templateUrl: "view/viewItemsDetail.html", controller: "viewItemsDetailCtrl"})

            .otherwise({redirectTo: '/'});
    })

    .run(function ($rootScope, $location, $cookies) {
        // Disable access to admin pages
        $rootScope.$on("$routeChangeStart", function (event, next, current) {
            let userInfoObject = $cookies.getObject("USERINFO");

            if (next.templateUrl === "view/admin/admin.html" ||
                next.templateUrl === "view/admin/addItems.html" ||
                next.templateUrl === "view/admin/addUsers.html" ||
                next.templateUrl === "view/admin/viewItems.html" ||
                next.templateUrl === "view/admin/modifyItems.html" ||
                next.templateUrl === "view/admin/deleteItems.html") {

                if (typeof userInfoObject === 'undefined')
                    $location.path("/");
                else if (userInfoObject.isAdmin === 0)
                    $location.path("/");

            }

        });
    });