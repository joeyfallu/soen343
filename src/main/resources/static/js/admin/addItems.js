'use strict';

angular.module('app').controller('addItemsCtrl', function ($scope, $http) {
    $scope.tvMessage = "";
    $scope.monitorMessage = "";
    $scope.tabletMessage = "";
    $scope.desktopMessage = "";
    $scope.laptopMessage = "";

    $scope.addMonitor = function () {
        var url = "/post/addMonitor";
        var data = {
            id: "0",
            model: $scope.monitorModelNumber,
            weight: $scope.monitorWeight,
            price: $scope.monitorPrice,
            brand: $scope.monitorBrand,
            size: $scope.monitorSize,
            discriminator: "2",
        };

        $http.post(url, data).then((res) => {
            $scope.monitorMessage = "Success!";
        }).catch((err) => {
            console.log("ERROR:");
            console.log(err);
            $scope.monitorMessage = "Error. Check console.";
        });
    };

    $scope.addTablet = function () {
        var url = "/post/addTablet";
        var data = {
            id: "0",
            model: $scope.tabletModelNumber,
            weight: $scope.tabletWeight,
            price: $scope.tabletPrice,
            brand: $scope.tabletBrand,
            dimensions: $scope.tabletDimensions,
            processorType: $scope.tabletProcessor,
            cpuCores: $scope.tabletCores,
            ram: $scope.tabletRam,
            hardDriveSize: $scope.tabletHardDriveSize,
            size: $scope.tabletSize,
            batteryInfo: $scope.tabletBattery,
            operatingSystem: $scope.tabletOperatingSystem,
            cameraInfo: $scope.tabletCamera,
            discriminator: "3",
        };

        $http.post(url, data).then((res) => {
            $scope.tabletMessage = "Success!";
        }).catch((err) => {
            console.log("ERROR:");
            console.log(err);
            $scope.tabletMessage = "Error. Check console.";
        });
    };

    $scope.addDesktop = function () {
        var url = "/post/addDesktop";
        var data = {
            id: "0",
            model: $scope.desktopModelNumber,
            weight: $scope.desktopWeight,
            price: $scope.desktopPrice,
            brand: $scope.desktopBrand,
            dimensions: $scope.desktopDimensions,
            processorType: $scope.desktopProcessor,
            cpuCores: $scope.desktopCores,
            ram: $scope.desktopRam,
            hardDriveSize: $scope.desktopHardDriveSize,
            discriminator: "4",
        };

        $http.post(url, data).then((res) => {
            $scope.desktopMessage = "Success!";
        }).catch((err) => {
            console.log("ERROR:");
            console.log(err);
            $scope.desktopMessage = "Error. Check console.";
        });
    };

    $scope.addLaptop = function () {
        var url = "/post/addLaptop";
        var data = {
            id: "0",
            model: $scope.laptopModelNumber,
            weight: $scope.laptopWeight,
            price: $scope.laptopPrice,
            brand: $scope.laptopBrand,
            processorType: $scope.laptopProcessor,
            cpuCores: $scope.laptopCores,
            ram: $scope.laptopRam,
            hardDriveSize: $scope.laptopHardDriveSize,
            size: $scope.laptopSize,
            batteryInfo: $scope.laptopBattery,
            operatingSystem: $scope.laptopOperatingSystem,
            camera: $scope.laptopCamera,
            touchScreen: $scope.laptopTouchScreen,
            discriminator: "5",
        };

        $http.post(url, data).then((res) => {
            $scope.laptopMessage = "Success!";
        }).catch((err) => {
            console.log("ERROR:");
            console.log(err);
            $scope.laptopMessage = "Error. Check console.";
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