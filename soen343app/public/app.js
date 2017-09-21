'use strict';

// Declare app level module which depends on views, and components
angular.module("myApp", [
  "ngRoute",
  'myApp.testPage',
])
.config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.html5Mode({
        enabled: true,
        requireBase: true
     });

  $routeProvider.otherwise({redirectTo: '/'});
}]);
