'use strict';

/*
Main Angular App
*/
var app = angular.module('myApp', [
    'ngRoute',
    'ngCookies',
    'myApp.testPage',
    'myApp.login',
    'myApp.addItems',
    'myApp.userActions',
    'myApp.registerAdmin',
    'myApp.viewItems',
]);

app.config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: true
    });

    $routeProvider
        .when('/login', { templateUrl: 'login.html', controller: 'LoginController' })
        .when('/test', { templateUrl: 'testPage.html', controller: 'TestPageCtrl'})
        .when('/logout', { templateUrl: 'logout.html', controller: 'logoutController'})
        .when('/home', { templateUrl: 'home.html', controller: 'homeController'});
    $routeProvider.otherwise({ redirectTo: '/' });
}]);

app.controller("AppController", function appController($scope) {
    $scope.style = {
        "background-color" : "white"
    }
});

