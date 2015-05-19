'use strict';
angular.module(
    'userMenusApp', ['pasvaz.bindonce', 'ngRoute', 'ngResource', 'Main.config',
        'Main.services', 'Main.directives'
    ]).controller('indexController', ['$scope', '$location', '$routeParams', '$window', '$route',
        function ($scope, $location, $routeParams, $window, $route) {
            $scope.html = "hello world!";
        }]).config(
    ['$routeProvider', function ($routeProvider) {
        var index = {
            templateUrl: Main.rootPath + '/app/system/userMenus/index.html',
            controller: 'indexController'
        };
        $routeProvider.when('/index', index);
    }]).run(function ($location) {
        $location.path("/index");
    });
angular.bootstrap(document.getElementById("userMenusApp"), ['userMenusApp']);
