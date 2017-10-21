'use strict';

angular.module('app', ['ngRoute'])
    .controller("mainController", function mainController($scope) {

    })

    .config(function ($routeProvider, $locationProvider) {
        $locationProvider.html5Mode(true);

        $routeProvider
            .when('/', {templateUrl: "view/frontPage.html"})
            .when('/test', {templateUrl: "view/testPage.html", controller: "testPageController"})
            .when('/login', {templateUrl: "view/login.html", controller: "loginController"})
            .otherwise({redirectTo: '/'});
    });