'use strict';

angular.module('app').controller('addItemsCtrl', function ($scope, $http) {

    /* Form submission */
    $scope.tvMessage = "";
    $scope.monitorMessage = "";
    $scope.tabletMessage = "";
    $scope.desktopMessage = "";
    $scope.laptopMessage = "";

    $scope.addMonitor = function () {
        let data = {
            id: "0",
            model: $scope.monitorModelNumber,
            weight: $scope.monitorWeight,
            price: $scope.monitorPrice,
            brand: $scope.monitorBrand,
            size: $scope.monitorSize,
            discriminator: "2",
        };

        $http.post("/post/addMonitor", data).then((res) => {
            $scope.monitorMessage = "Success!";
        }).catch((err) => {
            console.log("ERROR:");
            console.log(err);
            $scope.monitorMessage = "Error. Check console.";
        });
    };

    $scope.addTablet = function () {
        let data = {
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

        $http.post("/post/addTablet", data).then((res) => {
            $scope.tabletMessage = "Success!";
        }).catch((err) => {
            console.log("ERROR:");
            console.log(err);
            $scope.tabletMessage = "Error. Check console.";
        });
    };

    $scope.addDesktop = function () {
        let data = {
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

        $http.post("/post/addDesktop", data).then((res) => {
            $scope.desktopMessage = "Success!";
        }).catch((err) => {
            console.log("ERROR:");
            console.log(err);
            $scope.desktopMessage = "Error. Check console.";
        });
    };

    $scope.addLaptop = function () {
        let data = {
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

        $http.post("/post/addLaptop", data).then((res) => {
            $scope.laptopMessage = "Success!";
        }).catch((err) => {
            console.log("ERROR:");
            console.log(err);
            $scope.laptopMessage = "Error. Check console.";
        });
    };

    /* Routing */
    $scope.$on('$routeChangeSuccess', function () {
        console.log("view loaded, starting transaction");
        $scope.initiateAddTransaction();
    });

    $scope.$on('$routeChangeStart', function() {
        console.log("exiting view, ending transaction");
        $scope.endTransaction();
    });

    window.onbeforeunload = function () {
        console.log("reloading or closing page");
        $scope.endTransaction();
    };

    $scope.initiateAddTransaction = function () {
        console.log("Calling backend to start add transaction");
        $http.get("/get/startAddTransaction");
    };

    $scope.endTransaction = function () {
        console.log("Calling backend to end transaction");
        $http.get("/get/endTransaction");
    };

});