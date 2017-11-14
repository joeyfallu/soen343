'use strict';

angular.module('app').controller('addItemsCtrl', function ($scope, $http) {

    /* Form submission */
    $scope.monitorMessage = "";
    $scope.tabletMessage = "";
    $scope.desktopMessage = "";
    $scope.laptopMessage = "";
    $scope.isMonitorMsgAvailable = false;
    $scope.isTabletMsgAvailable = false;
    $scope.isDesktopMsgAvailable = false;
    $scope.isLaptopMessageAvailable = false;

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
            $scope.isMonitorMsgAvailable = true;
            $scope.monitorMessage = "Successfully added monitor with model: " + res.data.model;
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
            $scope.isTabletMsgAvailable = true;
            $scope.tabletMessage = "Successfully added monitor with model: " + res.data.model;
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
            $scope.isDesktopMsgAvailable = true;
            $scope.desktopMessage = "Successfully added monitor with model: " + res.data.model;
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
            $scope.isLaptopMessageAvailable = true;
            $scope.laptopMessage = "Successfully added monitor with model: " + res.data.model;
        }).catch((err) => {
            console.log("ERROR:");
            console.log(err);
            $scope.laptopMessage = "Error. Check console.";
        });
    };

    /* Transaction */
    $scope.$on('$routeChangeSuccess', function () {
        $http.get("/get/startAddTransaction");
    });

    $scope.$on('$routeChangeStart', function() {
        $scope.endTransaction();
    });

    window.onbeforeunload = function () {
        $scope.endTransaction();
    };

    $scope.endTransaction = function () {
        $http.get("/get/endTransaction");
    };

});