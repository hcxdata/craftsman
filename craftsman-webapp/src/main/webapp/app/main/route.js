'use strict';
angular.module('app.route', [ 'ngRoute' ]).config(
		function($routeProvider, $locationProvider) {
			var index = {
				templateUrl : 'app/main/index.html',
				controller : 'IndexController'
			}, news = {
				templateUrl : 'app/main/new.html',
				controller : 'NewController'
			}, edit = {
				templateUrl : 'app/main/new.html',
				controller : 'EditController'
			};
			$routeProvider.when('/web/index', index).when('new', news);

			$locationProvider.html5Mode(true).hashPrefix('!');
		});