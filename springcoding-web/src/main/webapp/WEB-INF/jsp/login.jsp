<%@ include file="header.jsp"%>
<div class="container">
	<h2>Login Information</h2>


	<div class="col-sm-6">
		<form:form method="POST" action="${path}/loginExecute"
			class="form-horizontal">

		
			<div class="form-group">
				<form:label path="email" class="control-label">Email:&nbsp;</form:label>
				<form:input path="email" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="password" class="control-label">Password:&nbsp;</form:label>
				<form:input type="password" path="password" class="form-control" />
			</div>
		
			<div class="form-group">
				<input type="submit" value="Submit" class="btn" />
			</div>			
		</form:form>
	</div>
	

</div>



<%@ include file="footer.jsp"%>