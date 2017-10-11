'use-script';

var app = angular.module('addItems', []);
app.controller('addItemsCtrl', function($scope, $http){

//      $scope.addMonitor = function(){
//              var url = "/post/addItem";
//              var data = {
//                discriminator : "2",
//                model : $scope.monitorModelNumber,
//                weight : $scope.monitorWeight,
//                price : $scope.monitorPrice,
//                brand : $scope.monitorBrand,
//                size : $scope.monitorSize
//              }
//              $http.post(url,data)
//              .then((res) => {
//                 });
//        }

        $scope.addTv = function(){
              var url = "/post/addTv";
              var data = {
                discriminator : "1",
                model : $scope.tvModelNumber,
                weight : $scope.tvWeight,
                price : $scope.tvPrice,
                brand : $scope.tvBrand,
                dimensions : $scope.tvDimensions
                }
                console.log("test");
              $http.post(url,data)
              .then((res) => {
                 });
        }

//        $scope.addTablet = function(){
//              var url = "/post/addItem";
//              var data = {
//                discriminator : "3",
//                model : $scope.tabletModelNumber,
//                weight : $scope.tabletWeight,
//                price : $scope.tabletPrice,
//                brand : $scope.tabletBrand,
//                dimensions : $scope.tabletDimensions,
//                processorType : $scope.tabletProcessor,
//                cpuCores : $scope.tabletCores,
//                ram : $scope.tabletRam,
//                hardDriveSize : $scope.tabletHardDriveSize,
//                size : $scope.tabletSize;
//                batteryInfo : $scope.tabletBattery,
//                operatingSystem : $scope.tabletOperatingSystem,
//                cameraInfo : $scope.tabletCamera
//
//              }
//              $http.post(url,data)
//              .then((res) => {
//                 });
//        }
//        $scope.addDesktop = function(){
//              var url = "/post/addItem";
//              var data = {
//                discriminator : "4",
//                model : $scope.desktopModelNumber,
//                weight : $scope.desktopWeight,
//                price : $scope.desktopPrice,
//                brand : $scope.desktopBrand,
//                dimensions : $scope.desktopDimensions
//                processorType : $scope.desktopProcessor,
//                cpuCores : $scope.desktopCores,
//                ram : $scope.desktopRam,
//                hardDriveSize : $scope.desktopHardDriveSize
//              }
//              $http.post(url,data)
//              .then((res) => {
//                 });
//        }
//        $scope.addLaptop = function(){
//              var url = "/post/addItem";
//              var data = {
//                discriminator : "5",
//                model : $scope.laptopModelNumber,
//                weight : $scope.laptopWeight,
//                price : $scope.laptopPrice,
//                brand : $scope.laptopBrand,
//                processorType : $scope.laptopProcessor,
//                cpuCores : $scope.laptopCores,
//                ram : $scope.laptopRam,
//                hardDriveSize : $scope.laptopHardDriveSize,
//                size : $scope.laptopSize,
//                batteryInfo : $scope.laptopBattery,
//                operatingSystem : $scope.laptopOperatingSystem,
//                cameraInfo : $scope.laptopCamera,
//                touchScreen : $scope.laptopTouchScreen
//              }
//              $http.post(url,data)
//              .then((res) => {
//                 });
//        }
});