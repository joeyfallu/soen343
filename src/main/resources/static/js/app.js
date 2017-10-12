'use strict';

/* Main Angular App */
var app = angular.module('myApp', [
    'ngRoute',
    'ngCookies',
    'myApp.testPage',
    'myApp.login',
    'myApp.adminAddItem',
    'myApp.userActions',
    'myApp.registerAdmin',
    'myApp.viewItems'
]);

app.config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: true
    });

    $routeProvider
        .when('/login', { templateUrl: 'login.html', controller: 'LoginController' })
        .when('/addItems', { templateUrl: 'adminAddItem.html', controller: 'adminAddItemController'})
        .when('/logout', { templateUrl: 'logout.html', controller: 'logoutController'})
        .when('/home', { templateUrl: 'home.html', controller: 'homeController'})
        .when('/admin', { templateUrl: 'admin.html', controller: 'adminPageController'})
        .when('/test', { templateUrl: 'testPage.html', controller: 'TestPageCtrl'});

    $routeProvider.otherwise({ redirectTo: '/' });
}]);

app.controller("AppController", function appController($scope) {
    $scope.style = {
        "background-color" : "white"
    }
});

