'use strict';

angular.module('controllers').controller( /*bug 或者需要改进 : 在未选择字典类型的情况下页面加载也会请求数据字典数据*/ 'IndexDictController',
    function($scope, $location, dictService, Paginator) {
        $scope.page = Paginator({
            resource: dictService
        });
        $scope.$on("refreshAndShowDictListFromIndex", function(e, data) {
            $scope.page.setParams({
                typeCode: data.code
            });
            $scope.page.fresh();
        });
        $scope.$on('getDictTypeFromIndex', function(e, data) {
            $scope.dictType = data
        });

        //升序 
        $scope.orderUp = function(key) {
            dictService.up({
                id: key
            }, function() {
                $scope.page.fresh();
            })
        };
        // 降序 
        $scope.orderDown = function(key) {
            dictService.down({
                id: key
            }, function() {
                $scope.page.fresh();
            });
        };

        $scope.addDict = function(typeId, typeCode) {
            if (typeId && typeCode) {
                $location.url("/dictNew?typeId=" + typeId + "&typeCode=" + typeCode);
            } else {
                Fun.notify("alert", {
                    data: {
                        message: "请选择数据字典类型!"
                    }
                });
            }
        };

        $scope.delete = function(id) {
            Fun.deleteMsgBox(function() {
                $location.url("/" + id + "/dictDel");
                $scope.$apply();
            });
        };
    }).controller('NewDictController',
    function($scope, $location, dictService) {
        $scope.save = function() {
            dictService.save($scope.dict, function() {
                Fun.notify("info");
                $location.path("/index");
            });
        }
        $scope.dict = {};
        $scope.dict.typeId = $location.$$search.typeId;
        $scope.dict.typeCode = $location.$$search.typeCode;
    }).controller('EditDictController',
    function($scope, $location, $routeParams, dictService) {
        dictService.get({
            id: $routeParams.id
        }, function(dict) {
            $scope.dict = dict;
        });
        $scope.save = function() {
            dictService.update($scope.dict, function() {
                Fun.notify("info");
                $location.path("/" + $scope.dict.typeCode + "/index");
            });
        }

    }).controller('DelDictController',
    function($scope, $location, $routeParams, dictService) {
        dictService.delete({
            id: $routeParams.id
        }, function() {
            Fun.notify("info");
            $location.path("/index");
        });

    });