'use strict';

const app = angular.module('addUsers', []);

app.controller('addUsersCtrl', function ($scope, $http) {
    $scope.userMessage = "";




    $scope.addUser = function () {
        var url = "/post/addUser";
        var data = {
            id : "0",
            firstName : $scope.firstName,
            lastName : $scope.lastName,
            address : $scope.address,
            phoneNumber : $scope.phoneNumber,
            email : $scope.email,
            password : $scope.password,
            isAdmin : $scope.adminStatus
        };

        $http.post(url, data).then((res) => {
            $scope.userMessage = "Success!";
    }).catch((err) => {
            console.log("ERROR:");
        console.log(err);
        $scope.userMessage = "Error. Check console.";
    });
    };

   $scope.endTransaction = function(){
        var url = "/post/endTransaction";
        console.log("Hello");
        $http.post(url);
    }


    window.onbeforeunload =  function(e){
          var url = "/post/endTransaction";
          $http.post(url);
          return "Leaving Page";
    };


});