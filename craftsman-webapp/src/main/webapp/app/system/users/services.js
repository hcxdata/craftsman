'use strict';

angular.module('app.services', []).factory('userService', function($resource) {
	return $resource('/api/system/users/:id', {
		id : '@id'
	},{
		update:{method:'PUT'}
	});
});