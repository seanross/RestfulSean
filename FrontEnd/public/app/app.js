/**
 * Created by Sean Ross on 12/7/2015.
 */


angular.module('app', ['ngResource', 'ngRoute']);


angular.module('app').config(function($routeProvider, $locationProvider){
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
    $routeProvider.when('/', {
        templateUrl : '/partials/main',
        controller : 'mainCtrl'
    })
});

angular.module('app').controller('mainCtrl', function($scope){
    $scope.myVar = "Sean Ross Pogi";
});