'use strict';

angular.module('app.controllers', []).controller('IndexController',
		function($scope, $location, userService) {
			userService.query(function(users) {
				$scope.users = users;
			});
		}).controller('NewController',
		function($scope, $location, userService, $q) {
			$scope.save = function() {
				userService.save($scope.user, function() {
					alert('success');
					$location.path("/index");
				});
			}
		}).controller('EditController',
		function($scope, $location, $routeParams, userService) {
			userService.get({
				id : $routeParams.id
			},function(user){
				$scope.user = user;
				$('#confimPassWord').val(user.password);
			});
			$scope.save = function() {
				userService.update($scope.user, function() {
					alert('success');
					$location.path("/index");
				});
			}

		}).controller('DelController',
		function($scope, $location, $routeParams, userService) {
			alert('del');
			userService.delete({
				id : $routeParams.id
			}, function() {
				alert('success');
				$location.path("/index");
			});

		});