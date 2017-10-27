'use strict';

angular.module('app', ['ngRoute', 'ngCookies'])
    .controller("mainController", ['$scope', '$cookies', '$http', "$location", function mainController($scope, $cookies, $http, $location) {
        $scope.loggedIn = function () {
            if ($cookies.get("SESSIONID") != null)
                return true;
            else
                return false;
        };

        // Check on page load
        $scope.loggedIn();

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
    }])

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

                if (typeof userInfoObject === 'undefined') {
                    $location.path("/");
                    return;
                } else if (userInfoObject.isAdmin === 0) {
                    $location.path("/");
                }
            }

        });
    });