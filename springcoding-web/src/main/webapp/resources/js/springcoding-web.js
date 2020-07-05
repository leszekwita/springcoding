miesiac = new Array(12)
miesiac[0] = "stycznia "
miesiac[1] = "lutego "
miesiac[2] = "marca "
miesiac[3] = "kwietnia "
miesiac[4] = "maja "
miesiac[5] = "czerwca "
miesiac[6] = "lipca "
miesiac[7] = "sierpnia "
miesiac[8] = "września "
miesiac[9] = "października "
miesiac[10] = "listopada "
miesiac[11] = "grudnia "
dzien = new Array(7)
dzien[0] = "niedziela "
dzien[1] = "poniedziałek "
dzien[2] = "wtorek "
dzien[3] = "środa "
dzien[4] = "czwartek "
dzien[5] = "piątek "
dzien[6] = "sobota "

function podaj_date() {
	var Dzisiaj = new Date()
	var Tygodnia = Dzisiaj.getDay()
	var Miesiac = Dzisiaj.getMonth()
	var Dnia = Dzisiaj.getDate()
	var Rok = Dzisiaj.getFullYear()
	if (Rok <= 99)
		Rok += 1900
	return dzien[Tygodnia] + "," + " " + Dnia + " " + miesiac[Miesiac] + ", "
			+ Rok + "r."
}

var dateValue = podaj_date();
document.write(dateValue);

function wersja() {
	var wersja = navigator.appVersion;
	var nazwa = navigator.appName;
	window.alert("wersja: " + wersja + "\nnazwa: " + nazwa);
}

$("#browserVersion").click(function() {
	wersja();
})

$(function() {
	$("#tabs").tabs();
});

$("#addDocumentFormSubmit").click(function() {

	$("#addDocumentForm").ajaxForm({
		beforeSubmit : function() {

		},
		success : function(data) {
			$("#tabs-1").html(data);
			$("#newDocTab").click();

		},
		error : function(data) {
		},
	});

});
$("#addUserFormSubmit").click(function() {

	$("#addUserForm").ajaxForm({
		beforeSubmit : function() {

		},
		success : function(data) {
			$("#tabs-3").html(data);
			$("#newUserTab").click();
			$("form#addUserForm input[name=firstName]").val("");
		},
		error : function(data) {
		},
	});

});

function isAnySelected(checkboxClass) {
	var checkedAtLeastOne = false;
	$(checkboxClass).each(function() {
		if ($(this).is(":checked")) {
			checkedAtLeastOne = true;
		}
	});

	if (!checkedAtLeastOne) {
		$("#roleValidationError").html(
				"You have to select at least one checkbox");
	} else {
		$("#roleValidationError").html("");
	}

	return checkedAtLeastOne;
}
function dateBirth() {
	var birthDateTwo = new Date($("#birthDateTwo").val());
	var czas = new Date(); // utworzenie nowego obiektu Date

	if (birthDateTwo.getTime() > czas.getTime()) {
		// alert("Nie mogłeś urodzić się w przyszłości");
		$("#birthDateValidationError").html("Your birth date is not valid");
		return false;
	} else {
		$("#birthDateValidationError").html("");
		return true;
	}
}
function dlugosc() {
	var dl = editUserForm.lastName.value;
	if (dl.length > 8) {
		// alert("Masz zbyt długie nazwisko");
		$("#lastNameValidationError").html("Your lastname is not valid");
		return false;
	} else {
		$("#lastNameValidationError").html("");
		return true;
	}

}
function lengthFirstName() {
	var lengthName = editUserForm.firstName.value;
	if (lengthName.length > 8) {
		$("#firstNameValidationError").html("Your firstName is not valid");
		return false;
	} else {
		$("#firstNameValidationError").html("");
		return true;
	}
}

function validate() {
	
	var isLengthOK = dlugosc();

	var isBirthDateOK = dateBirth();
	
	var isOK = isAnySelected(".role-checkbox");
	
	var isFirstNameOk = lengthFirstName();
	
	if (!isLengthOK || !isBirthDateOK || !isOK || !isFirstNameOk) {
		return false;
	}

	return true;

}

function logika() {
	var x = (dlugosc() && dateBirth() && (isOK));
	if (Boolean(x) == "true") {
		alert("true ");
		return true;
	} else {
		alert("Brak");
	}
}

$(document)
		.ready(
				function() {

					function addFileChangeListener() {
						$('input[name=icoFile]')
								.change(
										function(e) {
											readURL(this);

											function readURL(input) {
												if (input.files
														&& input.files[0]) {

													var reader = new FileReader();
													reader.onload = function(e) {
														var d = new Date();
														var millis = d
																.getTime();

														$('#fileForEdit')
																.attr(
																		'src',
																		e.target.result);
													};

													reader
															.readAsDataURL(input.files[0]);
													$("#fileForEdit").show();
												}
											}
										});
					}

					addFileChangeListener();

					$("#updateDocumentFormSubmit")
							.click(
									function() {

										$("#editDocumentForm")
												.ajaxForm(
														{
															beforeSubmit : function() {

															},
															success : function(
																	data) {
																$("#tabs-1")
																		.html(
																				data);
																$(
																		"#editDocumentDialog")
																		.modal(
																				'hide');
																$(
																		'.document-edit-btn')
																		.click(
																				"click.editDocumentHandler",
																				editDocumentHandler);
																$(
																		'.document-delete-btn')
																		.click(
																				"click.deleteDocumentHandler",
																				deleteDocumentHandler);

															},
															error : function(
																	data) {
															},
														});

									});

					$("#updateUserFormSubmit")
							.click(
									function() {

										$("#editUserForm")
												.ajaxForm(
														{
															beforeSubmit : function() {

															},
															success : function(
																	data) {
																$("#tabs-3")
																		.html(
																				data);
																$(
																		"#editUserDialog")
																		.modal(
																				'hide');
																$(
																		'.user-edit-btn')
																		.click(
																				"click.editUserHandler",
																				editUserHandler);
																$(
																		'.user-delete-btn')
																		.click(
																				"click.deleteUserHandler",
																				deleteUserHandler);

															},
															error : function(
																	data) {
															},
														});
									});

					$('.birthDate').datepicker({
						changeMonth : true,
						changeYear : true,
					/*	dateFormat : 'yy-mm-dd'*/
					 dateFormat : 'yy/mm/dd' 
					});
					/*
					 * $('.birthDateTwo').datepicker({ changeMonth : true,
					 * changeYear : true, dateFormat : 'yy-mm-dd' dateFormat :
					 * 'dd/mm/yy' });
					 */
					var editDocumentHandler = function(e) {
						console.log("DocumentType: "
								+ $("#" + this.id + "_type").html());
						console.log("Document id: " + this.id);
						console.log("Document title: "
								+ $("#" + this.id + "_title").html());

						$("form#editDocumentForm input[name=title]").val(
								$("#" + this.id + "_title").html());
						$("form#editDocumentForm input[name=number]").val(
								$("#" + this.id + "_number").html());
						$("form#editDocumentForm input[name=id]").val(this.id);

						$(".attachment-checkbox").prop('checked', false);

						$(".attachement-checkboxes-simple-" + this.id).each(
								function(key, value) {

									var clikedValue = this.value;

									$(".attachment-checkbox").each(
											function(key, value) {

												if (this.value == clikedValue) {
													$(this).prop('checked',
															true);
												}
											});

								});

						$("form#editDocumentForm #documentType").val(
								$("#" + this.id + "_type").text());

					}

					var editUserHandler = function(e) {

						console.log("User id: " + this.id);
						console.log("User firstName: "
								+ $("#" + this.id + "_firstName").html());
						console.log("User lastName: "
								+ $("#" + this.id + "_lastName").html());
						console.log("User email: "
								+ $("#" + this.id + "_email").html());
						console.log("User password: "
								+ $("#" + this.id + "_password").html());
						console.log("User birthDate: "
								+ $("#" + this.id + "_birthDate").html());
						console.log("User active: "
								+ $("#" + this.id + "_active").html());
						console.log("User sex: "
								+ $("#" + this.id + "_sex").html());
						console.log("UserType: "
								+ $("#" + this.id + "_typeUser").html());


						$("form#editUserForm input[name=firstName]").val(
								$("#" + this.id + "_firstName").html());
						$("form#editUserForm input[name=lastName]").val(
								$("#" + this.id + "_lastName").html());
						$("form#editUserForm input[name=email]").val(
								$("#" + this.id + "_email").html());
						$("form#editUserForm input[name=password]").val(
								$("#" + this.id + "_password").html());
						$("form#editUserForm input[name=birthDateText]").val(
								$("#" + this.id + "_birthDate").html());
						$("form#editUserForm input[name=active]").val(
								$("#" + this.id + "_active").html());
						$("form#editUserForm input[name=sex]").val(
								$("#" + this.id + "_sex").html());

						console.log("checkedRadMan: "
								+ $("#" + this.id + "_sexMan").is(":checked"));
						console.log("checkedRadWoman: "
								+ $("#" + this.id + "_sexWoman").is(":checked"));
						
						if($("#" + this.id + "_sexMan").is(":checked")) {
							$("form#editUserForm input[id=_sexMan]").prop('checked', true);
						}
						else {
							$("form#editUserForm input[id=_sexMan]").removeAttr('checked');
						}
						
						
						if($("#" + this.id + "_sexWoman").is(":checked")) {
							$("form#editUserForm input[id=_sexWoman]").prop('checked', true);
						}
						else {
							$("form#editUserForm input[id=_sexWoman]").removeAttr('checked');
						}
						
						
						$("form#editUserForm input[name=id]").val(this.id);

						$("form#editUserForm #userType").val(
								$("#" + this.id + "_typeUser").text());
						$(".role-checkbox").prop('checked', false);

						$(".role-checkboxes-simple-" + this.id).each(
								function(key, value) {

									var clikedValue = this.value;

									$(".role-checkbox").each(
											function(key, value) {

												if (this.value == clikedValue) {
													$(this).prop('checked',
															true);
												}
											});

								});
					}
					var deleteDocumentHandler = function(e) {

						console.log('./deleteDocumentAjax/' + this.id);

						$.post('./deleteDocumentAjax/' + this.id,
								function(data) {

									$("#tabs-1").html(data);
									$('.document-edit-btn').click(
											"click.editDocumentHandler",
											editDocumentHandler);
									$('.document-delete-btn').click(
											"click.deleteDocumentHandler",
											deleteDocumentHandler);
								});
					}

					$('.document-edit-btn').click("click.editDocumentHandler",
							editDocumentHandler);
					$('.document-delete-btn').click(
							"click.deleteDocumentHandler",
							deleteDocumentHandler);

					var deleteUserHandler = function(e) {
						alert("micio");
						console.log('./deleteUserAjax/' + this.id);

						$.post('./deleteUserAjax/' + this.id, function(data) {

							$("#tabs-3").html(data);
							$('.user-edit-btn').click("click.editUserHandler",
									editUserHandler);
							$('.user-delete-btn').click(
									"click.deleteUserHandler",
									deleteUserHandler);
						});
					}
					$('.user-edit-btn').click("click.editUserHandler",
							editUserHandler);
					$('.user-delete-btn').click("click.deleteUserHandler",
							deleteUserHandler);

				});

$(function() {
	$('#browserVersion').on('drag', function() {

		$('<b> Michał </b>').appendTo('#Content');
	});
});
$(function() {
	$('#browserVersion').on('mousemove', function() {
		$('#browserVersion').css('color', 'red');
		$('#browserVersion').css('fontSize', '19px');
	});
});

$(function() {
	$('#test3').on('click', function() {
		var name = 'Witaj ' + $('#test3').val();
		window.alert(name);
	});
});
