/**
 * Created by Sean Ross on 1/8/2015.
 */


angular.module('application', ['ngResource', 'ngRoute']);


angular.module('application').config(function($routeProvider, $locationProvider){
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
    $routeProvider.when('/', {
        templateUrl : '/application/home/home',
        controller : 'homeController'
    })
});