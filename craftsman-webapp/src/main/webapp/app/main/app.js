'use strict';
angular.module('app', [ 'ngRoute', 'ngResource', 'app.route',
		'app.controllers', 'app.services' ]);

angular.module('app').run(function($location){
	$location.path("/index");
});
