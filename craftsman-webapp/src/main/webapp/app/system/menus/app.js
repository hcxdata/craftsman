'use strict';
angular.module(
	'app', ['pasvaz.bindonce', 'ngRoute', 'ngResource', 'route', 'Main.config', 'controllers',
		'services', 'Main.services', 'Main.directives', 'ngFabForm', 'treeGrid'
	]).run(function($location) {
	$location.path("/index");
});