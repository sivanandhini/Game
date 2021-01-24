var socket = null;
var stompClient = null;
var onlineChatList = new Array();

(function(angular) {
	var app = angular.module("chatBotApp", []);

	app.controller("chatBotCtrl", function($scope) {
	});

	var initializeSocket = function() {
		socket = new SockJS("/admin");
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function() {
			stompClient.subscribe("/user/messages", function(data) {
				var text = JSON.parse(data.body);
				if ($.inArray(text.userId, onlineChatList) > -1) {
					
					if ($("#qnimate" + text.userId).hasClass("popup-box-on")) {
						$('#showMessage' + text.userId).append("<tr><td> User : " + text.message + "</td></tr>");
					}
				} else {
					onlineChatList.push(text.userId);
					$("#chatList").append('<tr><td>User '+text.userId+'</td><td><button type="button" data-toggle="modal" data-target="#edit" data-uid="1" class=""><span onClick=openChatBox("'+ text.userId + '") class="glyphicon glyphicon-pencil"></span></button></td></tr>');
					var chatBoxText = $('#chatDiv').html().replaceAll('{userId}', text.userId);
					$("#addChatBox").append("<th>" + chatBoxText + "</th>");
					$('#showMessage' + text.userId).append("<tr><td> User : " + text.message + "</td></tr>");
				}
			});
		});
	};

	initializeSocket();

})(angular);

function sendMessageOnKeyPress(event, id) {
	if (event.which == 13) {
		sendMessage(id);
	}
}

function sendMessage(userid) {

	var formMessage = {
		message : $('#message' + userid).val()
	};

	stompClient.send("/app/user/" + userid, {
		priority : 9
	}, JSON.stringify(formMessage));
	
	$('#showMessage' + userid).append(
			"<tr><td> You : " + $('#message' + userid).val() + "</td></tr>");

	$('#message' + userid).val("");
}

function openChatBox(userId) {
	$('#qnimate' + userId).addClass('popup-box-on');
}

function removeClass(userid) {
	$('#qnimate' + userid).removeClass('popup-box-on');
}

function showMessageToAll(){
	$('#msgToAll').modal('show');
	$('#allMsgTxt').val('')
}

function sendMessageToAll(){
	var formMessage = {
			message : $('#allMsgTxt').val()
		};

		stompClient.send("/app/user/all", {
			priority : 9
		}, JSON.stringify(formMessage));
		
}