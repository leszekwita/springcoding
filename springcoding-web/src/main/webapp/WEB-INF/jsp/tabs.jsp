<%@ include file="header.jsp"%>
<div class="container">

	<div id="tabs">
		<ul>
			<li><a href="#tabs-1" id="newDocTab">Dokumenty</a></li>
			<li><a href="#tabs-2">* Nowy dokument</a></li>
			<li><a href="#tabs-3" id="newUserTab"> Uzytkownicy</a></li>
			<li><a href="#tabs-4">* Nowy uzytkownik</a></li>
		</ul>

		<div id="tabs-1">
			<%@ include file="show_documents_simple.jsp"%>
		</div>

		<div id="tabs-2">
			<%@ include file="document_simple.jsp"%>		
		</div>
		<div id="tabs-3">
			<%@ include file="show_users_simple.jsp"%>		
		</div>
		<div id="tabs-4">
			<%@ include file="user_simple.jsp"%>		
		</div>

			<%@ include file="document_edit.jsp"%>
<%@ include file="user_edit.jsp"%>
	</div>

</div>



<%@ include file="footer.jsp"%>