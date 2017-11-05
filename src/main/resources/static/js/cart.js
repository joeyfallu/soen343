'use strict';

angular.module('app')
    .controller('cartController', function ($scope, $http, $cookies) {

        $scope.cart = "";
        const urlCart = '/get/products';

         $http.get(urlCart).then((res) => {

            $scope.cart = res.data;
         });

    });