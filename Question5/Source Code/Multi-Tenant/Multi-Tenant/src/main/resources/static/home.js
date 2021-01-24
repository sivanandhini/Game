function saveWebPunch() {
	$.ajax({
		type : "GET",
		url : _saveWebPunchURL,
		async : false,
		contentType : 'application/json',
		success : function(response) {
			$('#alertmsg').modal('show');
		},
		error : function(e) {
			
		}
	});
}