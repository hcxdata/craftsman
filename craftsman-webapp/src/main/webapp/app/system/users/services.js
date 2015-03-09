'use strict';

angular.module('services', []).factory('userService', function ($resource) {
    return $resource(Main.rootPath + '/api/system/users/:id', {
        id: '@id'
    }, {
        'query': {method: 'GET', isArray: false},
        update: {method: 'PUT'}
    });
});