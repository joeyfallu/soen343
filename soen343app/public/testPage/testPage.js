'use strict';


angular.module('myApp.testPage', ['ngRoute'])

//configure the routing  and connect the component to the specified controller (MainPageCtrl)
    .config(['$routeProvider', function($routeProvider){
        $routeProvider.when('/test', {
            templateUrl: 'testPage/testPage.html',
            controller: 'TestPageCtrl'
        })
    }])

    /*
    Controller is the JavaScript function that makes/changes/removes/controls the data.
    The data from the controller is sent to the model (html page) which will be displayed in the view
    */
    .controller('TestPageCtrl', ["$scope", "$http", function($scope, $http) {

        //Instance Variables Declaration
        $scope.data = "";
        $scope.dataPost = "";
        $scope.dataLoginResponse = "";

        /*
        Adds an entry to the database by
        Making a post request to the server
        */
        $scope.testEntry = function(){
            //Example POST request
            var url = "/post/name";
            var data = {
                name : $scope.name,
            };
            //optional var config = {};
            $http.post(url,data)
                .then((res) => {
                $scope.dataPost = res.data;
        });
        }

        /*
        Gets the list of names from the database
        Is called when the button with ng-click="getEntry()" is clicked in testPage.html
        */
        $scope.getEntry = function(){
            //Example GET request to the backend
            var url = "/get/name";
            $http.get(url)
                .then((res) => {
                $scope.data = res.data

        });
        }


        /*
        Handles the registration form
        */
        $scope.register = function(){
            var url = "/post/register";
            var data = {
                email : $scope.email,
                password : $scope.password
            }
            $http.post(url,data)
                .then((res) => {
                $scope.dataRegisterResponse = res.data;
        });
        }


    }]);