'use strict';


angular.module('myApp.userActions', ['ngRoute'])

//configure the routing  and connect the component to the specified controller (MainPageCtrl)
    .config(['$routeProvider', function($routeProvider){
        $routeProvider.when('/account', {
            templateUrl: 'userActions/userActions.html',
            controller: 'UserActionsCtrl'
        })
    }])

    /*
    Controller is the JavaScript function that makes/changes/removes/controls the data.
    The data from the controller is sent to the model (html page) which will be displayed in the view
    */
    .controller('UserActionsCtrl', ["$scope", "$http","$auth343","$location", function($scope, $http, $auth343, $location) {
      //check login
      if(!$auth343.requireLogin()){
         $location.path("/login");
         return;
      }
    }]);
