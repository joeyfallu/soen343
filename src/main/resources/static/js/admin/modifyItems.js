'use strict';

angular.module('app').controller('modifyItemsController', function($scope, $http){
          $scope.getProdInfo = function (){
                $scope.message = "";
                var id = $scope.mId;
                var urlProduct = '/getItem/' + id;
                $scope.itemData = [];

                $http.get(urlProduct)
                .then((res) => {
                    if(res.data == null){
                        $scope.message = "Product with ID: " + $scope.mId + " does not exist.";
                    }
                    $scope.itemData = res.data;
                    console.log($scope.itemData);

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
                console.log(res);
                $scope.message = "Successful product modification!";
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
                console.log(res);
                $scope.message = "Successful product modification!";
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
                console.log(res);
                $scope.message = "Successful product modification!";
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
            console.log(res);
            $scope.message = "Successful product modification!";
            });
        };

    $scope.endTransaction = function(){
        var url = "/post/endTransaction";

        $http.post(url);
    }


       window.onbeforeunload =  function(e){
            var url = "/post/endTransaction";
            $http.post(url);
            return "Leaving Page";
       };
});
