'use strict';

angular.module('app.controllers', []).controller('IndexController',
		function($scope, userService) {
			userService.query(function(users) {
				$scope.users = users;
			});
		}).controller('NewController', function($scope) {
}).controller('EditController', function($scope) {

}).controller('DelController', function($scope) {

});