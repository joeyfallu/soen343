'use strict';

angular.module('app').controller('registerController', function ($scope, $http, $location) {

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

        console.log("Calling backend to add user");
        $http.post("/post/addUser", data).then((res) => {
            if (res.data.message) {
                $scope.userMessage = "ERROR: Duplicate Email!";
            } else {
                $scope.userMessage = "Success";
                console.log("Calling backend to perform registration transaction");
                $http.get("/get/registrationTransaction").then(function (err) {
                    if (err.data.message)
                        $scope.userMessage = "ERROR: Registration failed.";
                    else
                        $location.path("/");
                });
            }
        });

    };

});