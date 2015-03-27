<%@ include file="/WEB-INF/jsp/frame/inc/page.jsp" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:url var="rootURL" value="/" />
<!DOCTYPE html>
<html>
    
    <head>
        <title>
            登录
        </title>
        <%@ include file="/WEB-INF/jsp/frame/inc/head.jsp" %>
        <!-- 自定义全局样式 -->
        <link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/app/system/login/style.css">
    </head>
    
    <body class="east-login-body">
        <div class="container east-login-container">
            <div class="row">
                <div class="col-md-6 east-clear-float-align-middle" style="height:48px;">
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            无效的用户名或密码!
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">
                            用户已经退出!
                        </div>
                    </c:if>
                </div>
                <div class="col-md-6 east-clear-float-align-middle">
                    <div class="jarviswidget" id="wid-id-0" data-widget-colorbutton="false"
                    data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon">
                                <i class="fa fa-lock">
                                </i>
                            </span>
                            <h2 style="width:auto;">
                                用户登录
                            </h2>
                        </header>
                        <div>
                            <div class="widget-body">
                                <form:form id="loginForm" method="post" action="${rootURL}web/login" modelAttribute="user"
                                class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label for="username" class="col-sm-2 control-label">
                                            用户名*
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" id="username" name="username" class="form-control"
                                            placeholder="UserName" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="col-sm-2 control-label">
                                            密&nbsp;&nbsp;&nbsp;码*
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="password" id="password" name="password" class="form-control"
                                            placeholder="Password" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-9">
                                            <input type="submit" class="btn btn-primary btn-lg btn-block" value="登&nbsp;&nbsp;&nbsp;录">
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </body>

</html>