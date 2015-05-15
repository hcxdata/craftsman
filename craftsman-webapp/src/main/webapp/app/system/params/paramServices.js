'use strict';
angular.module('services', []).factory('paramService', function ($resource) {
    return $resource(Main.rootPath + '/api/system/params/:id', {
        id: '@id'
    }, {
        'query': {
            method: 'GET',
            isArray: false
        },
        update: {
            method: 'PUT'
        }
    });
});