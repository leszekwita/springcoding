<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*, java.lang.*"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/default.css" />

<br />
<table id="users">
	<tr>
		<th>Id</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Email</th>
	    <th>Password</th>
		<th>BirthDate</th>
		<th>Active</th>
		<th>Sex</th>
		<th>UserType</th>
		<th>Role</th>
		<th>Sex</th>
		<th colspan="2">Action</th>
	</tr>
	<c:forEach items="${users}" var="user">
		<tr>
			<td><span id="${user.id}_id"><c:out value="${user.id}" /></span></td>
            <td><span id="${user.id}_firstName"><c:out value="${user.firstName}" /></span></td>
			<td><span id="${user.id}_lastName"><c:out value="${user.lastName}" /></span></td>
			<td><span id="${user.id}_email"><c:out value="${user.email}" /></span></td>
 			<td><span id="${user.id}_password"><c:out value="${user.password}" /></span></td>
			<td><span id="${user.id}_birthDate"><c:out value="${user.birthDateText}" /></span></td> 
			<td><span id="${user.id}_active"><c:out value="${user.active}" /></span></td>
			<td><span id="${user.id}_sex"><c:out value="${user.sex}" /></span></td>
			<td><span id="${user.id}_typeUser"><c:out value="${user.userType}" /></span></td>
			<td>
			<c:set var="rolesArr" value="${user.roles}" /> 
			<c:set var="rol" value="Role" />
		 	<c:set var="rolName1" value="${rol} 1" /> 
			<c:set var="rolName2" value="${rol} 2" />
			<c:set var="rolName3" value="${rol} 3" />
			 
			<c:forEach var="i" begin="1" end="3" > 
			<c:set var="role" value="${rolesArr[i-1]}" />
			<c:if test="${(role.name eq rolName1) or (role.name eq rolName2) or (role.name eq rolName3)}">
			<label class="control-label">${role.name}:&nbsp;</label>
			<input type="checkbox" name="rolesIds" id="${role.id}" value="${role.name}" checked disabled class="checkbox-inline role-checkboxes-simple-${user.id}" />
				</c:if> <c:if test="${(role.name != rolName1) and (role.name != rolName2) and (role.name != rolName3)}">
			<label class="control-label">${rol} ${i}:&nbsp;</label>
			<input type="checkbox" name="rolesIds" id="" value="${rolName}" class="checkbox-inline role-checkboxes-simple-${user.id}" />
			</c:if> 
			</c:forEach> 
			</td>
			<td>
			
				<label><input id="${user.id}_sexMan" type="radio" name="${user.id}_sex" value="Man" class="form-control" ${user.checkedRadMan} ></input>Man </label>
				<label><input id="${user.id}_sexWoman" type="radio" name="${user.id}_sex" value="Woman" class="form-control" ${user.checkedRadWom}></input>Woman</label>
			
			</td>			
	<td><button id="${user.id}" data-toggle="modal" data-target="#editUserDialog" class="btn btn-default btn-sm user-edit-btn">
			edit&nbsp;&nbsp;<span class="\glyphicon glyphicon-editglyphicon glyphicon-edit\"></span>
		</button></td>
	<td><button id="${user.id}" class="btn btn-default btn-sm user-delete-btn">
			delete&nbsp;&nbsp;<span class="\glyphicon glyphicon-editglyphicon glyphicon-edit\"></span>
		</button></td>
	</tr>
	</c:forEach>
</table>