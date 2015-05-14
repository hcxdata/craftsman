'use strict';
angular.module(
	'app', ['pasvaz.bindonce', 'ngRoute', 'ngResource', 'route', 'Main.config', 'controllers',
		'services', 'Main.services', 'Main.directives', 'ngFabForm'
	]).run(function($location) {
		if(!/^\/(\w+)\/index$/.test($location.$$path))
			$location.path("/index");
});

angular.module('controllers', []).controller("IndexController", function($scope) {
	// 添加事件监听用于显示参数列表
	$scope.$on("refreshAndShowParamList",
		function(event, data) {
			$scope.$broadcast("refreshAndShowParamListFromIndex", data);
		});
	$scope.$on("getParamType",
		function(event, data) {
			$scope.$broadcast("getParamTypeFromIndex", data);
		});
});

