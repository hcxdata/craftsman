'use strict';

angular.module('app.filters', []).filter('trust', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	};
});