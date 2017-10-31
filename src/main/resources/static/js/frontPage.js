'use strict';

angular.module('app')
    .controller('frontPageController', function ($rootScope, $scope) {

        /* Application-wide global variable */
        $rootScope.aGlobalVariable = false;

        /* Example of reference to a global variable in an html
        <div ng-show="authenticated">
                Logged in as ...
        </div>

        <div ng-show="!authenticated">
            Not logged in
        </div>
        */

        /* Example of using 'this'
           this.aVar = false;
           this.aFunction = function() {};

           In html:
            <div ng-show="frontPageController.aVar"></div>
            <form ng-submit="frontPageController.function()"></form>
            We have to do frontPageController.something because of frontPageController as frontPageController
            which gives frontPageController a namespace called frontPageController
         */

        /* To reference 'this' in callbacks */
        // let self = this;

        // Old code for reference
        // // Function to authenticate a user's credentials
        // let authenticate = function(credentials, callback) {
        //     let requestHeaders = credentials ? {authorization : "Basic " + btoa(credentials.email + ":" +
        //         credentials.password)} : {};
        //
        //     $http.get('user', {headers : requestHeaders}).then(function(response) {
        //
        //         console.log("Receiving response in front end...");
        //         console.log(response);
        //
        //         if (response.data.name) { // if (response.data.name === credentials.email)?
        //             console.log("Login success");
        //             $rootScope.authenticated = true;
        //         } else {
        //             console.log("Login failed");
        //             $rootScope.authenticated = false;
        //         }
        //
        //         callback && callback($rootScope.authenticated);
        //     }, function() {
        //         $rootScope.authenticated = false;
        //         callback && callback(false);
        //     });
        // };
        //
        // // Check if a user is already authenticated
        // authenticate();
        //
        // self.credentials = {};
        //
        // // Function to handle login form submit
        // self.login = function() {
        //     authenticate(self.credentials, function(authenticated) {
        //         if (authenticated) {
        //             $location.path("/");
        //             self.error = false;
        //             $rootScope.authenticated = true;
        //         } else {
        //             $location.path("/login");
        //             self.error = true;
        //             $rootScope.authenticated = false;
        //         }
        //     });
        // };
        //
        // // Function to handle logout button click
        // self.logout = function() {
        //     $http.post('logout', {}).finally(function() {
        //         $rootScope.authenticated = false;
        //         $location.path("/");
        //     });
        // }
    });