'use strict';

angular.module('app').controller('addUsersCtrl', function ($scope, $http) {

    /* Form submission */
    $scope.userMessage = "";

    $scope.addUser = function () {
        let data = {
            id: "0",
            firstName: $scope.firstName,
            lastName: $scope.lastName,
            address: $scope.address,
            phoneNumber: $scope.phoneNumber,
            email: $scope.email,
            password: $scope.password,
            isAdmin: $scope.adminStatus
        };

        $http.post("/post/addUser", data).then((res) => {
            $scope.userMessage = "Success!";
        }).catch((err) => {
            console.log("ERROR:");
            console.log(err);
            $scope.userMessage = "Error. Check console.";
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