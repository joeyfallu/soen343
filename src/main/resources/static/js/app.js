'use strict';

/*
Main Angular App
*/
var app = angular.module('myApp', []);

app.controller("AppController", function appController($scope) {
    $scope.style = {
        "background-color" : "white"
    }
});

