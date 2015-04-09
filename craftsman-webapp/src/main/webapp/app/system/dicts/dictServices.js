'use strict';
angular.module('services', []).factory('dictService', function ($resource) {
    return $resource(Main.rootPath + '/api/system/dicts/:id', {
        id: '@id'
    }, {
        'query': {
            method: 'GET',
            isArray: false
        },
        update: {
            method: 'PUT'
        },
        up: {
            params: {action: "order", direction: "up"},
            method: "PUT"
        },
        down: {
            params: {action: "order", direction: "down"},
            method: "PUT"
        }
    });
});