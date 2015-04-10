'use strict';

angular.module('Main.filters', []).filter('trust', function ($sce) {
    return function (text) {
        return $sce.trustAsHtml(text);
    };
});