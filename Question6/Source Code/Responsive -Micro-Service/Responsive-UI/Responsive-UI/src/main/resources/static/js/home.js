var officename='';
var geocoder, map, service, infowindow, bounds;

$(document).ready(function(){
	
	 $("#language").multipleSelect({ width: 460, filter: true,placeholder:"Select languages" });
	$( '#feadback-editor' ).ckeditor();

	 $('#dob').daterangepicker({
		    "singleDatePicker": true,
		    "showDropdowns": true,
		    "autoApply": true,
		    "alwaysShowCalendars": true,
		    "opens": "center",
		    "autoUpdateInput": false,
		    "buttonClasses": "btn btn-lg",
		    "maxDate": new Date(),
		}, function(start, end, label) {
				$("#"+$(this)[0].element.attr("id")).val(start.format('MM/DD/YYYY'));		
	});
	 
	 $("#uploadProfilePicFile").change(function() {
		  readURL(this);
		});
});

function aboutKg(obj){
	
	$(".navbar-n a").removeClass("dropdown-active");
	$(".navbar-n div").removeClass("dropdown-active");
	$('#aboutC').addClass("dropdown-active");
	
	$('#my-profile').hide();
	$('#feedback-kg').hide();
	$('#map-kg').hide();
	$('#about-kg').show();
}

function sendUsFeedback(){
	
	$(".navbar-n a").removeClass("dropdown-active");
	$(".navbar-n div").removeClass("dropdown-active");
	$('#feedback').addClass("dropdown-active");
	
	$('#my-profile').hide();
	$('#feedback-kg').show();
	$('#map-kg').hide();
	$('#about-kg').hide();
}

function showMyProfile(){
	
	$(".navbar-n a").removeClass("dropdown-active");
	$(".navbar-n div").removeClass("dropdown-active");
	$('#headlines').addClass("dropdown-active");
	
	$('#my-profile').show();
	$('#map-kg').hide();
	$('#about-kg').hide();
	$('#feedback-kg').hide();
}

function showMap(obj){
	
	$(".navbar-n a").removeClass("dropdown-active");
	$(".navbar-n div").removeClass("dropdown-active");
	$(obj).addClass("dropdown-active");
	
	$('#my-profile').hide();
	$('#map-kg').show();
	$('#about-kg').hide();
	$('#feedback-kg').hide();
	
	var geocoder =  new google.maps.Geocoder();

 	var  formAddress="KGiSL Campus,365, Thudiyalur Rd, Saravanampatti, Coimbatore, Tamil Nadu 641035";
 	officename="KGiSL Campus";

 	geocoder.geocode(
    		{
    	    'address': formAddress},
            function(results, status) {
         if (status == google.maps.GeocoderStatus.OK) {

           success(results[0].geometry.location.lat(),results[0].geometry.location.lng(),formAddress);

           callback(results, status);
         } else {
           alert("Invalid Address " + status);
         }
       });
}
	    
	    function success(lat,lng,formAddress){
			 map = new google.maps.Map(
					    document.getElementById("map_canvas"), {
					        center: new google.maps.LatLng(lat,lng),
					        zoom: 10,
					        mapTypeId: google.maps.MapTypeId.ROADMAP
					    });
					    var request = {
					    query: formAddress
					    }
					    infowindow = new google.maps.InfoWindow();
					    // service = new google.maps.places.PlacesService(map);
					    bounds = new google.maps.LatLngBounds();
					// service.textSearch(request, callback);
		}


		function callback(results, status) {
		  if (status == google.maps.GeocoderStatus.OK) {
		    for (var i = 0; i < results.length; i++) {
		      var place = results[i];
		      var marker = createMarker(results[i]);
		      bounds.extend(marker.getPosition());
		    }
		    // map.setCenter(bounds.getCenter());
		   // map.fitBounds(bounds);
		   map.setZoom(17);
		  }
		}
		function createMarker(place) {
		  var placeLoc = place.geometry.location;

		  var marker = new google.maps.Marker({
		    map: map,
		   icon: markerIcon,
		    animation: google.maps.Animation.DROP,
		    position: placeLoc
		  });

		 // google.maps.event.addListener(marker, 'dblclick', function() {

		   // infowindow.setContent(officename);
		//    infowindow.open(map, marker);
		// });
		  return marker;
		}
var _expCount = 1;
function addExperience(){
	_expCount = _expCount + 1;
	
	$('#exp-div').append('<div class="col-md-12 form-group exp'+_expCount+'"><label class="labels">Experience</label><div class="input-group"><input type="text" name="quant[2]"' + 
					' class="form-control input-number" placeholder="Enter your experience"/><span onclick="removeExperience('+_expCount+')" class="input-group-btn"><button type="button" class="btn btn-danger btn-number" data-type="minus" data-field="quant[2]">' +
					'	<span class="glyphicon glyphicon-minus"></span></button></span></div></div>');
}

function removeExperience(count){
	$('.exp' + count).remove();
}

function triggerFileClick(){
	$('#uploadProfilePicFile').trigger('click'); 
}

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#profile-img').attr('src', e.target.result);
		}

		reader.readAsDataURL(input.files[0]); // convert to base64 string
	}
}

function saveProfileDetails() {

	var status = true;

	if ($('#name').val() == '') {
		$('#name').notify("Please enter your name.", {
			position : "top"
		}, "error");
		status = false;
	}
	if ($('#phoneNumber').val() == '') {
		$('#phoneNumber').notify("Please enter your phone number.", {
			position : "top"
		}, "error");
		status = false;
	}
	if ($('#address').val() == '') {
		$('#address').notify("Please enter your address.", {
			position : "top"
		}, "error");
		status = false;
	}
	if ($('#gender').val() == '') {
		$('#gender').notify("Please choose your gender.", {
			position : "top"
		}, "error");
		status = false;
	}
	if ($('#dob').val() == '') {
		$('#dob').notify("Please enter your dob.", {
			position : "top"
		}, "error");
		status = false;
	}
	if ($('#email').val() == '') {
		$('#email').notify("Please enter your email address.", {
			position : "top"
		}, "error");
		status = false;
	}
	if ($('#language').val() == '') {
		$('#language').notify("Please select languages.", {
			position : "top"
		}, "error");
		status = false;
	}
	if ($('#resumeFile').val() == '') {
		$('#resumeLbl').notify("Please attach your resume.", {
			position : "top"
		}, "error");
		status = false;
	}
	if ($('#uploadProfilePicFile').val() == '') {
		$('#profile-img').notify("Please attach your profile pic.", {
			position : "top"
		}, "error");
		status = false;
	}

	if (!status) {
		return false;
	}
	
	var profileData = {
			"name": $('#name').val(),
			"phoneNumber": $('#phoneNumber').val(),
			"address": $('#address').val(),
			"gender": $('#gender').val(),
			"dob": $('#dob').val(),
			"education": $('#education').val(),
			"emailId": $('#emailId').val(),
			"languages": $('#languages').val(), 
			"experience": $('#experience').val(),
			"platforms": $('#platforms').val(),
			"additionalDetails": $('#additionalDetails').val(),
	}

	var data = new FormData();

	data.append('resume', $('#resumeFile')[0].files[0]);
	data.append('profilePic', $('#uploadProfilePicFile')[0].files[0]);
	
	data.append('user', new Blob([JSON.stringify(profileData)], {
            type: "application/json"
        }));
	

	 fetch(_saveProfileInfoURL, {
         method: 'post',
         body: data
     }).then(function (response) {
         if (response.status !== 200) {
             alert("There was an error!");
         } else {
        	 alert("Saved successfully!");
         }
     }).catch(function (err) {
         alert("There was an error!");
     });;
}

function feedbachSend(){
	
	$.ajax({
		type : "POST",
		url : _sendFeedbackURL,
		data : $('#feadback-editor').val(),
		async : false,
		contentType : 'application/json',
		success : function(response) {
			$('#feadback-editor').val('')
			alert("Thanks for your feedback!!!");
		},
		error : function(e) {
		}
	});
	
}