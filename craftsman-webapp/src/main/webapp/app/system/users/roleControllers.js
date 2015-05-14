'use strict';

angular.module('controllers').controller('IndexRoleController',
    function ($scope, $location, roleService) {
        $scope.$on("refreshAndShowRoleListFromIndex", function (e, data) {
            $scope.refresh(data.id);
        });
        $scope.$on('getUserFromIndex', function (e, data) {
            $scope.user = data;
        });
        $scope.refresh = function (userid) {
            if (userid !== undefined)
                $scope.tree_data = roleService.query({
                    userid: userid
                });
            else
                $scope.tree_data = [];
        };
        /*列定义*/
        $scope.col_defs = [];
        // 树控制器
        $scope.my_tree = {};

        $scope.refresh();
        $scope.save = function () {
            var data = {
                userid: $scope.user.id,
                roles: (function () {
                    function d(data) {
                        if (data.checked === true) {
                            ids.push(data.id);
                            if (data.children && data.children.length > 0) {
                                for (var i = 0; i < data.children.length; i++) {
                                    d(data.children[i]);
                                }
                            }
                        }
                    };
                    var ids = [];
                    var treeData = $scope.my_tree.get_treeData();
                    if (treeData.length > 0) {
                        for (var i = 0; i < treeData.length; i++) {
                            d(treeData[i]);
                        }
                    }
                    return ids;
                }())
            };
            roleService.update(data, function () {
                Fun.msg.notifyInfo();
            });
        };
    });