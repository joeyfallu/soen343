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

