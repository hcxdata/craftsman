<%@ include file="/WEB-INF/jsp/frame/inc/page.jsp" %>
<%@ page language="java" import="com.bigbata.craftsman.page.PageWarp" %>
<%
    PageWarp pageWarp = (PageWarp) request.getAttribute("pageWarp");
%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/frame/inc/head.jsp" %>
    <%for (String cssPath : pageWarp.getCssPathArray()) {%>
    <link href="<%=path+cssPath%>"/>
    <%}%>
</head>
<body>
<%@ include file="/WEB-INF/jsp/frame/structure/header.jsp" %>
<%@ include file="/WEB-INF/jsp/frame/structure/nav.jsp" %>
<!-- MAIN PANEL -->
<div id="main" role="main" ng-app="app">
    <!-- MAIN CONTENT -->
    <div id="content">
        <%@ include file="/WEB-INF/jsp/frame/structure/ribbon.jsp" %>
        <div class="col-sm-12" ng-view>
        </div>
    </div>
    <!-- END MAIN CONTENT -->
</div>
<%@ include file="/WEB-INF/jsp/frame/inc/bottom.jsp" %>
<%for (String jsPath : pageWarp.getJsPathArray()) {%>
<script src="<%=path+jsPath%>"></script>
<%}%>
</body>
</html>