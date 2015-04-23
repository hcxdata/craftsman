'use strict';

angular.module('controllers', []).controller('IndexController',
    function($scope, $location, menuService, menuFetchService) {
        $scope.reset = function() {};
        /*$scope.tree_data = menuService.query({
            id : 0
        });*/
        $scope.col_defs = [{
            field: "id"
        }, {
            field: "text",
            displayName: "菜单名称"
        }, {
            field: "herfTarget",
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
        $scope.my_tree_handler = function(branch) {
            $scope.currentSelectBranch = branch;
        };
        $scope.move_update = function(branch, targetBranch) {

        };
        $scope.my_tree = {
            
        };
        $scope.tree_data = [{
            "id": 1,
            "parentId": 0,
            "text": "菜单",
            "orders": 1,
            "herfTarget": "www.baidu.com",
            "leaf": "false",
            children: [{
                "id": 11,
                "parentId": 1,
                "text": "菜单11",
                "orders": 1,
                "herfTarget": "www.baidu.com",
                "leaf": "true"
            }, {
                "id": 12,
                "parentId": 1,
                "text": "菜单12",
                "orders": 2,
                "herfTarget": "www.baidu.com",
                "leaf": "false",
                children: [{
                    "id": 121,
                    "parentId": 12,
                    "text": "菜单121",
                    "orders": 1,
                    "herfTarget": "www.baidu.com",
                    "leaf": "true"
                }, {
                    "id": 122,
                    "parentId": 12,
                    "text": "菜单122",
                    "orders": 2,
                    "herfTarget": "www.baidu.com",
                    "leaf": "true"
                }]
            }, {
                "id": 13,
                "parentId": 1,
                "text": "菜单13",
                "orders": 3,
                "herfTarget": "www.baidu.com",
                "leaf": "true"
            }, {
                "id": 14,
                "parentId": 1,
                "text": "菜单14",
                "orders": 4,
                "herfTarget": "www.baidu.com",
                "leaf": "true"
            }]
        }, {
            "id": 2,
            "parentId": 0,
            "text": "菜单",
            "orders": 2,
            "herfTarget": "www.baidu.com",
            "leaf": "false",
            children: [{
                "id": 21,
                "parentId": 2,
                "text": "菜单21",
                "orders": 1,
                "herfTarget": "www.baidu.com",
                "leaf": "true"
            }, {
                "id": 22,
                "parentId": 2,
                "text": "菜单22",
                "orders": 2,
                "herfTarget": "www.baidu.com",
                "leaf": "true"
            }, {
                "id": 23,
                "parentId": 2,
                "text": "菜单23",
                "orders": 3,
                "herfTarget": "www.baidu.com",
                "leaf": "true"
            }, {
                "id": 24,
                "parentId": 2,
                "text": "菜单24",
                "orders": 4,
                "herfTarget": "www.baidu.com",
                "leaf": "true"
            }]
        }];
        $scope.edit = function() {
            if ($scope.currentSelectBranch)
                $location.url("/" + $scope.currentSelectBranch["id"] + "/edit");
            else {}
        };
        $scope.delete = function() {
            if ($scope.currentSelectBranch)
                $location.url("/" + $scope.currentSelectBranch["id"] + "/edit");
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
        }, function(menu) {});
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