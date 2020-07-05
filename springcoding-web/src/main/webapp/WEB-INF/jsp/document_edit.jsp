<c:set var="path" value="${pageContext.request.contextPath}" />
<div class="modal fade" id="editDocumentDialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Edit document</h4>
			</div>

			<%-- <form id="editDocumentForm" class="horizontal" role="form" action="./documents/save" method="POST">--%>
			<form id="editDocumentForm" class="horizontal" role="form" action="${path}/updateDocumentAjax" method="POST">
				<div class="modal-body">

					<div class="form-group">
						<label> Title * </label> <input id="title" type="text" name="title" class="form-control"></input>
					</div>

					<div class="form-group">
						<label> Number * </label> <input id="number" type="text" name="number" class="form-control"></input>
					</div>
					<div class="form-group">
						<label> Document Type </label> <select name="documentType" id="documentType">
							<option value=""></option>
							<c:forEach var="item" items="${documentTypesValues}">
								<option value="${item}">${item}</option>

							</c:forEach>
						</select>

					</div>

					<div class="form-group">
						<img id="fileForEdit" /><input type="file" id="icoFile" accept="image/*" name="icoFile" class="ui-widget" required>
					</div>

					<div class="col-sm-12 row">
						<div class="col-sm-4">
							<label for="att1" class="control-label">Attachment 1:&nbsp;</label>
						</div>
						<div class="col-sm-8">
							<input id="att1" type="checkbox" class="pull-left attachment-checkbox" name="attachmentsTexts"
								value="Attachment 1" />
						</div>
					</div>
					<div class="col-sm-12 row">
						<div class="col-sm-4">
							<label for="att2" class="control-label">Attachment 2:&nbsp;</label>
						</div>
						<div class="col-sm-8">
							<input id="att2" type="checkbox" class="pull-left attachment-checkbox" name="attachmentsTexts"
								value="Attachment 2" />
						</div>
					</div>
					<div class="col-sm-12 row">
						<div class="col-sm-4">
							<label for="att3" class="control-label">Attachment 3:&nbsp;</label>
						</div>
						<div class="col-sm-8">
							<input id="att3" type="checkbox" class="pull-left attachment-checkbox" name="attachmentsTexts"
								value="Attachment 3" />
						</div>
					</div>

					<div class="form-group">

						<input type="hidden" id="id" name="id" value="">
					</div>

					<div class="modal-footer">


						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-primary" id="updateDocumentFormSubmit">Save</button>
					</div>
				</div>
			</form>
		</div>


	</div>
</div>

