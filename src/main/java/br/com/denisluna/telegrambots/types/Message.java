package br.com.denisluna.telegrambots.types;

import java.util.ArrayList;

public class Message {
	private int messageId;
	private Usuario usuarioFrom;
	private int date;
	private Chat chat;
	private String text;
	private String caption;
	private Audio audio;
	private ArrayList<Photo> photo;
	private Document document;
	private Video video;
	private Voice voice;
	private ArrayList<MessageEntity> entities;
	private String messageType;

	public Message(int messageId, Usuario usuarioFrom, int date, Chat chat) {
		this.messageId = messageId;
		this.usuarioFrom = usuarioFrom;
		this.date = date;
		this.chat = chat;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public Usuario getUsuarioFrom() {
		return usuarioFrom;
	}

	public void setUsuarioFrom(Usuario usuarioFrom) {
		this.usuarioFrom = usuarioFrom;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Audio getAudio() {
		return audio;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public ArrayList<Photo> getPhoto() {
		return photo;
	}

	public void setPhoto(ArrayList<Photo> photo) {
		this.photo = photo;
	}

	public ArrayList<MessageEntity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<MessageEntity> entities) {
		this.entities = entities;
	}
}
