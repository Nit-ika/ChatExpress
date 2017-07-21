var fnOpen = function(strName, strDivId) {
	$('body').append("<div id='chatwindow'><ul></ul></div>");
	$("#chatwindow").tabs({
		event : "mouseover"
	}).addClass("ui-tabs-vertical ui-helper-clearfix");

	$("#chatwindow").find("ul").append("<li><a href='#" + strDivId + "'>" + strName + "</a></li>");
	$("#chatwindow li").removeClass("ui-corner-top").addClass("ui-corner-left");

	$("#chatwindow").append("<div id='" + strDivId + "'><h2>Content heading 1</h2></div>");
};

var CreateProxy = function(wsUri) {
	var websocket = null;
	var elements = null;
	var strName = '';
	var strDiv = '';

	var showMsgPanel = function() {
		$("#loginPanel").hide();
		$("#msgPanel").show();
/*		elements.loginPanel.style.display = "none";
		elements.msgPanel.style.display = "block";*/
	//elements.txtMsg.focus();
	};

	var hideMsgPanel = function() {
		$("#msgPanel").hide();
		$("#loginPanel").hide();
//		$("#loginPanel").show();
		$(".popup-box.chat-popup").remove();
		//elements.txtLogin.focus();
	};


	var displayMessage = function(msg) {
		if (elements.msgContainer.childNodes.length === 100) {
			elements.msgContainer.removeChild(elements.msgContainer.childNodes[0]);
		}
                
		var arrSplit = msg.split("^");
		var type = arrSplit[0];
		var name = arrSplit[1];
		var divId = arrSplit[2];
                
		if (type === 'join' && $("#" + divId).length === 0) 
                {
			var div = document.createElement('div');
			
			div.id = divId;
			var popUpId = name + "_" + divId;
                        
			div.className = "members";
			if (strName === name) {
				strDiv = divId;
				div.innerHTML = "<img src='images/online-icon.png'>&nbsp;" + name + " (You)<div class='line'></div>";
			
                }
			else {
                div.innerHTML = "<img src='images/online-icon.png'>&nbsp;<span>" 
                        + name + "</span>&nbsp;<a href='#' title='Click to Chat' onclick=javascript:register_popup('" + popUpId + "','" + name + "','" +  divId +"');><img src='images/chat.png'></a><div class='line'></div>";
			
                }

			elements.msgContainer.appendChild(div);
			elements.msgContainer.scrollTop = elements.msgContainer.scrollHeight;
		}
		if (type === 'left') {
                    
                    $("#" + divId).html("<img src='images/offline-icon.png'>&nbsp;" +name + " Left the Room<div class='line'></div>");
		}
                if(type === 'msg'){
                    
                        divId = name;
			var actMsg  = arrSplit[3];
			var strNames = $("#"+divId+" span").text();
                        
			var popUpId = strNames + "_" + divId;
			register_popup(popUpId,strNames,divId);
			
			var elments = '<p class="bubbleRight"><b>' + strNames + ': </b>' + actMsg + "</p>";
			$("#"+popUpId).find(".popup-messages").append(elments);
			$("#"+popUpId).find(".popup-messages").animate({ scrollTop: $(document).height() }, "slow");
			
		}

	};

	var clearMessage = function() {
		elements.msgContainer.innerHTML = '';
	};

	return {
		login : function() {
                    
			var id = elements.txtLoginID.value.trim().split(' ').join('_');	
			var name = elements.txtLoginName.value.trim().split(' ').join('_');			
			if (name === ''||id==='') {
				return;
			}
                        
                        name = id+"-"+name;
			strName = name;

			// Initiate the socket and set up the events
			if (websocket === null) 
                        {
				websocket = new WebSocket(wsUri);

				websocket.onopen = function() 
                                {
					var message = 
                                        {
						messageType : 'LOGIN',
						message : name,
						chatPerson : '',
						fromPerson : '',
						chatPersonSession : ''
					};
					websocket.send(JSON.stringify(message));
				};
				websocket.onmessage = function(e) 
                                {
					displayMessage(e.data);
					showMsgPanel();
					playSound();
				};
				websocket.onerror = function(e) {};
				websocket.onclose = function(e) {
					websocket = null;
					clearMessage();
					hideMsgPanel();
				};
			}
		},
		sendMessage : function(msg,strTo,strFrom,strToSession) {
			
			//elements.txtMsg.focus();
			if (websocket !== null && websocket.readyState === 1) {
				var input = msg;
				if (input === '') {
					return;
				}

				//elements.txtMsg.value = '';

				var message = {
					messageType : 'MESSAGE',
					message : input,
					chatPerson : strTo,
					fromPerson : strDiv,
					chatPersonSession : strToSession
				};

				// Send a message through the web-socket
				websocket.send(JSON.stringify(message));
			}
		},
		login_keyup : function(e) {
			if (e.keyCode === 13) {
				this.login();
			}
		},
		sendMessage_keyup : function(e) {
			if (e.keyCode === 13) {
				//this.sendMessage();
				alert("Entered");
			}
		},
		logout : function() 
                { 
			if (websocket !== null && websocket.readyState === 1) {
				websocket.close();
			}
		},
		initiate : function(e) {
			elements = e;
		}
	};
};