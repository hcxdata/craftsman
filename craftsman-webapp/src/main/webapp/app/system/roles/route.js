'use strict';
angular.module('route', ['ngRoute']).config(
    function($routeProvider, $locationProvider) {
        var index = {
            templateUrl: Main.rootPath + '/app/system/roles/index.html',
            controller: 'IndexController'
        }, news = {
                templateUrl: Main.rootPath + '/app/system/roles/new.html',
                controller: 'NewRoleController'
            }, edit = {
                templateUrl: Main.rootPath + '/app/system/roles/new.html',
                controller: 'EditRoleController'
            }, del = {
                template: ' ',
                controller: 'DelRoleController'
            };
        $routeProvider.when('/index', index).when('/:id/index', index).when('/new', news).when(
            "/:id/edit", edit).when("/:id/del", del);

    });