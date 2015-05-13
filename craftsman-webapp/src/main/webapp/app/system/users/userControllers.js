'use strict';

angular.module('controllers').controller('IndexUserController',
    function($scope, $location, userService, Paginator) {
        $scope.page = Paginator({
            resource: userService
        });
        $scope.reset = function() {
            $scope.name = "";
            $scope.search();
            $scope.$emit("refreshAndShowRoleList", {});
            $scope.$emit("getUser", {});
        };
        $scope.search = function() {
            $scope.page.setParams({
                name: $scope.name
            });
            $scope.page.fresh();
            $scope.$emit("refreshAndShowRoleList", {});
            $scope.$emit("getUser", {});
        };

        $scope.delete = function(id) {
            Fun.msg.delConfirm(function() {
                $location.url("/" + id + "/del");
                $scope.$apply();
            });
        };
        $scope.showRoleList = function(index, user) {
            $scope.$emit("refreshAndShowRoleList", user);
            $scope.$emit("getUser", user);
        };
    }).controller('NewController',
    function($scope, $location, userService) {
        $scope.save = function() {
            userService.save($scope.user, function() {
                Fun.msg.notifyInfo();
                $location.path("/index");
            });
        }
    }).controller('EditController',
    function($scope, $location, $routeParams, userService) {
        userService.get({
            id: $routeParams.id
        }, function(user) {
            $scope.user = user;
            $scope.pwRepeat = user.password;
        });
        $scope.save = function() {
            userService.update($scope.user, function() {
                Fun.msg.notifyInfo();
                $location.path("/index");
            });
        }

    }).controller('DelController',
    function($scope, $location, $routeParams, userService) {
        userService.delete({
            id: $routeParams.id
        }, function() {
            Fun.msg.notifyInfo();
            $location.path("/index");
        });

    });