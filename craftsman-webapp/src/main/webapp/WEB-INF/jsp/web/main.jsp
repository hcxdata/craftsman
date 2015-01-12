<%@ include file="/WEB-INF/jsp/frame/inc/page.jsp"%>
<!DOCTYPE html>
<html ng-app="app">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/frame/inc/head.jsp"%>
<base href="/web">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/frame/structure/header.jsp"%>
	<%@ include file="/WEB-INF/jsp/frame/structure/nav.jsp"%>
	<!-- MAIN PANEL -->
	<div id="main" role="main">
		<!-- MAIN CONTENT -->
		<div id="content">
			<%@ include file="/WEB-INF/jsp/frame/structure/ribbon.jsp"%>
			<div class="col-sm-12" ng-view>
			</div>
		</div>
		<!-- END MAIN CONTENT -->
	</div>
	<%@ include file="/WEB-INF/jsp/frame/inc/bottom.jsp"%>
	<script src="<%=path%>/app/main/app.js"></script>
	<script src="<%=path%>/app/main/route.js"></script>
	<script src="<%=path%>/app/main/controllers.js"></script>
	<script src="<%=path%>/app/main/services.js"></script>
</body>
</html>