'use-script';

var app = angular.module('addItems', []);
app.controller('addItemsCtrl', function($scope, $http){

    var urlProduct = '/get/items';


        $http.get(urlProduct)
        .then((res) => {

        });
});