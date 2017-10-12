 var loginModule = angular.module('myApp.deleteItems', []);

loginModule.controller('LoginController', ['$scope', '$location', "$http",function loginController($scope, $location, $http) {
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

