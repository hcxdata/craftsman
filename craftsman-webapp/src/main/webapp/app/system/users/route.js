'use strict';
angular.module('route', ['ngRoute']).config(
    function ($routeProvider, $locationProvider) {
        var index = {
            templateUrl: Main.rootPath + '/app/system/users/index.html',
            controller: 'IndexController'
        }, news = {
            templateUrl: Main.rootPath + '/app/system/users/new.html',
            controller: 'NewController'
        }, edit = {
            templateUrl: Main.rootPath + '/app/system/users/new.html',
            controller: 'EditController'
        }, del = {
            template: ' ',
            controller: 'DelController'
        };
        $routeProvider.when('/index', index).when('/new', news).when(
            "/:id/edit", edit).when("/:id/del", del);

    });