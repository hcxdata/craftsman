'use strict';

angular.module('controllers').controller('IndexRoleController',
    function($scope, $location, roleService, Paginator, $routeParams) {
        $scope.page = Paginator({
            resource: roleService,
            successCallback: function(data) {
                if ($routeParams.id) {
                    var Role = null;
                    for (var i in data.content) {
                        if (data.content[i].id === $routeParams.id) {
                            $scope.showMenuList(null, data.content[i]);
                        }
                    }
                }
            }
        });
        $scope.reset = function() {
            $scope.name = "";
            $scope.search();
            $scope.$emit("refreshAndShowMenuList", {});
            $scope.$emit("getRole", {});
        };
        $scope.search = function() {
            $scope.page.setParams({
                name: $scope.name
            });
            $scope.page.fresh();

            $scope.$emit("refreshAndShowMenuList", {});
            $scope.$emit("getRole", {});
        };
        $scope.showMenuList = function(index, role) {
            $scope.$emit("refreshAndShowMenuList", role);
            $scope.$emit("getRole", role);
        }

        $scope.delete = function(id) {
            Fun.msg.delConfirm(function() {
                $location.url("/" + id + "/del");
                $scope.$apply();
            });
        };

    }).controller('NewRoleController',
    function($scope, $location, roleService) {
        $scope.save = function() {
            roleService.save($scope.role, function() {
                Fun.msg.notifyInfo();
                $location.path("/index");
            });
        }
    }).controller('EditRoleController',
    function($scope, $location, $routeParams, roleService) {
        roleService.get({
            id: $routeParams.id
        }, function(role) {
            $scope.role = role;
        });
        $scope.save = function() {
            roleService.update($scope.role, function() {
                Fun.msg.notifyInfo();
                $location.path("/" + $scope.role.id + "/index");
            });
        }

    }).controller('DelRoleController',
    function($scope, $location, $routeParams, roleService) {
        roleService.delete({
            id: $routeParams.id
        }, function() {
            Fun.msg.notifyInfo();
            $location.path("/index");
        });
    });