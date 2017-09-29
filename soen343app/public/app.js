'use strict';

/*
Authentication Module
*/
var auth343 = angular.module('Auth343', ['ngCookies'])
.service('$auth343', function($cookies, $rootScope){
   this.isLogged = function(){
      var user = $cookies.getObject('userInfo');
      var session = $cookies.get('session');
      if(user == undefined){
         return false;
      } else {
         if(user != undefined) return true;
      }
   }
   this.isAdmin = function(){
      var user = $cookies.getObject('userInfo');
      var session = $cookies.get('session');
      if((user != undefined) && (session != undefined)) {
         if(user.isAdmin == 1){
            return true;
         } else {
            return false;
         }
      }
   }
   this.requireLogin = function(){
      if(!this.isLogged()){
         $location.path("/login");
         return;
      }
   }
   this.updateValues = function(){
      $rootScope.loggedIn = this.isLogged();
      $rootScope.isAdmin = this.isAdmin();
   }
});

/*
Main Angular App
*/
var app = angular.module('myApp', [
    'ngRoute',
    'ngCookies',
    'myApp.testPage',
    'myApp.login',
    'myApp.adminAddItem',
    'myApp.userActions',
    'myApp.registerAdmin',
    'Auth343',
]);

app.config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: true
    });

    $routeProvider
        .when('/login', { templateUrl: 'login/login.html', controller: 'LoginController' })
        .when('/test', { templateUrl: 'testPage/testPage.html', controller: 'TestPageCtrl'})
        .when('/addItems', { templateUrl: 'adminAddItemsPage/adminAddItem.html', controller: 'adminAddItemController'})
        .when('/logout', { templateUrl: 'login/logout.html', controller: 'logoutController'});
    $routeProvider.otherwise({ redirectTo: '/' });
}]);

app.controller("AppController", function appController($scope, $auth343) {
    $scope.style = {
        "background-color" : "white"
    }
    $auth343.updateValues();
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
/*
Custom service to check Authentication and Authorization
returns true or false
*/
app.service('$auth343', function($cookies, $rootScope){
   this.isLogged = function(){
      var user = $cookies.getObject('userInfo');
      var session = $cookies.get('session');
      if(user == undefined){
         return false;
      } else {
         if(user != undefined) return true;
      }
   }
   this.isAdmin = function(){
      var user = $cookies.getObject('userInfo');
      var session = $cookies.get('session');
      if((user != undefined) && (session != undefined)) {
         if(user.isAdmin == 1){
            return true;
         } else {
            return false;
         }
      }
   }
   this.requireLogin = function(){
      if(!this.isLogged()){
         $location.path("/login");
         return;
      }
   }
   this.updateValues = function(){
      $rootScope.loggedIn = this.isLogged();
      $rootScope.isAdmin = this.isAdmin();
   }
});
