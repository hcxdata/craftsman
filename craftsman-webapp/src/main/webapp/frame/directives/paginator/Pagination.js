angular.module("main.directives", []).directive('mainPage', ['$rootScope', function ($rootScope) {
    var pageDec = {
        restrict: 'E',
        replace: true,
        templateUrl: main.rootPath + '/frame/directives/paginator/page.html',
        scope: {page: '=page'}
    };
    return pageDec;
}]);