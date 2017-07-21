package com.tcs.chat.room;

public class ChatMessage {
	private MessageType messageType;
	private String message;

	private String chatPerson = "";
	private String fromPerson = "";
	private String chatPersonSession = "";

	public void setMessageType(MessageType v) {
		this.messageType = v;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessage(String v) {
		this.message = v;
	}

	public String getMessage() {
		return this.message;
	}
     
	public String getChatPerson() {
		return chatPerson;
	}

	public void setChatPerson(String chatPerson) {
		this.chatPerson = chatPerson;
	}

	public String getFromPerson() {
		return fromPerson;
	}

	public void setFromPerson(String fromPerson) {
		this.fromPerson = fromPerson;
	}

	public String getChatPersonSession() {
		return chatPersonSession;
	}

	public void setChatPersonSession(String chatPersonSession) {
		this.chatPersonSession = chatPersonSession;
	}
}