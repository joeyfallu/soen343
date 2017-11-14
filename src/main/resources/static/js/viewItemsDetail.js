'use strict';

angular.module('app').controller('viewItemsDetailCtrl', function ($scope, $http, $location) {

    var itemId = $location.path().split("/")[2];

    const urlItem = '/getItem/' + itemId;

    $http.get(urlItem).then((res) => {
        $scope.item = res.data;
        console.log($scope.item);
    });

});