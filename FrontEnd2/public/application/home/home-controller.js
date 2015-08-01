/**
 * Created by Sean Ross on 1/8/2015.
 */

angular.module('application').controller('homeController', function($scope){
    $scope.courses = [
        {name : "Mathematics", featured : true, published : new Date('2015-07-04') },
        {name : "English", featured : false,  published : new Date('2015-01-15') }
    ];
    $scope.messageLang = "Sean Pogi So Much";
});