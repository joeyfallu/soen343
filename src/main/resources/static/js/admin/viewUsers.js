angular.module('app').controller('viewUsersCtrl', function ($scope, $http) {

    $scope.test = "HELLO"

    $http.get('/getUsers/').then((res) => {
        $scope.users = res.data;
        console.log($scope.users);
    }).catch((err) => {
          console.log("ERROR:");
          console.log(err);
    });
});


