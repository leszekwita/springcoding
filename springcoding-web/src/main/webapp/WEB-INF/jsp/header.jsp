
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@page import="java.util.*, java.lang.*"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>My App</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${path}/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
<link href="${path}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${path}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${path}/resources/css/jquery-ui/jquery-ui-1.10.2.custom.css" />

<link href="${path}/resources/css/springcoding-web.css" rel="stylesheet">
</head>
<body>

	<div class="container" style="height: 10%; padding-top: 20px; font-size: 1.5em">
		<b>MICHAŁ FIRST <font color=green>SPRING</font> PROJECT
		</b>
	</div>