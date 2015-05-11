'use strict';
angular.module(
	'app', ['pasvaz.bindonce', 'ngRoute', 'ngResource', 'route', 'Main.config', 'controllers',
		'services', 'Main.services', 'Main.directives', 'ngFabForm', 'treeGrid'
	]).run(function($location) {
	if (!/^\/(\w+)\/index$/.test($location.$$path))
		$location.path("/index");
});

angular.module('controllers', []).controller("IndexController", function($scope) {
	$scope.$on("refreshAndShowMenuList",
		function(event, data) {
			$scope.$broadcast("refreshAndShowMenuListFromIndex", data);
		});
	$scope.$on("getRole",
		function(event, data) {
			$scope.$broadcast("getRoleFromIndex", data);
		});
});


angular.module('services', []);