'use strict';

angular.module('services').factory('dictTypeService', function($resource) {
	return $resource(Main.rootPath + '/api/system/dictTypes/:id' , {
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