'use strict';

const registerAdminModule = angular.module('registerAdmin', []);

/*
Controller is the JavaScript function that makes/changes/removes/controls the data.
The data from the controller is sent to the model (html page) which will be displayed in the view
*/
registerAdminModule.controller('RegisterAdminCtrl', ["$scope", "$http", function ($scope, $http) {

    // Instance Variables Declaration
    $scope.dataRegisterResponse = "";

    /*
    Handles the registration form
    */
    $scope.register = function () {
        const url = "/post/register";

        let data = {
            firstName: $scope.firstName,
            lastName: $scope.lastName,
            address: $scope.address,
            phoneNumber: $scope.phoneNumber,
            email: $scope.email,
            password: $scope.password
        };

        // TODO backend POST
        $http.post(url, data).then((res) => {
            $scope.dataRegisterResponse = res.data;
        }).catch((err) => {
            console.log("ERROR:");
            console.log(err);
            $scope.dataRegisterResponse = "Error. Check console.";
        });
    }
}]);
