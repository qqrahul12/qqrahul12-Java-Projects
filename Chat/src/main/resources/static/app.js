var stompClient = null;
var socket = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#join").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    console.log('Connect function started');
    socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/chat/send', function (greeting) {
            console.log(greeting.body);
            showGreeting(JSON.parse(greeting.body).content);
        });
        
        stompClient.subscribe('/chat/isTyping', function (greeting) {
            console.log(greeting.body);
            showTyping(JSON.parse(greeting.body).content);
        });

        stompClient.subscribe('/chat/newUser', function (greeting) {
            console.log(greeting.body);
            showJoinedName(JSON.parse(greeting.body).content);
        })
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/receive", {}, JSON.stringify({'message': $("#message").val()}));
}

function showGreeting(message) {
    console.log(message);
    $("#chatMessages").append("<tr><td>" + message + "</td></tr>");
    $("#typingUpdates").html("<tr><td>&nbsp;</td></tr>");
}

function showTyping(message) {
	$("#typingUpdates").html("<tr><td>Someone is typing...</td></tr>");
}

function showJoinedName(message) {
    shortName = message;
    $("#members").append("<tr><td>" + message + " just joined</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(async function () {
        console.log('connect button clicked');
        connect();
    });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    
    $("#message").keyup(function (e)  {
    		console.log('user is typing');
    		stompClient.send("/app/isTyping", {}, JSON.stringify({'message': $("#message").val()}));
    	});
    $("#join").click(function (e) {
        stompClient.send('/app/newUserJoined', {}, JSON.stringify({'message': $("#shortName").val()}));
    })
});

