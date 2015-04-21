'use strict';

angular.module('controllers', []).controller('IndexController',
    function ($scope, $location, userService, Paginator) {
        $scope.page = Paginator({
            resource: userService
        });
        $scope.reset = function () {
            $scope.name = "";
            $scope.search();
        };
        $scope.search = function () {
            $scope.page.setParams({
                name: $scope.name
            });
            $scope.page.fresh();
        };

        $scope.delete = function(id){
            Fun.deleteMsgBox(function(){
                $location.url("/" + id + "/del");
                $scope.$apply();
            });
        };
    }).controller('NewController',
    function ($scope, $location, userService) {
        $scope.save = function () {
            userService.save($scope.user, function () {
                Fun.notifyInfo();
                $location.path("/index");
            });
        }
    }).controller('EditController',
    function ($scope, $location, $routeParams, userService) {
        userService.get({
            id: $routeParams.id
        }, function (user) {
            $scope.user = user;
            $scope.pwRepeat = user.password;
        });
        $scope.save = function () {
            userService.update($scope.user, function () {
                Fun.notifyInfo();
                $location.path("/index");
            });
        }

    }).controller('DelController',
    function ($scope, $location, $routeParams, userService) {
        userService.delete({
            id: $routeParams.id
        }, function () {
            Fun.notifyInfo();
            $location.path("/index");
        });

    });