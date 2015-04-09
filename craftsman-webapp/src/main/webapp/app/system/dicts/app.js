'use strict';
angular.module(
	'app', ['pasvaz.bindonce', 'ngRoute', 'ngResource', 'route', 'Main.config', 'controllers',
		'services', 'Main.services', 'Main.directives', 'ngFabForm'
	]).run(function($location) {
	$location.path("/index");
});

angular.module('controllers', []).controller("IndexController", function($scope) {
	$scope.$on("refreshAndShowDictList",
		function(event, data) {
			$scope.$broadcast("refreshAndShowDictListFromIndex", data);
		});
	$scope.$on("getDictType",
		function(event, data) {
			$scope.$broadcast("getDictTypeFromIndex", data);
		});
});

