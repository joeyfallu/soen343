angular.module('app').controller('viewUsersCtrl', function ($scope, $http) {

    $scope.users = [];

    $http.get('/getUsers/').then((res) => {
        $scope.users = res.data;

        var items = [];
        for(var key in res.data){
            items.push(res.data[key]);
        }

        $scope.users = items;
    }).catch((err) => {
          console.log("ERROR:");
          console.log(err);
    });
});


