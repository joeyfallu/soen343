'use strict';

var app = angular.module('modifyItem', []);
app.controller('modifyItemCtrl', function($scope, $http){

    var id = 6;     //TODO: initialize id from url
    var urlProduct = '/getItem/' + id;
    $scope.itemData = [];

    $http.get(urlProduct)
    .then((res) => {
        $scope.itemData = res.data;
        console.log($scope.itemData);
        });


       $scope.modifyTV = function(){
           var url ="/post/modify";
           var data = {
               discriminator : "1",
               model : $scope.itemData.model,
               weight : $scope.itemData.weight,
               price : $scope.itemData.price,
               brand : $scope.itemData.brand,
               dimensions : $scope.itemData.dimensions
           }
           $http.post(url,data)
           .then((res) => {
            });
        }

        $scope.modifyMonitor = function(){
            var url = "/post/modify";
            var data = {
                discriminator : "2",
                model : $scope.itemData.model,
                weight : $scope.itemData.weight,
                price : $scope.itemData.price,
                brand : $scope.itemData.brand,
                size : $scope.itemData.size
            }
            $http.post(url,data)
            .then((res) => {
            });
         }


        $scope.modifyTablet = function(){
            var url = "/post/modify";
            var data = {
                discriminator : "3",
                model : $scope.itemData.model,
                weight : $scope.itemData.weight,
                price : $scope.itemData.price,
                brand : $scope.itemData.brand,
                 dimensions : $scope.itemData.dimensions,
                 processorType : $scope.itemData.processorType,
                 cpuCores : $scope.itemData.cpuCores,
                 ram : $scope.itemData.ram,
                 hardDriveSize : $scope.itemData.hardDriveSize,
                 size : $scope.itemData.size;
                 batteryInfo : $scope.itemData.batteryInfo,
                 operatingSystem : $scope.itemData.operatingSystem,
                 cameraInfo : $scope.itemData.cameraInfo
            }
            $http.post(url,data)
            .then((res) => {
            });
         }

         $scope.modifyDesktop = function(){
         var url = "/post/modify";
         var data = {
            discriminator : "4",
            model : $scope.itemData.model,
            weight : $scope.itemData.weight,
            price : $scope.itemData.price,
            brand : $scope.itemData.brand,
            dimensions : $scope.itemData.dimensions
            processorType : $scope.itemData.processorType,
            cpuCores : $scope.itemData.cpuCores,
            ram : $scope.itemData.ram,
            hardDriveSize : $scope.itemData.hardDriveSize
         }
         $http.post(url,data)
         .then((res) => {
          });
         }

         $scope.modifyLaptop = function(){
         var url = "/post/modify";
         var data = {
             discriminator : "5",
             model : $scope.itemData.model,
             weight : $scope.itemData.weight,
             price : $scope.itemData.price,
             brand : $scope.itemData.brand,
             processorType : $scope.itemData.processorType,
             cpuCores : $scope.itemData.cpuCores,
             ram : $scope.itemData.ram,
             hardDriveSize : $scope.itemData.hardDriveSize,
             size : $scope.itemData.size,
             batteryInfo : $scope.itemData.batteryInfo,
             operatingSystem : $scope.itemData.operatingSystem,
             cameraInfo : $scope.itemData.cameraInfo,
             touchScreen : $scope.itemData.touchScreen
         }
         $http.post(url,data)
         .then((res) => {
          });
         }


});