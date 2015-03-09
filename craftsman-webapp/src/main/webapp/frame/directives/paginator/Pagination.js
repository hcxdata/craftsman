angular.module("Main.directives", []).directive('mainPage', ['$rootScope', function ($rootScope) {
    var pageDec = {
        restrict: 'E',
        replace: true,
        templateUrl: Main.rootPath + '/frame/directives/paginator/page.html',
        scope: {page: '=page'}
    };
    return pageDec;
}]);