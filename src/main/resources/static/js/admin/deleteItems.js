'use strict';

angular.module('app').controller('deleteItemsController', ['$scope', "$http", function loginController($scope, $http) {
    $scope.deleteItem = function () {
        $scope.message = "";

        const url = "/post/deleteItems";
        let data = {
            deleteId: $scope.deleteId,
        };

        $http.post(url, data).then((res) => {
            console.log(res);
            $scope.message = "Success!";
        }).catch((err) => {
            console.log("ERROR:");
            console.log(err);
            $scope.message = "Error. Check console.";
        });
    };
}]);

