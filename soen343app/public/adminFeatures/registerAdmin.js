'use strict';


angular.module('myApp.registerAdmin', ['ngRoute'])

//configure the routing  and connect the component to the specified controller (MainPageCtrl)
    .config(['$routeProvider', function($routeProvider){
        $routeProvider.when('/registerAdmin', {
            templateUrl: 'adminFeatures/registerAdmin.html',
            controller: 'RegisterAdminCtrl'
        })
    }])

    /*
    Controller is the JavaScript function that makes/changes/removes/controls the data.
    The data from the controller is sent to the model (html page) which will be displayed in the view
    */
    .controller('RegisterAdminCtrl', ["$scope", "$http", "$auth343", function($scope, $http, $auth343) {
        $auth343.requireLogin();
        //Instance Variables Declaration
        $scope.dataLoginResponse = "";
        /*
        Handles the registration form
        */
        $scope.register = function(){
            var url = "/post/register";
            var data = {
                firstName : $scope.firstName,
                lastName : $scope.lastName,
                address : $scope.address,
                phoneNumber : $scope.phoneNumber,
                email : $scope.email,
                password : $scope.password
            }
            $http.post(url,data)
                .then((res) => {
                $scope.dataRegisterResponse = res.data;
             });
          }
    }]);
