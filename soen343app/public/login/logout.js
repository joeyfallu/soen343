'use strict';

angular.module('myApp.logout', ['ngCookies'])

.controller('logoutController', ['$scope', '$location', "$http", "$cookies",
function logoutController($scope, $location, $http, $cookies) {
   var user = $cookies.getObject('userInfo');
   var session = $cookies.get('session');
   if(user != undefined){
      $cookies.remove('userInfo');
   }
   if(session != undefined){
      $cookies.remove('session');
   }
   console.log(user.email, " has been logged out");

}]);
