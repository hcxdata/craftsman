'use strict';

angular.module('controllers').controller('IndexParamTypeController',
    function ($scope, $location, paramTypeService, Paginator, $routeParams) {
        $scope.page = Paginator({
            resource: paramTypeService,
            successCallback: function (data) {
                if ($routeParams.code) {
                    var paramType = null;
                    for (var i in data.content) {
                        if (data.content[i].code === $routeParams.code) {
                            $scope.showParamList(null, data.content[i]);
                        }
                    }
                }
            }
        });
        $scope.reset = function () {
            $scope.name = "";
            $scope.code = "";
            $scope.search();
        };
        $scope.search = function () {
            $scope.page.setParams({
                name: $scope.name,
                code: $scope.code
            });
            $scope.page.fresh();
        };
        $scope.showParamList = function (index, paramType) {
            $scope.$emit("refreshAndShowParamList", paramType);
            $scope.$emit("getParamType", paramType);
        }

        $scope.delete = function (id) {
            Fun.msg.delConfirm(function () {
                $location.url("/" + id + "/del");
                $scope.$apply();
            });
        };

    }).controller('NewParamTypeController',
    function ($scope, $location, paramTypeService) {
        $scope.save = function () {
            paramTypeService.save($scope.paramType, function () {
                Fun.msg.notifyInfo();
                $location.path("/index");
            });
        }
    }).controller('EditParamTypeController',
    function ($scope, $location, $routeParams, paramTypeService) {
        paramTypeService.get({
            id: $routeParams.id
        }, function (paramType) {
            $scope.paramType = paramType;
        });
        $scope.save = function () {
            paramTypeService.update($scope.paramType, function () {
                Fun.msg.notifyInfo();
                $location.path("/" + $scope.paramType.code + "/index");
            });
        }

    }).controller('DelParamTypeController',
    function ($scope, $location, $routeParams, paramTypeService) {
        paramTypeService.delete({
            id: $routeParams.id
        }, function () {
            Fun.msg.notifyInfo();
            $location.path("/index");
        });
    });