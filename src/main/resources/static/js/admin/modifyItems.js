'use strict';

angular.module('app').controller('modifyItemsController', function ($scope, $http) {

    $http.get("/get/TransactionInProgress").then((res) => {
        if(res.data.message == "In Progress"){
            alert("Transaction Already in Progress\n You will be redirected");
            window.location = "/admin";
        }
    });


    /* Form submission */
    $scope.getProdInfo = function () {
        $scope.message = "";
        let modelNumber = $scope.modelNumber;
        let urlProduct = '/getItemModify/' + modelNumber;
        $scope.itemData = [];

        $http.get(urlProduct)
            .then((res) => {
                console.log(res.data);
                if (res.data.message == 'Item Does Not Exist') {
                    $scope.message = "Product with Model Number: " + $scope.modelNumber + " does not exist.";
                }
                $scope.itemData = res.data;
                console.log($scope.itemData);

            });
    };
    $scope.modifyMonitor = function () {
        let data = {
            serialNumber: $scope.itemData.serialNumber,
            discriminator: "2",
            model: $scope.itemData.model,
            weight: $scope.itemData.weight,
            price: $scope.itemData.price,
            brand: $scope.itemData.brand,
            size: $scope.itemData.size,
        };

        $http.post("/post/modifyMonitor", data).then((res) => {
            console.log(res);
            $scope.message = "Successful product modification!";
        });
    };
    $scope.modifyTablet = function () {
        let data = {
            serialNumber: $scope.itemData.serialNumber,
            discriminator: "3",
            model: $scope.itemData.model,
            weight: $scope.itemData.weight,
            price: $scope.itemData.price,
            brand: $scope.itemData.brand,
            dimensions: $scope.itemData.dimensions,
            processorType: $scope.itemData.processorType,
            cpuCores: $scope.itemData.cpuCores,
            ram: $scope.itemData.ram,
            hardDriveSize: $scope.itemData.hardDriveSize,
            size: $scope.itemData.size,
            batteryInfo: $scope.itemData.batteryInfo,
            operatingSystem: $scope.itemData.operatingSystem,
            cameraInfo: $scope.itemData.cameraInfo,
        };
        $http.post("/post/modifyTablet", data).then((res) => {
            console.log(res);
            $scope.message = "Successful product modification!";
        });
    };
    $scope.modifyDesktop = function () {
        let data = {
            serialNumber: $scope.itemData.serialNumber,
            discriminator: "4",
            model: $scope.itemData.model,
            weight: $scope.itemData.weight,
            price: $scope.itemData.price,
            brand: $scope.itemData.brand,
            dimensions: $scope.itemData.dimensions,
            processorType: $scope.itemData.processorType,
            cpuCores: $scope.itemData.cpuCores,
            ram: $scope.itemData.ram,
            hardDriveSize: $scope.itemData.hardDriveSize,
        };
        $http.post("/post/modifyDesktop", data).then((res) => {
            console.log(res);
            $scope.message = "Successful product modification!";
        });
    };
    $scope.modifyLaptop = function () {
        let data = {
            serialNumber: $scope.itemData.serialNumber,
            discriminator: "5",
            model: $scope.itemData.model,
            weight: $scope.itemData.weight,
            price: $scope.itemData.price,
            brand: $scope.itemData.brand,
            processorType: $scope.itemData.processorType,
            cpuCores: $scope.itemData.cpuCores,
            ram: $scope.itemData.ram,
            hardDriveSize: $scope.itemData.hardDriveSize,
            size: $scope.itemData.size,
            batteryInfo: $scope.itemData.batteryInfo,
            operatingSystem: $scope.itemData.operatingSystem,
            camera: $scope.itemData.camera,
            touchScreen: $scope.itemData.touchScreen,
        };
        $http.post("/post/modifyLaptop", data).then((res) => {
            console.log(res);
            $scope.message = "Successful product modification!";
        });
    };

    /* Routing */
    $scope.$on('$routeChangeSuccess', function () {
        console.log("view loaded, starting transaction");
        $scope.initiateModifyTransaction();
    });

    $scope.$on('$routeChangeStart', function() {
        console.log("exiting view, ending transaction");
        $scope.endTransaction();
    });

    window.onbeforeunload = function () {
        $scope.endTransaction();
    };

    $scope.initiateModifyTransaction = function () {
        console.log("Calling backend to start modify transaction");
        $http.get("/get/startModifyTransaction");
    };

    $scope.endTransaction = function () {
        console.log("Calling backend to end transaction");
        $http.get("/get/endTransaction");
    };

});
