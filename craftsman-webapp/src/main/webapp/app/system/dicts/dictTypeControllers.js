'use strict';

angular.module('controllers').controller('IndexDictTypeController',
    function($scope, $location, dictTypeService, Paginator) {
        $scope.page = Paginator({
            resource: dictTypeService
        });
        $scope.reset = function() {
            $scope.name = "";
            $scope.code = "";
            $scope.search();
        };
        $scope.search = function() {
            $scope.page.setParams({
                name: $scope.name,
                code: $scope.code
            });
            $scope.page.fresh();
        };
        $scope.showDictList = function(index, dictType) {
            $scope.$emit("refreshAndShowDictList", dictType);
            $scope.$emit("getDictType", dictType);
        }
    }).controller('NewDictTypeController',
    function($scope, $location, dictTypeService) {
        $scope.save = function() {
            dictTypeService.save($scope.dictType, function() {
                $location.path("/index");
            });
        }
    }).controller('EditDictTypeController',
    function($scope, $location, $routeParams, dictTypeService) {
        dictTypeService.get({
            id: $routeParams.id
        }, function(dictType) {
            $scope.dictType = dictType;
        });
        $scope.save = function() {
            dictTypeService.update($scope.dictType, function() {
                $location.path("/index");
            });
        }

    }).controller('DelDictTypeController',
    function($scope, $location, $routeParams, dictTypeService) {
        dictTypeService.delete({
            id: $routeParams.id
        }, function() {
            $location.path("/index");
        });

    });