'use strict';
angular.module(
	'app', ['pasvaz.bindonce', 'ngRoute', 'ngResource', 'route', 'Main.config', 'controllers',
		'services', 'Main.services', 'Main.directives', 'ngFabForm', 'treeGrid'
	]).run(function($location) {
	$location.path("/index");
});
angular.module('controllers', []).controller("IndexController", function($scope) {
	$scope.$on("refreshAndShowRoleList",
		function(event, data) {
			$scope.$broadcast("refreshAndShowRoleListFromIndex", data);
		});
	$scope.$on("getUser",
		function(event, data) {
			$scope.$broadcast("getUserFromIndex", data);
		});
});


angular.module('services', []);