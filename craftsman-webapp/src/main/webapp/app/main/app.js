'use strict';
angular.module(
		'app',
		[ 'ngRoute', 'ngResource', 'app.route', 'app.controllers',
				'app.services' ]).config(function($httpProvider) {
	$httpProvider.interceptors.push(function($q) {
		return {
			request : function(config) {
				config.headers['x-request-with'] = 'xmlhttprequest';
				return config || $q.when(config);
			},
			responseError : function(res) {
				if (res.status === 401) {
					location.reload();
				}
				return $q.reject(res);
			}
		};
	});
}).run(function($location) {
	$location.path("/index");
});