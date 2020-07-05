<%@page import="radzik.michal.model.UserType"%>
<%@ include file="header.jsp"%>
<div class="container">
	<h2>User Information</h2>


	<div class="col-sm-6">
		<form:form method="POST" action="${path}/addUser"
			class="form-horizontal">

			<div class="form-group">
				<form:label path="firstName" class="control-label">First Name:&nbsp;</form:label>
				<form:input path="firstName" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="lastName" class="control-label">Last Name:&nbsp;</form:label>
				<form:input path="lastName" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="email" class="control-label">Email:&nbsp;</form:label>
				<form:input path="email" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="password" class="control-label">Password:&nbsp;</form:label>
				<form:input type="password" path="password" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="userType" class="control-label">User Type:&nbsp;</form:label>
				<form:select path="userType" items="${userTypesValues}" />
			</div>
			<div class="form-group">
				<form:label path="birthDate" class="control-label" id="birthDate">Birth Date:&nbsp;</form:label>
				<form:input path="birthDate" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="active" class="control-label">Active:&nbsp;</form:label>
				<form:checkbox path="active" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="sex" class="control-label">Sex:&nbsp;</form:label>
				<form:radiobutton path="sex" value="Man" />Man
				<form:radiobutton path="sex" value="Woman" />Woman
			</div>
			<div class="form-group">
				<input type="submit" value="Submit" class="btn" />
			</div>			
			
		</form:form>
	</div>


</div>




<%@ include file="footer.jsp"%>