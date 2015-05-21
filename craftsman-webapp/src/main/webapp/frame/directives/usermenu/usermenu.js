angular.module("Main.directives").directive('usermenu', ['$rootScope', '$compile',
    function ($rootScope, $compile) {
        return {
            restrict: 'E',
            replace: true,
            scope: {
                data: '=',
                rootPath: '=',
                menuControl: '='
            },
            template: "<nav></nav>",
            link: function (scope, element, attrs) {

                function createUl(data, flag) {
                    var ul = "";
                    if (flag === false)
                        ul = $("<ul></ul>");
                    else
                        ul = $("<ul data-smart-menu></ul>");
                    for (var i = 0; i < data.length; i++) {
                        var li = "";
                        if (data[i].leaf === true) {
                            /*由于没有使用angular-ui-router.js,所以data-ui-sref属性只是为了smartMenu.js插件能够正确识别带链接的超链接标签而已*/
                            li = $("<li " + (data[i].active === true ? "class=\"active\"" : "") + "><a data-ui-sref href=\"{{rootPath}}/web" + data[i].hrefTarget + "\"><span>" + data[i].text + "</span></a></li>");
                        } else
                            li = $("<li data-menu-collapse><a><span class=\"menu-item-parent\">" + data[i].text + "</span></a></li>");
                        if (data[i].children && data[i].children.length > 0)
                            li.append(createUl(data[i].children, false));
                        ul.append(li);
                    }
                    return ul;
                }

                scope.$watch('data', function (new_data) {
                    if (new_data && new_data.$resolved === true) {
                        element.html("");
                        var ul = createUl(scope.data);
                        var new_elem = $compile(ul.prop("outerHTML"))(scope);
                        element.append(new_elem);
                    }
                }, true);
            }
        };
    }
]);