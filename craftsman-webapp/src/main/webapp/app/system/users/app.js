'use strict';
angular.module(
    'app',
    ['pasvaz.bindonce', 'ngRoute', 'ngResource', 'app.route', 'app.controllers',
        'app.services', 'main.services', 'main.directives']).config(function ($httpProvider) {
        function notifyError(rejection){
            $.bigBox({
                title: rejection.status + ' ' + rejection.statusText,
                content: rejection.data.message,
                color: "#C46A69",
                icon: "fa fa-warning shake animated",
                timeout: 6000
            });
        }
        $httpProvider.interceptors.push(function ($q) {
            return {
                request: function (config) {
                    config.headers['x-request-with'] = 'xmlhttprequest';
                    return config || $q.when(config);
                },
                responseError: function (res) {
                    if (res.status === 401) {
                        location.reload();
                    }else if(res.status === 500){
                        notifyError(res);
                    }
                    return $q.reject(res);
                }
            };
        });
    }).run(function ($location) {
        $location.path("/index");
    });