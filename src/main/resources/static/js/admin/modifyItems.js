'use strict';

const modifyItemsModule = angular.module('modifyItems', []);

modifyItemsModule.controller('modifyItemsController', function($scope, $http){
          $scope.getProdInfo = function (){
                var id = $scope.mId;
                var urlProduct = '/getItem/' + id;
                $scope.itemData = [];

                $http.get(urlProduct)
                .then((res) => {
                    $scope.itemData = res.data;
                    console.log($scope.itemData);
                    });
            };

        $scope.modifyTV = function(){
        console.log("HERE");
            var url ="/post/modifyTV";
            var data = {
                id : $scope.itemData.id,
                discriminator : "1",
                model : $scope.itemData.model,
                weight : $scope.itemData.weight,
                price : $scope.itemData.price,
                brand : $scope.itemData.brand,
                dimensions : $scope.itemData.dimensions,
            };
            $http.post(url,data)
            .then((res) => {
            });
         };
        $scope.modifyMonitor = function(){
              var url = "/post/modifyMonitor";
              var data = {
                id : $scope.itemData.id,
                discriminator : "2",
                model : $scope.itemData.model,
                weight : $scope.itemData.weight,
                price : $scope.itemData.price,
                brand : $scope.itemData.brand,
                size : $scope.itemData.size,
              }
              $http.post(url,data)
              .then((res) => {
              });
        };
        $scope.modifyTablet = function(){
                var url = "/post/modifyTablet";
                var data = {
                    id : $scope.itemData.id,
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
                    size : $scope.itemData.size,
                    batteryInfo : $scope.itemData.batteryInfo,
                    operatingSystem : $scope.itemData.operatingSystem,
                    cameraInfo : $scope.itemData.cameraInfo,
                }
                $http.post(url,data)
                .then((res) => {
                });
        };
        $scope.modifyDesktop = function(){
                var url = "/post/modifyDesktop";
                var data = {
                    id : $scope.itemData.id,
                    discriminator : "4",
                    model : $scope.itemData.model,
                    weight : $scope.itemData.weight,
                    price : $scope.itemData.price,
                    brand : $scope.itemData.brand,
                    dimensions : $scope.itemData.dimensions,
                    processorType : $scope.itemData.processorType,
                    cpuCores : $scope.itemData.cpuCores,
                    ram : $scope.itemData.ram,
                    hardDriveSize : $scope.itemData.hardDriveSize,
                    }
                $http.post(url,data)
                .then((res) => {
                });
        };
        $scope.modifyLaptop = function(){
            var url = "/post/modifyLaptop";
            var data = {
                id : $scope.itemData.id,
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
                touchScreen : $scope.itemData.touchScreen,
            }
            $http.post(url,data)
            .then((res) => {
            });
        };
});

