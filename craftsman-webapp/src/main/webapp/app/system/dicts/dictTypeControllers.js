'use strict';

angular.module('controllers').controller('IndexDictTypeController',
    function ($scope, $location, dictTypeService, Paginator, $routeParams) {
        $scope.page = Paginator({
            resource: dictTypeService,
            successCallback: function (data) {
                if ($routeParams.code) {
                    var dictType = null;
                    for (var i in data.content) {
                        if (data.content[i].code === $routeParams.code) {
                            $scope.showDictList(null, data.content[i]);
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
        $scope.showDictList = function (index, dictType) {
            $scope.$emit("refreshAndShowDictList", dictType);
            $scope.$emit("getDictType", dictType);
        }

        $scope.delete = function (id) {
            Fun.msg.delConfirm(function () {
                $location.url("/" + id + "/del");
                $scope.$apply();
            });
        };

    }).controller('NewDictTypeController',
    function ($scope, $location, dictTypeService) {
        $scope.save = function () {
            dictTypeService.save($scope.dictType, function () {
                Fun.msg.notifyInfo();
                $location.path("/index");
            });
        }
    }).controller('EditDictTypeController',
    function ($scope, $location, $routeParams, dictTypeService) {
        dictTypeService.get({
            id: $routeParams.id
        }, function (dictType) {
            $scope.dictType = dictType;
        });
        $scope.save = function () {
            dictTypeService.update($scope.dictType, function () {
                Fun.msg.notifyInfo();
                $location.path("/" + $scope.dictType.code + "/index");
            });
        }

    }).controller('DelDictTypeController',
    function ($scope, $location, $routeParams, dictTypeService) {
        dictTypeService.delete({
            id: $routeParams.id
        }, function () {
            Fun.msg.notifyInfo();
            $location.path("/index");
        });
    });