'use strict';

angular.module('app').controller('deleteItemsController', ['$scope', "$http", function loginController($scope, $http) {
    $scope.deleteItem = function () {
        $scope.message = "";
        let id = $scope.deleteId;
        let url = "/deleteItem/" + id;

        $http.get(url).then((res) => {
            if (res.data == null)
                $scope.message = "Product with ID: " + $scope.deleteId + " does not exist.";
            else
                $scope.message = "Product with ID: " + $scope.deleteId + " has been deleted.";

         }).catch((err) => {
             console.log("ERROR:");
             console.log(err);
             $scope.message = "Error. Check console.";
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
        $http.get("/get/startDeleteTransaction");
    };

    $scope.endTransaction = function () {
        console.log("Calling backend to end transaction");
        $http.get("/get/endTransaction");
    };

}]);

