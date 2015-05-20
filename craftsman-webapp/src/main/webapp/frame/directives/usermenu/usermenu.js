angular.module("Main.directives").directive('usermenu', ['$rootScope', '$compile',
	function($rootScope, $compile) {
		return {
			restrict: 'E',
			replace: true,
			scope: {
				data: '='
			},
			template: "<nav></nav>",
			link: function(scope, element, attrs) {

				function createUl(data, flag) {
					var ul = "";
					if (flag === false)
						ul = $("<ul></ul>");
					else
						ul = $("<ul data-smart-menu></ul>");
					for (var i = 0; i < data.length; i++) {
						var li = "";
						if(data[i].leaf === true)
							li = $("<li><a href=\"" + data[i].hrefTarget + "\"><span>" + data[i].text + "</span></a></li>");
						else
							li = $("<li data-menu-collapse><a><span class=\"menu-item-parent\">" + data[i].text + "</span></a></li>");
						if (data[i].children && data[i].children.length > 0)
							li.append(createUl(data[i].children, false));
						ul.append(li);
					}
					return ul;
				}
				scope.$watch('data', function(new_data) {
					if (new_data.$resolved === true) {
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