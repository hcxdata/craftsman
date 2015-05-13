'use strict';
angular.module('services').factory('roleService', function ($resource) {
    return $resource(Main.rootPath + '/api/system/roles/:id', {
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