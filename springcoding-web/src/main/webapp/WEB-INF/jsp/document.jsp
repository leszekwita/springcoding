<%@ include file="header.jsp"%>

<div class="container">
	<h2>Document Information</h2>

	<table>
		<tr>
			<td>Title:</td>
			<td>${title}</td>
		</tr>
		<tr>
			<td>Number:</td>
			<td>${number}</td>
		</tr>
	</table>

	<div class="col-sm-6">
		<form:form method="POST" action="${path}/addDocument"
			class="form-horizontal">

			<div class="form-group">
				<form:label path="title" class="control-label">Title:&nbsp;</form:label>
				<form:input path="title" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="number" class="control-label">Number:&nbsp;</form:label>
				<form:input path="number" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="documentType" class="control-label">Document Type:&nbsp;</form:label>
				<form:select path="documentType" items="${documentTypesValues}" />
			</div>
			<div class="form-group">
				<input type="submit" value="Submit" class="btn" />
			</div>

		</form:form>
	</div>

</div>



<%@ include file="footer.jsp"%>