<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.lang.*"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="${path}/resources/css/default.css" />



<br />
<table id="documents">
	<tr>
		<th>Id</th>
		<th>Icon</th>
		<th>Title</th>
		<th>Number</th>
		<th>DocumentType</th>
		<th>Attachment</th>
		<th colspan="2">Action</th>

	</tr>

	<c:forEach items="${documents}" var="document">

		<tr>

			<td><span id="${document.id}_id"><c:out
						value="${document.id}" /></span></td>
			<td><img name="documentsIcon" src="${document.iconPath}"/></td>						
			<td><span id="${document.id}_title"><c:out
						value="${document.title}" /></span></td>
			<td><span id="${document.id}_number"><c:out
						value="${document.number}" /></span></td>
			<td><span id="${document.id}_type"><c:out
						value="${document.getDocumentType()}" /></span></td>
			<td>
			<c:set var="attachmentsArr" value="${document.attachments}" />
			<c:set var="att" value="Attachment" />				
			<c:set var="attName1" value="${att} 1" />
			<c:set var="attName2" value="${att} 2" />
			<c:set var="attName3" value="${att} 3" />
			<c:forEach var="i" begin="1" end="3">
				<c:set var="attachment" value="${attachmentsArr[i-1]}" />									
				
				<c:if test="${(attachment.name eq attName1) or (attachment.name eq attName2) or (attachment.name eq attName3)}">
					<label class="control-label">${attachment.name}:&nbsp;</label>
					<input type="checkbox" name="attachementsIds" id="${attachment.id}" value="${attachment.name}" checked disabled	
						class="checkbox-inline attachement-checkboxes-simple-${document.id}" />
				</c:if>
				<c:if test="${(attachment.name != attName1) and (attachment.name != attName2) and (attachment.name != attName3)}">
					<label class="control-label">${att} ${i}:&nbsp;</label>
					<input type="checkbox" name="attachementsIds" id="" value="${attName}"
						class="checkbox-inline attachement-checkboxes-simple-${document.id}" disabled/>
				</c:if>				
				
			</c:forEach>
			
			<td><button id="${document.id}" data-toggle="modal"
					data-target="#editDocumentDialog"
					class="btn btn-default btn-sm document-edit-btn">
					edit&nbsp;&nbsp;<span
						class="\glyphicon glyphicon-editglyphicon glyphicon-edit\"></span>
				</button></td>
			<td><button id="${document.id}"
					class="btn btn-default btn-sm document-delete-btn">
					delete&nbsp;&nbsp;<span
						class="\glyphicon glyphicon-editglyphicon glyphicon-edit\"></span>
				</button></td>
		</tr>
	</c:forEach>

</table>