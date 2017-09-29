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
      $scope.addTablet = function(){
            var url = "/post/addItem";
            var data = {
              type : "tablet",
              model : $scope.modelNumber,
              weight : $scope.weight,
              price : $scope.price,
              brand : $scope.brand,
              processorType : $scope.processor,
              cpuCores : $scope.cores, 
              ram : $scope.ram,
              hardDriveSize : $scope.hardDriveSize,
              dimension : $scope.dimensions,
              batteryInfo : $scope.battery,
              operatingSystem : $scope.operatingSystem,
              cameraInfo : $scope.camera
            }
            $http.post(url,data)
            .then((res) => {
               });
      }
      $scope.addDesktop = function(){
            var url = "/post/addItem";
            var data = {
              type : "desktop",
              model : $scope.modelNumber,
              weight : $scope.weight,
              price : $scope.price,
              brand : $scope.brand,
              processorType : $scope.processor,
              cpuCores : $scope.cpuCores, 
              ram : $scope.ram,
              hardDriveSize : $scope.hardDriveSize,
              dimension : $scope.dimensions,
            }
            $http.post(url,data)
            .then((res) => {
               });
      }
      $scope.addLaptop = function(){
            var url = "/post/addItem";
            var data = {
              type : "laptop",
              model : $scope.model,
              weight : $scope.weight,
              price : $scope.price,
              brand : $scope.brand,
              processorType : $scope.processor,
              cpuCores : $scope.cors, 
              ram : $scope.ram,
              hardDriveSize : $scope.hardDriveSize,
              dimension : $scope.dimensions,
              batteryInfo : $scope.battery,
              operatingSystem : $scope.operatingSystem,
              cameraInfo : $scope.camera,
              touchScreen : $scope.touchScreen
            }
            $http.post(url,data)
            .then((res) => {
               });
      }
}]);
