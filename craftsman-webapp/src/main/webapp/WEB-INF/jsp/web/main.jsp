<%@ include file="/WEB-INF/jsp/frame/inc/page.jsp" %>
<!DOCTYPE html>
<html ng-app="app">
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/frame/inc/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/jsp/frame/structure/header.jsp" %>
<%@ include file="/WEB-INF/jsp/frame/structure/nav.jsp" %>
<!-- MAIN PANEL -->
<div id="main" role="main">
    <!-- MAIN CONTENT -->
    <div id="content">
        <%@ include file="/WEB-INF/jsp/frame/structure/ribbon.jsp" %>
        <div class="col-sm-12" ng-view>
        </div>
    </div>
    <!-- END MAIN CONTENT -->
</div>
<script type="application/javascript">
    var main = {};
    main.rootPath = '<%=request.getContextPath()%>';
</script>
<%@ include file="/WEB-INF/jsp/frame/inc/bottom.jsp" %>
<script src="<%=path%>/frame/plugin/paginator/Paginator.js"></script>
<script src="<%=path%>/frame/directives/paginator/Pagination.js"></script>
<script src="<%=path%>/app/system/users/app.js"></script>
<script src="<%=path%>/app/system/users/route.js"></script>
<script src="<%=path%>/app/system/users/controllers.js"></script>
<script src="<%=path%>/app/system/users/services.js"></script>
</body>
</html>