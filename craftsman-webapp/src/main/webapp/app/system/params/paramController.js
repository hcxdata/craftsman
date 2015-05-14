'use strict';

angular.module('controllers').controller('IndexParamController',
    function($scope, $location, paramService, Paginator) {
        $scope.page = Paginator({
            resource: paramService
        });
        // 显示参数列表
        $scope.$on("refreshAndShowParamListFromIndex", function(e, data) {
            $scope.page.setParams({
                typeCode: data.code
            });
            $scope.page.fresh();
        });
        // 获取参数类型
        $scope.$on('getParamTypeFromIndex', function(e, data) {
            $scope.paramType = data
        });

        // 添加参数
        $scope.addParam = function(typeId, typeCode) {
            if (typeId && typeCode) {
                $location.url("/paramNew?typeId=" + typeId + "&typeCode=" + typeCode);
            } else {
                Fun.msg.notifyAlert(
                    "请选择系统参数类型!"
                );
            }
        };

        // 删除参数
        $scope.delete = function(id, typeCode) {
            Fun.msg.delConfirm(function() {
                $location.url("/" + typeCode + "/" + id + "/paramDel");
                $scope.$apply();
            });
        };
    }).controller('NewParamController',
    function($scope, $location, paramService) {
        $scope.save = function() {
            // 保存参数信息
            paramService.save($scope.param, function() {
                Fun.msg.notifyInfo();
                $location.path("/" + $scope.param.typeCode + "/index");
            });
        }
        $scope.param = {};
        // 根据url参数获取获取参数类型必要信息
        $scope.param.typeId = $location.$$search.typeId;
        $scope.param.typeCode = $location.$$search.typeCode;
    }).controller('EditParamController',
    function($scope, $location, $routeParams, paramService) {
        // 获取参数信息
        paramService.get({
            id: $routeParams.id
        }, function(param) {
            $scope.param = param;
        });
        // 保存修改后的参数信息
        $scope.save = function() {
            paramService.update($scope.param, function() {
                Fun.msg.notifyInfo();
                $location.path("/" + $scope.param.typeCode + "/index");
            });
        }

    }).controller('DelParamController',
    function($scope, $location, $routeParams, paramService) {
        // 删除参数信息
        paramService.delete({
            id: $routeParams.id
        }, function() {
            Fun.msg.notifyInfo();
            $location.path("/" + $routeParams.typeCode + "/index");
        });

    });