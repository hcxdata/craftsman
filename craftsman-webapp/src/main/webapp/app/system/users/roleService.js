'use strict';
angular.module('services').factory('roleService', function($resource) {
    return $resource(Main.rootPath + '/app/system/users/test/role.json'  /*'/api/system/users/:userid/roles'*/ , {
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