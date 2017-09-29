'use strict';


angular.module('myApp.testPage', ['ngRoute','ngCookies'])

// //configure the routing  and connect the component to the specified controller (MainPageCtrl)
// .config(['$routeProvider', function($routeProvider){
//   $routeProvider.when('/test', {
//     templateUrl: 'testPage/testPage.html',
//     controller: 'TestPageCtrl'
//   })
// }])

/*
Controller is the JavaScript function that makes/changes/removes/controls the data.
The data from the controller is sent to the model (html page) which will be displayed in the view
*/
.controller('TestPageCtrl', ["$scope", "$http", "$cookies", "$cookieStore", function($scope, $http, $cookies, $cookieStore) {

   //Instance Variables Declaration
   $scope.data = "";
   $scope.dataPost = "";
   $scope.dataLoginResponse = "";
   $scope.userInfo = "";
   /*
   Adds an entry to the database by
   Making a post request to the server
   */
   $scope.testEntry = function(){
     //Example POST request
     var url = "/post/name";
     var data = {
        name : $scope.name,
     };
     //optional var config = {};
     $http.post(url,data)
     .then((res) => {
        $scope.dataPost = res.data;
     });
   }

   /*
   Gets the list of names from the database
   Is called when the button with ng-click="getEntry()" is clicked in testPage.html
   */
   $scope.getEntry = function(){
     //Example GET request to the backend
     var url = "/get/name";
     $http.get(url)
     .then((res) => {
       $scope.data = res.data

     });
   }

   /*
   Handles the login form
   */
   $scope.login = function(){
      var url = "/post/login";
      var data = {
         email : $scope.email,
         password : $scope.password
      }
      $http.post(url,data)
      .then((res) => {
         $scope.userInfo = res.data;
         $cookies.putObject('userInfo', $scope.userInfo);
      });
   }

   $scope.addProduct = function(){
    var url = "/post/addProduct";
    var data = {
      type : "tablet",
      model : "27",
      weight : "8",
      price : "1337",
      brand : "lenovo",
      processorType : "i7",
      cpuCores : "4", 
      ram : "8gb",
      hardDriveSize : "1",
      dimension : "21X14",
      batteryInfo : "8hours",
      operatingSystem : "ubuntu",
      cameraInfo : "good"
    }
    $http.post(url,data)
    .then((res) => {
       });
 }

}]);