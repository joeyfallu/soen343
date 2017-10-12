const deleteItemsModule = angular.module('deleteItems', []);

deleteItemsModule.controller('deleteItemsController', ['$scope', "$http", function loginController($scope, $http) {
    $scope.deleteItem = function () {
        const url = "/post/deleteItem";
        let data = {
            deleteId: $scope.deleteId,
        };

        $http.post(url, data).then((res) => {

        });
    };
}]);

