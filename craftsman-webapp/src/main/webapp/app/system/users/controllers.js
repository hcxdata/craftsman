'use strict';

angular.module('controllers', []).controller('IndexController',
    function ($scope, $location, userService, Paginator) {
        $scope.page = Paginator({resource: userService});
        $scope.reset = function(){$scope.name="";$scope.search();}
        $scope.search = function(){
            $scope.page.setParams({name:$scope.name});
            $scope.page.fresh();
        }
    }).controller('NewController',
    function ($scope, $location, userService, $q) {
        $scope.save = function () {
            userService.save($scope.user, function () {
                $location.path("/index");
            });
        }
    }).controller('EditController',
    function ($scope, $location, $routeParams, userService) {
        userService.get({
            id: $routeParams.id
        }, function (user) {
            $scope.user = user;
            $('#confimPassWord').val(user.password);
        });
        $scope.save = function () {
            userService.update($scope.user, function () {
                $location.path("/index");
            });
        }

    }).controller('DelController',
    function ($scope, $location, $routeParams, userService) {
        userService.delete({
            id: $routeParams.id
        }, function () {
            alert('success');
            $location.path("/index");
        });

    });