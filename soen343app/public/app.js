'use strict';

var app = angular.module('myApp', [
    'ngRoute',
    'myApp.testPage'
]);

app.config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: true
    });

    $routeProvider
        .when('/login', { templateUrl: 'login/login.html' })
        .when('/test', { templateUrl: 'testPage/testPage.html'});

    $routeProvider.otherwise({redirectTo: '/'});
}]);

app.controller("appController", function appController($scope) {
    $scope.style = {
        "background-color" : "white"
    }
});
