'use strict';
angular.module('services').factory('menuService', function($resource) {
    return $resource(Main.rootPath + '/app/system/roles/test/menu.json' /*'/api/system/roles/:roleid/menus'*/ , {
        roleid: '@roleid'
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