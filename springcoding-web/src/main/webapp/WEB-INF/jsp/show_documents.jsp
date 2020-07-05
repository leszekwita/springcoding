<head>
<%@ include file="header.jsp"%>

<link rel="stylesheet" href="${path}/resources/css/default.css" />

</head>

<div class="container">
	
		<br />
		Documents:
		<table id="documents">
			<tr>
				<th>Number</th>
				<th>Title</th>
				<th>CreationDate</th>
			</tr>
			

	
			<c:forEach items="${documents}" var="document">
			<tr>
				<td><c:out value="${document.title}" /></td>
				<td><c:out value="${document.number}" /></td>
				<td><c:out value="${document.creationDate}" /></td>
				
			</tr>

</c:forEach>

		</table>
	
</div>
<%@ include file="footer.jsp"%>