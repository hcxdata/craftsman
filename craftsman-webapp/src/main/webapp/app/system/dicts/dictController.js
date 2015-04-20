'use strict';

angular.module('controllers').controller('IndexDictController',
    function ($scope, $location, dictService, Paginator) {
        $scope.page = Paginator({
            resource: dictService
        });
        $scope.$on("refreshAndShowDictListFromIndex", function (e, data) {
            $scope.page.setParams({
                typeCode: data.code
            });
            $scope.page.fresh();
        });
        $scope.$on('getDictTypeFromIndex', function (e, data) {
            $scope.dictType = data
        });

        //升序 
        $scope.orderUp = function (key) {
            dictService.up({
                id: key
            }, function () {
                $scope.page.fresh();
            })
        };
        // 降序 
        $scope.orderDown = function (key) {
            dictService.down({
                id: key
            }, function () {
                $scope.page.fresh();
            });
        };

    }).controller('NewDictController',
    function ($scope, $location, dictService) {
        $scope.save = function () {
            dictService.save($scope.dict, function () {
                $location.path("/index");
            });
        }
        $scope.dict = {};
        $scope.dict.typeId = $location.$$search.typeId;
        $scope.dict.typeCode = $location.$$search.typeCode;
    }).controller('EditDictController',
    function ($scope, $location, $routeParams, dictService) {
        dictService.get({
            id: $routeParams.id
        }, function (dict) {
            $scope.dict = dict;
        });
        $scope.save = function () {
            dictService.update($scope.dict, function () {
                $location.path("/index");
            });
        }

    }).controller('DelDictController',
    function ($scope, $location, $routeParams, dictService) {
        dictService.delete({
            id: $routeParams.id
        }, function () {
            $location.path("/index");
        });

    });