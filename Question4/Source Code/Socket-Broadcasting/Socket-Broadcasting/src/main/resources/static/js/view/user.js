
$(document).ready(function(){
       $(".chat_close_icon").click(function(){
           $(".chat_on").show(300);
    });
    
       $('.chat-txt-area').show();
		initializeSocket();
		 $(".Layout").toggle();
});

(function(angular) {
	var app = angular.module("chatBotApp", []);
	var socket = null;
	var stompClient = null;

	app.controller("chatBotCtrl", function($scope) {

	});
	
})(angular);

function initializeSocket(){
	socket = new SockJS(_socketURL);
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function () {
        stompClient.subscribe("/user/message/"+ _userId, function (data) {
        	var text=JSON.parse(data.body);
        	
        	  $('#messageContent').append("<li class='pull-left botli'><b> Admin : </b>" + text.message + "</li>");
        });
    });
	
	socket2 = new SockJS(_socketURL);
	stompClient2 = Stomp.over(socket2);
	stompClient2.connect({}, function () {
        stompClient.subscribe("/user/messages/all", function (data) {
        	var text=JSON.parse(data.body);
        	
        	  $('#messageContent').append("<li class='pull-left botli'><b> Admin : </b>" + text.message + "</li>");
        });
    });
}

function sendMessageOnKeyPress(event) {
	if (event.which == 13) {
		sendMessage();
	}
}

function sendMessage() {
	var message = { message : $('#message').val()};
	var userId = $('#userId').val();
	stompClient.send("/app/admin/" + _userId, {
		priority : 9
	}, JSON.stringify(message));
	
	$('#messageContent').append("<li class='pull-right youli'> <b>You :</b> " + $('#message').val() + "</li>");
	$('#message').val('');
}