'use strict';

angular.module('Main.config', ['ngRoute', 'ngResource', 'ngFabForm']).config(function ($httpProvider) {

    $httpProvider.interceptors.push(function ($q) {
        return {
            request: function (config) {
                config.headers['x-request-with'] = 'xmlhttprequest';
                return config || $q.when(config);
            },
            responseError: function (res) {
                if (res.status === 401) {
                    location.reload();
                } else if (res.status === 500) {
                    Fun.notifyWarn(res.status + ' ' + res.statusText, res.data.message)
                }
                return $q.reject(res);
            }
        };
    });
}).config(function (ngFabFormProvider) {
    ngFabFormProvider.extendConfig({
        validationsTemplate: Main.rootPath + '/frame/directives/ng-fab-form/message-zh.html',
        preventInvalidSubmit: true,
        preventDoubleSubmit: true,
        setFormDirtyOnSubmit: true,
        scrollToAndFocusFirstErrorOnSubmit: true,
        scrollAnimationTime: 900,
        scrollOffset: -100
    });
});