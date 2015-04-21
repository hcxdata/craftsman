'use strict';

angular.module('services', []).factory('menuService', function ($resource) {
    return $resource(Main.rootPath + '/api/system/menus/:id/children', {
        id: '@id'
    }, {
        'query': {method: 'GET', isArray: false},
        update: {method: 'PUT'}
    });
});