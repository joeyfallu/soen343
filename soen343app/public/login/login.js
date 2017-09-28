var loginModule = angular.module('myApp.login', []);

loginModule.controller('LoginController', ['$scope', '$location', 'Authentication',
    function loginController($scope, $location, Authentication) {
        $scope.login = function() {
            Authentication.login($scope.username, $scope.password, function() {
                $location.path('/');
            });
        };
    }]);