<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>


<div class="container">
	<a href="${path}/document_view?documentName=Invoice">Show Invoice</a> <br />
	<a href="${path}/user_view?userName=Invoice">Show SecondIvoice</a> <br />
	<a href="${path}/user">Add user</a> <br /> <a href="${path}/document">Add
		document</a> <br /> <a href="${path}/show_documents">Show documents</a> <br />
	<a href="${path}/show_users">Show users</a> <br /> <a
		href="${path}/login">Login</a> <br /> <a href="${path}/tabs">Tabs</a>
	<br /> <a href="javascript:void(0);" id="browserVersion">Dane na
		temat przeglądarki.</a> <br />

	<div id="Content" ALIGN="center" DIR="ltr">
		<br />

		<div id="Content">
			<p>
				Podaj imie: <input type="text" id="test3" name="test3">
			</p>
			<br />
		</div>
	</div>

	<%@ include file="footer.jsp"%>