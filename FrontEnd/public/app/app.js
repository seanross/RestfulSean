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
        templateUrl : '/partials/main/main',
        controller : 'mvMainCtrl'
    })
});