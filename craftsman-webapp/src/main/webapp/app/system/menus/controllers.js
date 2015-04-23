'use strict';

angular.module('controllers', []).controller('IndexController',
    function($scope, $location, menuService, menuFetchService) {
        $scope.reset = function() {};
        $scope.tree_data = menuFetchService.query({
            id: 0
        });
        $scope.col_defs = [{
            field: "id"
        }, {
            field: "text",
            displayName: "菜单名称"
        }, {
            field: "hrefTarget",
            displayName: "链接地址"
        }, {
            field: "leaf",
            displayName: "是否叶子节点",
            cellTemplate: '<text ng-if="row.branch[col.field] === \'true\'">是</text><text ng-if="row.branch[col.field] === \'false\'">否</text>',
            cellTemplateInfo: '<text ng-if="info.content[col.field] === \'true\'">是</text><text ng-if="info.content[col.field] === \'false\'">否</text>'
        }, {
            field: "orders",
            displayName: "排序"
        }];
        $scope.currentSelectBranch = {
            id: 0,
            text: ""
        };
        $scope.my_tree_expand_handler = function(branch) {
            if (!branch.loaded)
                menuFetchService.query({
                    id: branch.id
                }, function(dataArr) {
                    for (var i = 0; i < dataArr.length; i++) {
                        var data = dataArr[i];
                        var b = data;
                        b.uid = Math.random();
                        b.parent_uid = branch.uid;
                        $scope.my_tree.add_branch(branch, data);
                    }
                    $scope.my_tree.set_branch_loaded(branch);
                    $scope.currentSelectBranch = branch;
                });
        };
        $scope.my_tree_select_handler = function(branch) {
            $scope.currentSelectBranch = branch;
        };
        $scope.move_update = function(branch, targetBranch) {
            if (branch.uid != targetBranch.uid) {
                menuService.change({
                    id: branch.id,
                    action: 'changeParent',
                    parentId: targetBranch.id
                }, function(result) {

                });
            }
        };
        $scope.my_tree = {

        };
        $scope.edit = function() {
            if ($scope.currentSelectBranch) {
                var parent = $scope.my_tree.get_parent_branch($scope.currentSelectBranch);
                if (!parent)
                    parent = {
                        id: 0,
                        text: ""
                    };
                $location.url("/" + $scope.currentSelectBranch["id"] + "/edit?parentId=" + parent.id + "&parentText=" + parent.text);
            } else {}
        };
        $scope.delete = function() {
            if ($scope.currentSelectBranch)
                $location.path("/" + $scope.currentSelectBranch["id"] + "/del");
            else {}
        };
    }).controller('NewController',
    function($scope, $location, menuService) {
        $scope.menu = {};
        $scope.menu.parentId = $location.$$search.parentId;
        $scope.menu.parentText = $location.$$search.parentText ? $location.$$search.parentText : "根节点";
        $scope.save = function() {
            menuService.save($scope.menu, function() {
                $location.path("/index");
            });
        }
    }).controller('EditController',
    function($scope, $location, $routeParams, menuService) {
        menuService.get({
            id: $routeParams.id
        }, function(menu) {
            $scope.menu = menu;
            $scope.menu.parentId = $location.$$search.parentId;
            $scope.menu.parentText = $location.$$search.parentText ? $location.$$search.parentText : "根节点";
        });
        $scope.save = function() {
            menuService.update($scope.menu, function() {
                $location.path("/index");
            });
        }

    }).controller('DelController',
    function($scope, $location, $routeParams, menuService) {
        menuService.delete({
            id: $routeParams.id
        }, function() {
            $location.path("/index");
        });

    });