'use strict';
angular.module('app', [ 'ngRoute','ngResource','app.route','app.controllers','app.services']).run(function($location){
	$location.path("/web/index");
});