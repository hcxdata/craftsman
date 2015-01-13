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
			}, del = {
				template : ' ',
				controller : 'DelController'
			};
			$routeProvider.when('/index', index).when('/new', news).when(
					"/:id/edit", edit).when("/:id/del", del);

		});