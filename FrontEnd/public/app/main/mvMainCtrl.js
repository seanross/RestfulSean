/**
 * Created by Sean Ross on 15/7/2015.
 */

angular.module('app').controller('mvMainCtrl', function($scope){
    $scope.courses = [
        {name : "Mathematics", featured : true, published : new Date('2015-07-04') },
        {name : "English", featured : false,  published : new Date('2015-01-15') }
    ];
});