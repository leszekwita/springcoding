<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@page import="radzik.michal.model.UserType"%>


<form:form commandName="userCommand" method="POST" action="${path}/addUserAjax"
	class="form-horizontal" id="addUserForm">

	
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
				<form:label path="birthDate" class="control-label ">Birth Date:&nbsp;</form:label>
				<form:input path="birthDate" class="form-control birthDate" />
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
		<form:label path="rolesTexts" class="control-label">Role 1:&nbsp;</form:label>
		<form:checkbox path="rolesTexts" class="form-control" value="Role 1"/>&nbsp;
		<form:label path="rolesTexts" class="control-label">Role 2:&nbsp;</form:label>
		<form:checkbox path="rolesTexts" class="form-control" value="Role 2"/>&nbsp;
		<form:label path="rolesTexts" class="control-label">Role 3:&nbsp;</form:label>
		<form:checkbox path="rolesTexts" class="form-control" value="Role 3"/>
	</div>
		<div class="form-group">
		<input type="submit" value="Submit" class="btn" id="addUserFormSubmit"/>
</div>

</form:form>
