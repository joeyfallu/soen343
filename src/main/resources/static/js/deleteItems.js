'use strict';




var loginModule = angular.module('myApp.deleteItems', [])

loginModule.controller('deleteItemsController', ['$scope', "$http", function deleteItemsController($scope, $http) {
    $scope.deleteItems = function(){
     // console.log("hi");
        var url = "/post/deleteItems";
        var data = {
           deleteId : $scope.deleteId
        }
        $http.post(url,data)
        .then((res) => {

            //console.log(' data ');

        })
        .catch(console.error);
     };
}]);

