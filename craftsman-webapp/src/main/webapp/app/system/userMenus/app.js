'use strict';
angular.module(
    'userMenusApp', ['pasvaz.bindonce', 'ngRoute', 'ngResource', 'Main.config', 'services', 'Main.services', 'Main.directives'])
    .controller('indexController', ['$scope', '$location', '$routeParams', '$window', '$route', 'loginService',
        function($scope, $location, $routeParams, $window, $route, loginService) {
            // $scope.loginUser = loginService.get({}, function() {});
            $scope.menuData = loginService.query({
                menus: 'menus'
            }, function() {});
        }
    ]).config(
        ['$routeProvider',
            function($routeProvider) {
                var index = {
                    templateUrl: Main.rootPath + '/app/system/userMenus/index.html',
                    controller: 'indexController'
                };
                $routeProvider.when('/', index).when('/index', index).otherwise({
                    redirectTo: '/'
                });
            }
        ]).run(function($location) {
        $location.path("/index");
    });
angular.module('services', [])
    .factory('loginService', function($resource) {
        return $resource(Main.rootPath + '/app/system/userMenus/test/data.json' /*'/api/system/loginUser/:menus'*/ , {
            menus: '@menus'
        }, {
            'query': {
                method: 'GET',
                isArray: true,
                cache: false
            }
        });
    });
angular.bootstrap(document.getElementById("userMenusApp"), ['userMenusApp']);