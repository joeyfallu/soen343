const deleteItemsModule = angular.module('deleteItems', []);

deleteItemsModule.controller('deleteItemsController', ['$scope', "$http", function loginController($scope, $http) {
    $scope.deleteItem = function(){
        /*
        Handles the 
        */
        var url = "/post/deleteItem";
        var data = {
           deleteId : $scope.deleteId,
        }
        $http.post(url,data)
        .then((res) => {
           
        });
     };
}]);

