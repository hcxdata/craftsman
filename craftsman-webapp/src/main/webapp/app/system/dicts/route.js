'use strict';
angular.module('route', ['ngRoute']).config(
    function ($routeProvider, $locationProvider) {
        var index = {
            templateUrl: Main.rootPath + '/app/system/dicts/index.html',
            controller: 'IndexController'
        }, news = {
            templateUrl: Main.rootPath + '/app/system/dicts/newDictType.html',
            controller: 'NewDictTypeController'
        }, edit = {
            templateUrl: Main.rootPath + '/app/system/dicts/newDictType.html',
            controller: 'EditDictTypeController'
        }, del = {
            template: ' ',
            controller: 'DelDictTypeController'
        }, dictNews = {
            templateUrl: Main.rootPath + '/app/system/dicts/newDict.html',
            controller: 'NewDictController'
        }, dictEdit = {
            templateUrl: Main.rootPath + '/app/system/dicts/newDict.html',
            controller: 'EditDictController'
        }, dictDel = {
            template: ' ',
            controller: 'DelDictController'
        };
        $routeProvider.when('/index', index).when('/:code/index',index).when('/new', news).when(
            "/:id/edit", edit).when("/:id/del", del).when('/dictNew', dictNews).when(
            "/:id/dictEdit", dictEdit).when("/:id/dictDel", dictDel);

    });