function validateKey(){
	
	var property = {
			"key": $('#Key-Id').val(),
			"value": $('#Key-Value').val()
	}
	
	$.ajax({
		type : "POST",
		url : _validationURL,
		async : false,
		data: JSON.stringify(property),
		contentType : 'application/json',
		success : function(response) {
			if(response){
				$('#alertMsg').text("Entered value matched with settings.");
				$('#alertBtn').removeClass();
				$('#alertBtn').addClass('btn btn-sm btn-success');
			}else{
				$('#alertMsg').text("Please change your value. Entered value doesn't match with the key settings.");
				$('#alertBtn').removeClass();
				$('#alertBtn').addClass('btn btn-sm btn-danger');
			}
			
			$('#alertmsg').modal('show');
		},
		error : function(e) {
			
		}
	});
}

function changeKeySettings(){
	var key = $('#Key-Id').val();
	
	$('#info-msg').text('');
	$('#Key-Value').val('');
	
	if(key == 'Number'){
		$('#info-msg').text('Allow only numbers');
	}else if(key == 'Text'){
		$('#info-msg').text('Allow only text');
	}else if(key == 'PAN'){
		$('#info-msg').text('It should be ten characters long. The first five characters should be any upper case alphabets.The next four-characters should be any number from 0 to 9. The last(tenth) character should be any upper case alphabet. It should not contain any white spaces.');
	}else if(key == 'PassportCurrency'){
		$('#info-msg').text('It should be eight characters long. The first character should be an upper case alphabet. The next two characters should be a number, but the first character should be any number from 1-9 and the second character should be any number from 0-9. It should be zero or one white space character. The next four characters should be any number from 0-9. The last character should be any number from 1-9.');
	}else if(key == 'Age'){
		$('#info-msg').text('Please enter DOB as date. (DD-MM-YYYY). It should not be greater that current date.');
	}else if(key == 'RestrictSpecialCharacters'){
		$('#info-msg').text('Restricting special characters in the set "[!@#$%^&*()_-+=,.[]{}\"<>%\\$]');
	}else if(key == 'RestrictCharacterRange'){
		$('#info-msg').text('Restricting characters range from (T to Z)');
	}else if(key == 'RestrictNumberRange'){
		$('#info-msg').text('Restricting number range from (5 to 10)');
	}else if(key == 'RestrictDateRange'){
		$('#info-msg').text('(Restricting date range [1900-01-01 to 2099-12-31] )');
	}else if(key == 'SpecialCharacterAlone'){
		$('#info-msg').text('Allow special characters alone like [^[^*|\":<>[\]{}`\\()');
	}else if(key == 'CreditCard'){
		$('#info-msg').text('It should be 13 or 16 digits long, new cards have 16 digits and old cards have 13 digits. It should be starts with 4. If the cards have 13 digits the next twelve digits should be any number between 0-9. If the cards have 16 digits the next fifteen digits should be any number between 0-9. It should not contains any alphabets and special characters.');
	}else if(key == 'PrimeNumber'){
		$('#info-msg').text('Allow only special characters. Prime numbers are numbers that have only 2 factors: 1 and themselves. Examples: (1 2 3 5 7)');
	}
}