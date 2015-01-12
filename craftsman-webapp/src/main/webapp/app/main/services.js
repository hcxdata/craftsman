'use strict';

angular.module('app.services', []).factory('userService', function($resource) {
	return $resource('/api/system/users/:userId', {
		userId : '@id'
	});
});