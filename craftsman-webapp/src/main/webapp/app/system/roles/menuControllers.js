'use strict';

angular.module('controllers').controller('IndexMenuController',
    function($scope, $location, menuService) {
        $scope.$on("refreshAndShowMenuListFromIndex", function(e, data) {
            $scope.refresh(data.id);
        });
        $scope.$on('getRoleFromIndex', function(e, data) {
            $scope.role = data;
        });
        /*刷新*/
        $scope.refresh = function(roleid) {
            if (roleid !== undefined)
                $scope.tree_data = menuService.query({
                    roleid: roleid
                });
            else
                $scope.tree_data = [];
        };
        /*列定义*/
        $scope.col_defs = [{
            field: "hrefTarget",
            displayName: "链接地址"
        }, {
            field: "leaf",
            displayName: "是否叶子节点",
            cellTemplate: '<text ng-if="row.branch[col.field] === true">是</text><text ng-if="row.branch[col.field] === false">否</text>'
        }];

        $scope.my_tree = {};

        $scope.expand_branch_all = function(){
            $scope.my_tree.expand_all();
        };

        $scope.refresh();
    }).controller('EditController',
    function($scope, $location, $routeParams, menuService) {
        $scope.save = function() {
            menuService.update($scope.menu, function() {
                Fun.msg.notifyInfo();
                $location.url("/index?initChain=" + $scope.initChain);
            });
        };
    });