'use strict';

const modifyItemsModule = angular.module('modifyItems', []);

modifyItemsModule.controller('modifyItemsController', ['$scope', "$http", function loginController($scope, $http) {
    $scope.modifyItem = function () {
        $scope.message = "";

        const url = "/post/modifyItems";
        let data = {
            modifiedItemID: $scope.modifiedItemID,
        };

        $http.post(url, data).then((res) => {
            $scope.message = "Success!";
        }).catch((err) => {
            console.log("ERROR:");
            console.log(err);
            $scope.message = "Error. Check console.";
        });
    };
}]);

