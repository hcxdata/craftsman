'use strict';

angular.module('app.services', []).factory('userService', function ($resource) {
    return $resource(main.rootPath + '/api/system/users/:id', {
        id: '@id'
    }, {
        'query': {method: 'GET', isArray: false},
        update: {method: 'PUT'}
    });
});