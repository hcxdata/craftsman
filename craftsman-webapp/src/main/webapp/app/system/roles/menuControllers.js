'use strict';

angular.module('controllers').controller('IndexMenuController',
    function ($scope, $location, menuService) {
        $scope.$on("refreshAndShowMenuListFromIndex", function (e, data) {
            $scope.refresh(data.id);
        });
        $scope.$on('getRoleFromIndex', function (e, data) {
            $scope.role = data;
        });
        /*刷新*/
        $scope.refresh = function (roleid) {
            if (roleid !== undefined)
                $scope.tree_data = menuService.query({
                    roleid: roleid
                });
            else
                $scope.tree_data = [];

            expanded_all = false;
        };
        /*列定义*/
        $scope.col_defs = [];
        // 树控制器
        $scope.my_tree = {};
        // 初始化还未展开
        var expanded_all = false;
        // 当treeData数据发生变化，就会调用次函数
        $scope.expand_branch_all = function () {
            if (expanded_all === false) {
                // 展开所有节点
                $scope.my_tree.expand_all();
                // 已展开完成
                expanded_all = true;
            }
        };

        $scope.refresh();

        $scope.save = function () {
            var data = {
                roleid: $scope.role.id,
                menus: (function () {
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
            menuService.update(data, function () {
                Fun.msg.notifyInfo();
            });
        };
    });