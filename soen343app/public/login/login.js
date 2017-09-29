var loginModule = angular.module('myApp.login', ['ngCookies']);

loginModule.controller('LoginController', ['$scope', '$location', 'Authentication', "$http", "$cookies", "$window", "$auth343",
function loginController($scope, $location, Authentication, $http, $cookies, $window, $auth343) {
   $scope.errorMsg = "";

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
            if(res.data == "Wrong Password" || res.data == "Email not found"){
               $scope.errorMsg = res.data;
               return;
            }
            else {
               $scope.userInfo = res.data;
               $cookies.putObject('userInfo', $scope.userInfo);
               console.log("Successful login for: ",$scope.userInfo.email);
               $auth343.updateValues();
               $location.path('/');
               return;
            }
         });
      });
   };
}]);

loginModule.controller('logoutController', ['$scope', '$location', "$http", "$cookies", "$window", "$auth343",
function logoutController($scope, $location, $http, $cookies, $window, $auth343) {
   var user = $cookies.getObject('userInfo');
   var session = $cookies.get('session');
   if(user != undefined){
      $cookies.remove('userInfo');
      if(session != undefined){
         $cookies.remove('session');
         console.log(user.email, " has been logged out");
         $auth343.updateValues();
         $location.path('/');
         return;
      }
   } else {
      //not logged in
      console.log("not logged in");
      $auth343.updateValues();
      $location.path('/');
      return;
   }
}]);
