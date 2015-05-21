'use strict';

angular.module('controllers', []).controller('IndexController',
    function ($scope, $location, menuService, menuFetchService) {
        /*控制树*/
        $scope.my_tree = {};
        /*展开链*/
        $scope.chain = "";
        /*初始化展开链*/
        $scope.initChain = $location.$$search.initChain && typeof $location.$$search.initChain === "string" ? decodeURI($location.$$search.initChain) : null;
        /*刷新*/
        $scope.refresh = function (callback) {
            $scope.tree_data = menuFetchService.query({
                id: 0
            });
            $scope.currentSelectBranch = {
                id: 0,
                text: ""
            };
        };
        $scope.refresh();
        /*列定义*/
        $scope.col_defs = [
            {
                field: "hrefTarget",
                displayName: "链接地址"
            }, {
                field: "leaf",
                displayName: "是否叶子节点",
                cellTemplate: '<text ng-if="row.branch[col.field] === true">是</text><text ng-if="row.branch[col.field] === false">否</text>',
                cellTemplateInfo: '<text ng-if="info.content[col.field] === true">是</text><text ng-if="info.content[col.field] === false">否</text>'
            }];
        /*节点展开回调，用于能获取远端数据*/
        $scope.my_tree_expand_handler = function (branch) {
            if (!branch.loaded)
                menuFetchService.query({
                    id: branch.id
                }, function (dataArr) {
                    for (var i = 0; i < dataArr.length; i++) {
                        var data = dataArr[i];
                        $scope.my_tree.add_branch(branch, data);
                    }
                    // 手动设置节点展开且数据已经从远端获取完毕的状态
                    $scope.my_tree.set_branch_loaded(branch, function () {
                        // 如果初始的展开链还存在且还没有完全展开
                        if ($scope.initChain && typeof $scope.initChain === "string" && $scope.initChain.split("/").length >= branch.level + 1) {
                            expand_branch_level(branch.level + 1, branch);
                        } else {
                            $scope.initChain = null;
                        }
                    });
                    $scope.currentSelectBranch = branch;
                    $scope.chain = getBranchChain(branch, branch.id);
                });
        };
        /*选择节点回调*/
        $scope.my_tree_select_handler = function (branch) {
            $scope.currentSelectBranch = branch;
            $scope.chain = getBranchChain(branch, branch.id);
        };

        // 获取结构树父子关系链

        function getBranchChain(branch, chain) {
            if (branch) {
                var parent = $scope.my_tree.get_parent_branch(branch);
                if (parent) {
                    chain = parent.id + "/" + chain;
                    if ($scope.my_tree.get_parent_branch(parent))
                        chain = getBranchChain(parent, chain);
                }

            }
            return chain;
        };


        /*
            由于scope一直在检测treeData的变化，所以每次treeData数据变化的时候，都会触发此函数。
            原本此处只是为了在数据加载完成时候对tree进行操作，由于treeData是一个promised对象，无法直接判断数据是否加载完毕，因此修改了插件添加了onTreeDataPromised回调函数的引用。
            只有真正加载完毕后才可以对树进行展开操作，而且仅需要操作一次，即展开树分支的根节点.
        */
        var firstLoaded = false;
        // treeData首次加载完毕调用
        $scope.expand_branch_chain = function () {
            if(firstLoaded === false)
            {
                expand_branch_level(1);
                firstLoaded = true;
            }
        };

        // 根据节点条件和级别展开节点

        function expand_branch_level(level, parent) {
            if ($scope.initChain && typeof $scope.initChain === "string") {
                var arr = $scope.initChain.split('/');
                for (var i = 0; i < arr.length; i++) {
                    /*i等于0时为树的根节点*/
                    if (i + 1 == level) {
                        var branch;
                        if (level == 1)
                            branch = $scope.my_tree.find_tree_by_condition({
                                id: arr[i]
                            });
                        else {
                            branch = $scope.my_tree.find_branch_by_condition(parent, {
                                id: arr[i]
                            });
                        }
                        $scope.my_tree.expand_branch_remote(branch);
                        break;
                    }
                }
            }
        };
        /*拖拽移动节点成功回调*/
        $scope.move_update = function (branch, targetBranch) {
            if (branch.uid != targetBranch.uid) {
                menuService.change({
                    id: branch.id,
                    parentId: targetBranch.id
                }, function (result) {

                });
            }
        };
        /*上移*/
        $scope.orderUp = function () {
            if ($scope.currentSelectBranch) {
                $scope.my_tree.order_up($scope.currentSelectBranch);
            }
        };
        /*下移*/
        $scope.orderDown = function () {
            if ($scope.currentSelectBranch) {
                $scope.my_tree.order_down($scope.currentSelectBranch);
            }
        };
        /*上移回调*/
        $scope.branch_order_up = function (branch, ebranch) {
            if (ebranch) {
                menuService.orderUp({
                    id: branch.id
                }, function () {

                });
            }
        };
        /*下移回调*/
        $scope.branch_order_down = function (branch, ebranch) {
            if (ebranch) {
                menuService.orderDown({
                    id: branch.id
                }, function () {

                });
            }
        };
        $scope.add = function () {
            $location.url("/new?parentId=" + $scope.currentSelectBranch.id + "&parentText=" + $scope.currentSelectBranch.text + "&initChain=" + escape($scope.chain));
        };
        $scope.edit = function () {
            if ($scope.currentSelectBranch) {
                var parent = $scope.my_tree.get_parent_branch($scope.currentSelectBranch);
                if (!parent)
                    parent = {
                        id: 0,
                        text: ""
                    };
                $location.url("/" + $scope.currentSelectBranch["id"] + "/edit?parentId=" + parent.id + "&parentText=" + parent.text + "&initChain=" + escape($scope.chain));
            } else {
            }
        };
        $scope.delete = function () {
            Fun.msg.delConfirm(function () {
                if ($scope.currentSelectBranch) {
                    $location.path("/" + $scope.currentSelectBranch["id"] + "/del");
                    $scope.$apply();
                }
                else {
                }
            })
        };
    }).controller('NewController',
    function ($scope, $location, menuService) {
        $scope.menu = {};
        $scope.menu.parentId = $location.$$search.parentId;
        $scope.menu.parentText = $location.$$search.parentText ? $location.$$search.parentText : "根节点";
        $scope.initChain = $location.$$search.initChain;
        $scope.save = function () {
            menuService.save($scope.menu, function () {
                Fun.msg.notifyInfo();
                $location.url("/index?initChain=" + $scope.initChain);
            });
        }
        $scope.reback = function () {
            $location.url("/index?initChain=" + $scope.initChain);
        };
    }).controller('EditController',
    function ($scope, $location, $routeParams, menuService) {
        $scope.initChain = $location.$$search.initChain;
        menuService.get({
            id: $routeParams.id
        }, function (menu) {
            $scope.menu = menu;
            $scope.menu.parentId = $location.$$search.parentId;
            $scope.menu.parentText = $location.$$search.parentText ? $location.$$search.parentText : "根节点";
        });
        $scope.save = function () {
            menuService.update($scope.menu, function () {
                Fun.msg.notifyInfo();
                $location.url("/index?initChain=" + $scope.initChain);
            });
        };
        $scope.reback = function () {
            $location.url("/index?initChain=" + $scope.initChain);
        };

    }).controller('DelController',
    function ($scope, $location, $routeParams, menuService) {
        menuService.delete({
            id: $routeParams.id
        }, function () {
            Fun.msg.notifyInfo();
            $location.path("/index");
        });

    });