'use strict';

angular.module('services').factory('paramTypeService', function($resource) {
	return $resource(Main.rootPath + '/api/system/paramTypes/:id' , {
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