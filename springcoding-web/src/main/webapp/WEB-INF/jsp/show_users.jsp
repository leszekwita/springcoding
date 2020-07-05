<head>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="${path}/resources/css/default.css" />
</head>
<div class="container">
	<br /> Users:
	<table id="users">
		<tr>
			<th>First name</th>
			<th>Last name</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><c:out value="${user.firstName}" /></td>
				<td><c:out value="${user.lastName}" /></td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="footer.jsp"%>