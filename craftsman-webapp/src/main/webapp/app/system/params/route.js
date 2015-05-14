'use strict';
angular.module('route', ['ngRoute']).config(
    function ($routeProvider, $locationProvider) {
        var index = {
            templateUrl: Main.rootPath + '/app/system/params/index.html',
            controller: 'IndexController'
        }, news = {
            templateUrl: Main.rootPath + '/app/system/params/newParamType.html',
            controller: 'NewParamTypeController'
        }, edit = {
            templateUrl: Main.rootPath + '/app/system/params/newParamType.html',
            controller: 'EditParamTypeController'
        }, del = {
            template: ' ',
            controller: 'DelParamTypeController'
        }, paramNews = {
            templateUrl: Main.rootPath + '/app/system/params/newParam.html',
            controller: 'NewParamController'
        }, paramEdit = {
            templateUrl: Main.rootPath + '/app/system/params/newParam.html',
            controller: 'EditParamController'
        }, paramDel = {
            template: ' ',
            controller: 'DelParamController'
        };
        $routeProvider.when('/index', index).when('/:code/index', index).when('/new', news).when(
            "/:id/edit", edit).when("/:id/del", del).when('/paramNew', paramNews).when(
            "/:id/paramEdit", paramEdit).when("/:typeCode/:id/paramDel", paramDel);

    });