'use strict';
angular.module('services').factory('roleService', function ($resource) {
    return $resource(Main.rootPath + '/api/system/users/:userid/roles', {
        userid: '@userid'
    }, {
        'query': {
            method: 'GET',
            isArray: true
        },
        update: {
            method: 'PUT'
        }
    });
});