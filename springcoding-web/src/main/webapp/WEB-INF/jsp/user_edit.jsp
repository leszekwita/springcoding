<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*, java.lang.*"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<div class="modal fade" id="editUserDialog">

	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Edit user</h4>
			</div>
			<%-- <form id="editUserForm" class="horizontal" role="form" action="./users/save" method="POST">--%>
			<form id="editUserForm" class="horizontal" role="form" action="${path}/updateUserAjax" method="POST">

				<div class="modal-body">
					<div class="form-group">
						<label> FirstName</label> <input id="firstName" type="text" name="firstName" class="form-control" required
							title="Uzupełnij imie"></input> <span id="firstNameValidationError" class="redValidationError"></span>
					</div>
					<div class="form-group">
						<label> LastName</label> <input id="lastName" type="text" name="lastName" class="form-control" required></input> <span
							id="lastNameValidationError" class="redValidationError"></span>
					</div>
					<div class="form-group">
						<label> Email * </label> <input id="email" type="text" name="email" class="form-control" required></input>
					</div>
					<div class="form-group">
						<label> Password </label> <input id="password" type="text" name="password" class="form-control"></input>
					</div>
					<div class="form-group">
						<span id="birthDateValidationError" class="redValidationError"></span> <label> BirthDate </label> <input
							id="birthDateTwo" type="text" name="birthDateText" class="form-control birthDate"></input>
					</div>
					<div class="form-group">
						<label> Active</label> <input id="active" type="text" name="active" class="form-control"></input>
					</div>
					<div class="form-group">
						<label> Sex * </label>
					</div>
					<div class="form-group">
		<label><input type="radio" id="_sexMan" name="${user.id}_sex" value="Man" class="form-control"></input>Man </label> 
		<label><input type="radio" id="_sexWoman" name="${user.id}_sex" value="Woman" class="form-control"></input>Woman</label>
						<%-- <span id="${user.id}_id"><c:out value="${user.id}" /></span> --%>
						<%-- <c:out value="${userSex}" /> --%>
					</div>
					<div class="form-group">
						<label>userType </label> <select name="userType" id="userType">
							<option value=""></option>
							<c:forEach var="item" items="${userTypesValues}">
								<option value="${item}">${item}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-12 row">
						<span id="roleValidationError" class="redValidationError"></span>
						<div class="col-sm-4">
							<label for="rol1" class="control-label">Role 1:&nbsp;</label>
						</div>
						<div class="col-sm-8">
							<input id="rol1" type="checkbox" class="pull-left role-checkbox" name="rolesTexts" value="Role 1" />
						</div>
					</div>
					<div class="col-sm-12 row">
						<div class="col-sm-4">
							<label for="rol2" class="control-label">Role 2:&nbsp;</label>
						</div>
						<div class="col-sm-8">
							<input id="rol2" type="checkbox" class="pull-left role-checkbox" name="rolesTexts" value="Role 2" />
						</div>
					</div>
					<div class="col-sm-12 row">
						<div class="col-sm-4">
							<label for="rol3" class="control-label">Role 3:&nbsp;</label>
						</div>
						<div class="col-sm-8">
							<input id="rol3" type="checkbox" class="pull-left role-checkbox" name="rolesTexts" value="Role 3" />
						</div>
					</div>

					<div class="form-group">
						<input type="hidden" id="id" name="id" value="">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-primary" id="updateUserFormSubmit" onclick="return validate();">Save</button>
					</div>

				</div>

			</form>

		</div>


	</div>

</div>
