var loginModule = angular.module('myApp.login', []);

loginModule.controller('LoginController', ['$scope', '$location', 'Authentication', "$http", "$cookies",
function loginController($scope, $location, Authentication, $http, $cookies) {
   $scope.login = function() {
      Authentication.login($scope.email, $scope.password, function() {
         /*
         Handles the login form
         */
         var url = "/post/login";
         var data = {
            email : $scope.email,
            password : $scope.password
         }
         $http.post(url,data)
         .then((res) => {
            $scope.userInfo = res.data;
            $cookies.putObject('userInfo', $scope.userInfo);
            console.log("Successful login for: ",$scope.userInfo.email);
            $location.path('/');
         });
      });
   };
}]);
