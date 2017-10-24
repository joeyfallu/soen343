'use strict';

const app = angular.module('viewItemsDetail', [], function($locationProvider){
    $locationProvider.html5Mode(true);
});

app.controller('viewItemsDetailCtrl', function ($scope, $http, $location) {

    var itemId = $location.path().split("/")[2];

    const urlItem = '/getItem/' + itemId;

    $http.get(urlItem).then((res) => {
        $scope.item = res.data;
        console.log($scope.item);
    });

});

app.filter('capitalize', function() {
    return function(input) {
      return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
    }
});