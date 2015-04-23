'use strict';

angular.module('services', []).factory('menuFetchService', function($resource) {
	return $resource(Main.rootPath + '/api/system/menus/:id/children', {
		id: '@id'
	}, {
		'query': {
			method: 'GET',
			isArray: false
		}
	});
}).factory('menuService', function($resource) {
	return $resource(Main.rootPath + '/api/system/menus/:id', {
		id: '@id'
	}, {
		update: {
			method: 'PUT'
		}
	});
});