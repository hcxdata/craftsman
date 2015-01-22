<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page session="false" %>
<html>
  <head>
    <title>SMS Message List</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
    <style type="text/css"><!--

	body, table {
		font-size: 16px;
	}
	
	.sms-table {
		border-collapse:collapse;
	}
	
	.sms-table, td, th {
		border:1px solid black;
	}
	
	--></style>
  </head>
  <body>
    <h1>SMS Message List</h1>
    <table class="sms-table">
		<tr>
			<th>ID</th>
			<th>接收号码</th>
			<th>短信内容</th>
			<th>状态时间</th>
		</tr>
		<c:forEach items="${smsMessageList}" var="message">
		<tr>
			<td><c:out value="${message.id}" /></td>
			<td><c:out value="${message.remoteNumber}" /></td>
			<td><c:out value="${message.smsContent}" /></td>
			<td><c:out value="${message.smsTime}" /></td>
		</tr>
		</c:forEach>
	</table>  
	
  </body>
</html>
