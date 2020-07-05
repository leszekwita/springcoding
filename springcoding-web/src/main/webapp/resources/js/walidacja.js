var Walidator = {
	ustawKomunikatyWalidacji: function(){
		jQuery.extend(jQuery.validator.messages, {
		    required: "Pole wymagane",
		    remote: "Please fix this field.",
		    email: "Please enter a valid email address.",
		    url: "Please enter a valid URL.",
		    date: "Niepoprawny format daty.",
		    dateISO: "Please enter a valid date (ISO).",
		    number: "Please enter a valid number.",
		    digits: "Wpisz tylko cyfry",
		    creditcard: "Please enter a valid credit card number.",
		    equalTo: "Please enter the same value again.",
		    accept: "Please enter a value with a valid extension.",
		    maxlength: jQuery.validator.format("Maksymalna długość: {0}"),
		    minlength: jQuery.validator.format("Minimalna długość: {0}"),
		    rangelength: jQuery.validator.format("Please enter a value between {0} and {1} characters long."),
		    range: jQuery.validator.format("Please enter a value between {0} and {1}."),
		    max: jQuery.validator.format("Please enter a value less than or equal to {0}."),
		    min: jQuery.validator.format("Please enter a value greater than or equal to {0}.")
		});
	},
	
	dodajWalidatoryIdentyfikatorowLekow: function(){
		$.validator.addMethod("maxLiczbaIdentyfikatorowLekow", function(value, element, arg){
				if(value === null || value === ""){
					return true;
				}
				return value.split(/\r?\n/g).length <= arg;
			}, "Maksymalna liczba identyfikatorów: {0}");
		
		$.validator.addMethod("formatIdentyfikatorowLekow", function(value, element, arg){
				if(value === null || value === ""){
					return true;
				}
				var identyfikatory = value.split(/\r?\n/g);
				for(var i = 0; i <identyfikatory.length; i++){
					if(!(/^\d{13,14}$/g.test(identyfikatory[i]))){
						return false;
					}
				}
				return true;
			}, "Nieprawidłowy format identyfikatora");
	},
	
	dodajWalidatorWymaganegoWyboru: function(){
		$.validator.addMethod("wyborWymagany", function(value, element, arg){
			 	return value !== null && value !== "";
			 }, "Wybor wymagany");
	},
	
	dodajWalidatorRegonu: function(){
		$.validator.addMethod("regon", function(value){
			if(value === null || value === ""){
				return true;
			}
			return /^(\d{9}|\d{14})$/g.test(value);
		}, "Nieprawidłowy format regonu (9 albo 14 cyfr)");
	},
	
	dodajWalidatorPowoduRozpoznania: function(){
		$.validator.addMethod("rozpoznaniePowod", function(value){
			if(value === null || value === ""){
				return true;
			}
			return /^[A-TV-Z][0-9][A-Z0-9](\\.[A-Z0-9]{1,4})?$/g.test(value);
		}, "Nieprawidłowy format powodu rozpoznania");
	},
	
	dodajWalidatorDaty: function(){
		$.validator.addMethod("data", function(value){
			if(value === null || value === ""){
				return true;
			}
			var match = /^(\d{2})\/(\d{2})\/(\d{4})$/g.exec(value);
			if(match !== null){
				var dzien = parseInt(match[1]);
				if(dzien === NaN || dzien < 1 || dzien > 31){
					return false;
				}
				var miesiac = parseInt(match[2]);
				if(miesiac === NaN || miesiac < 1 || miesiac > 12){
					return false;
				}
				var rok = parseInt(match[3]);
				if(rok === NaN){
					return false;
				}
				return true;
			}
			return false;
		}, "Nieprawidłowy format daty");
	},
	
	dodajWalidatorWypelnijJednozPol : function() {
		$.validator.addMethod("wypelnijJednozPol", function(value, element, arg) {

			selector = arg;
			
			var numberFilled = $(selector, element.form).filter(function() {
				return $(this).val();
			}).length;
			 b
			var valid = numberFilled === 1;

			if (!$(element).data('being_validated')) {
				var fields = $(selector, element.form);
				fields.data('being_validated', true);
				fields.valid();
				fields.data('being_validated', false);
			}
			return valid;
		}, "Wypełnij tylko jedno z wymaganych pól.");
	},
	
	dodajWalidatorWymaganeRazemZ : function() {
		$.validator.addMethod("wymaganeRazemZ", function(value, element, arg) {

			var selektor = '#' + arg.replace(/\./g,'\\.');
			var wartoscDrugiegoPola = $(selektor).val();
			return !(wartoscDrugiegoPola !== null && wartoscDrugiegoPola !== '' && (value === null || value === ''));
		}, "Pole wymagane.");
	},
	
	dodajWalidatorPrzynajmniejJednoWypelnione: function(form){
		$('#komunikatBleduPrzynajmniejJednoPole').remove();
		//$(selektor, form).removeClass('error');
		var selektor = ':text:visible';
		var liczbaWypelnionychPol = $(selektor, form).filter(function(){return $(this).val();}).length;
		if(liczbaWypelnionychPol <= 0){
			//$(selektor, form).addClass('error');
			$('#'+form.id).append('<label id="komunikatBleduPrzynajmniejJednoPole" class="error">Wypełnij przyjamniej jedno pole.<label>');
			return;
		}
		form.submit();
	},
	
	init : function(idFormularza, opcje){
		Walidator.ustawKomunikatyWalidacji();
		Walidator.dodajWalidatoryIdentyfikatorowLekow();
		Walidator.dodajWalidatorWymaganegoWyboru();
		Walidator.dodajWalidatorRegonu();
		Walidator.dodajWalidatorPowoduRozpoznania();
		Walidator.dodajWalidatorDaty();
		Walidator.dodajWalidatorWypelnijJednozPol();
		Walidator.dodajWalidatorWymaganeRazemZ();
		
		return $("#"+idFormularza).validate(opcje);
	}
};