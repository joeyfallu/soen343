'use strict';

const appModule = angular.module('app', ['ngRoute']);

// indexModule.controller("indexController", function indexController($scope) {
//
// });

appModule.config(function($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true);

    $routeProvider
        .when('/', { templateUrl: "view/frontPage.html"})
        .when('/test', { templateUrl: "view/testPage.html" })
        .otherwise({ redirectTo: '/'});
});