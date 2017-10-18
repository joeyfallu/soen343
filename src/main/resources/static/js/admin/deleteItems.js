'use strict';

const deleteItemsModule = angular.module('deleteItems', []);

deleteItemsModule.controller('deleteItemsController', ['$scope', "$http", function loginController($scope, $http) {
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

       $scope.endTransaction = function(){
            var url = "/post/endTransaction";
            $http.post(url);
        }

       window.onbeforeunload =  function(e){
            var url = "/post/endTransaction";
            $http.post(url);
            return "Leaving Page";
       };
}]);

