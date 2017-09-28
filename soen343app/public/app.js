'use strict';

var app = angular.module('myApp', [
    'ngRoute',
    'myApp.testPage',
    'myApp.login',
    'myApp.adminAddItem',
]);

app.config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: true
    });

    $routeProvider
        .when('/login', { templateUrl: 'login/login.html', controller: 'LoginController' })
        .when('/test', { templateUrl: 'testPage/testPage.html', controller: 'TestPageCtrl'})
        .when('/admin', { templateUrl: 'adminAddItemsPage/adminAddItem.html', controller: 'adminAddItemController'})
        .when('/logout', { templateUrl: 'login/logout.html', controller: 'logoutController'});
    $routeProvider.otherwise({ redirectTo: '/' });
}]);

app.controller("AppController", function appController($scope) {
    $scope.style = {
        "background-color" : "white"
    }


});

app.factory('Authentication', function() {
   var service = {};

   service.login = function(username, password, callback) {
        callback();
   };

   service.setCredentials = function(username, password) {

   };

   service.clearCredentials = function() {

   };

   return service;
});
