'use strict';

angular.module('myApp.adminAddItem', ['ngCookies'])

.controller('adminAddItemController',['$scope', '$location', "$http", "$cookies",
   function adminAddItemController($scope, $location, $http, $cookies){
      var user = $cookies.getObject('userInfo');
      //check for admin rights
      if(user == undefined){
         console.log("Not Authenticated");
         $location.path('/login');
         return;
      }
      if(user.isAdmin != 1){
         alert("Not authorized to access page");
         $location.path('/');
         return;
      }
}]);
