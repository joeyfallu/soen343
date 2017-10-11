'use strict';

angular.module('myApp.modify', ['ngCookies'])

.controller('modifyCtrl',['$scope', '$location', "$http", "$cookies",
   function modifyCtrl($scope, $location, $http, $cookies){
      var user = $cookies.getObject('userInfo');
      //check for admin
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
      $scope.modifyMonitor = function(){
            var url = "/post/addItem";
            var data = {
              type : "monitor",
              model : $scope.monitorModelNumber,
              weight : $scope.monitorWeight,
              price : $scope.monitorPrice,
              brand : $scope.monitorBrand,
              size : $scope.monitorSize
            }
            $http.post(url,data)
            .then((res) => {
               });
      }

      $scope.modifyTV = function(){
            var url = "/post/modify";
            var data = {
              type : "television",
              model : $scope.tvModelNumber,
              weight : $scope.tvWeight,
              price : $scope.tvPrice,
              brand : $scope.tvBrand,
              dimensions : $scope.tvDimensions
            }
            $http.post(url,data)
            .then((res) => {
               });
      }

      $scope.modifyTablet = function(){
            var url = "/post/modify";
            var data = {
              type : "tablet",
              model : $scope.tabletModelNumber,
              weight : $scope.tabletWeight,
              price : $scope.tabletPrice,
              brand : $scope.tabletBrand,
              processorType : $scope.tabletProcessor,
              cpuCores : $scope.tabletCores,
              ram : $scope.tabletRam,
              hardDriveSize : $scope.tabletHardDriveSize,
              dimension : $scope.tabletDimensions,
              batteryInfo : $scope.tabletBattery,
              operatingSystem : $scope.tabletOperatingSystem,
              cameraInfo : $scope.tabletCamera,

            }
            $http.post(url,data)
            .then((res) => {
               });
      }
      $scope.modifyDesktop = function(){
            var url = "/post/modify";
            var data = {
              type : "desktop",
              model : $scope.desktopModelNumber,
              weight : $scope.desktopWeight,
              price : $scope.desktopPrice,
              brand : $scope.desktopBrand,
              processorType : $scope.desktopProcessor,
              cpuCores : $scope.desktopCpuCores,
              ram : $scope.desktopRam,
              hardDriveSize : $scope.desktopHardDriveSize,
              dimension : $scope.desktopDimensions
            }
            $http.post(url,data)
            .then((res) => {
               });
      }
      $scope.modifyLaptop = function(){
            var url = "/post/modify";
            var data = {
              type : "laptop",
              model : $scope.laptopModel,
              weight : $scope.laptopWeight,
              price : $scope.laptopPrice,
              brand : $scope.laptopBrand,
              processorType : $scope.laptopProcessor,
              cpuCores : $scope.laptopCores,
              ram : $scope.laptopRam,
              hardDriveSize : $scope.laptopHardDriveSize,
              dimension : $scope.laptopDimensions,
              batteryInfo : $scope.laptopBattery,
              operatingSystem : $scope.laptopOperatingSystem,
              cameraInfo : $scope.laptopCamera,
              touchScreen : $scope.laptopTouchScreen
            }
            $http.post(url,data)
            .then((res) => {
               });
      }
}]);
