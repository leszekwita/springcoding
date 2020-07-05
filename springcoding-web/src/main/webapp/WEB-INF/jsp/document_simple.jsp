<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<head>

</head>



<h2>Document Information</h2>

<form:form method="POST" action="${path}/addDocumentAjax" class="form-horizontal" id="addDocumentForm" enctype="multipart/form-data">

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
		<form:label path="attachmentsTexts" class="control-label">Attachment 1:&nbsp;</form:label>
		<form:checkbox path="attachmentsTexts" class="form-control" value="Attachment 1" />
		&nbsp;
		<form:label path="attachmentsTexts" class="control-label">Attachment 2:&nbsp;</form:label>
		<form:checkbox path="attachmentsTexts" class="form-control" value="Attachment 2" />
		&nbsp;
		<form:label path="attachmentsTexts" class="control-label">Attachment 3:&nbsp;</form:label>
		<form:checkbox path="attachmentsTexts" class="form-control" value="Attachment 3" />
	</div>

	<div class="form-group">
		<img id="fileForEdit"/><input type="file" id="icoFile" accept="image/*" name="icoFile" class="ui-widget" required>
	</div>

	<div class="form-group">
		<input type="submit" value="Submit" class="btn" id="addDocumentFormSubmit" />
	</div>

</form:form>
