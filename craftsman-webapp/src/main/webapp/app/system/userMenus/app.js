'use strict';
angular.module(
    'userMenusApp', ['pasvaz.bindonce', 'ngRoute', 'ngResource', 'Main.config', 'services', 'Main.services', 'Main.directives'])
    .controller('indexController', ['$scope', '$location', '$routeParams', '$window', '$route', 'loginService', '$rootScope',
        function($scope, $location, $routeParams, $window, $route, loginService, $rootScope) {
            $scope.rootPath = $rootScope.rootPath;
            $scope.menuControl = {};
            // $scope.loginUser = loginService.get({}, function() {});
            loginService.query({
                menus: 'menus'
            }, function(data) {
                function improveData(data) {
                    for (var i = 0; i < data.length; i++) {
                        /*根据浏览器地址打开对应的导航菜单*/
                        if ($scope.rootPath + '/' + data[i].hrefTarget.replace(/\#(.*)$/, "") === location.pathname) {
                            data[i].active = true;
                        }
                        if (data[i].children && data[i].children.length > 0) {
                            improveData(data[i].children);
                        }
                    }
                    return data;
                };
                if (data.$resolved === true) {
                    $scope.menuData = improveData(data);
                }
            });

            /*当前路由不跳转*/
            var lastRoute = $route.current;
            $scope.$on('$locationChangeSuccess', function(event, next, nextParams) {
                $route.current = lastRoute;
            });
        }
    ]).config(
        ['$routeProvider',
            function($routeProvider) {
                var index = {
                    templateUrl: Main.rootPath + '/app/system/userMenus/index.html',
                    controller: 'indexController'
                };
                $routeProvider.when('/', index).when('/index', index);
            }
        ]).run(function($location, $rootScope) {
        $rootScope.rootPath = Main.rootPath;
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