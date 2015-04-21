'use strict';

angular.module('controllers', []).controller('IndexController',
    function($scope, $location, menuService) {
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
            cellTemplate: '<text ng-if="row.branch[col.field] === \'true\'">是</text><text ng-if="row.branch[col.field] === \'false\'">否</text>'
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
        }
        $scope.tree_data = [{
            "id": 1,
            "parentId": 0,
            "text": "菜单",
            "orders": 1,
            "herfTarget": "www.baidu.com",
            "leaf": "true",
            children: [{
                "id": 11,
                "parentId": 1,
                "text": "菜单1",
                "orders": 1,
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