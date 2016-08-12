package br.com.denisluna.Telegram;

import org.json.JSONObject;

import br.com.denisluna.utils.Usuario;

public class Message {
	public Usuario usuario;
	public boolean istext = false;
	public String text = "";
	public boolean audio = false;
	public boolean document = false;
	public boolean photo = false;
	public boolean voice = false;
	public boolean sticker = false;
	public boolean video = false;
	public boolean hascaption = false;
	public String caption = "";
	public boolean location = false;
	public boolean new_chat_member = false;
	public boolean left_chat_member = false;
	public boolean new_chat_title = false;

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the istext
	 */
	public boolean istext() {
		return istext;
	}

	/**
	 * @param istext
	 *            the istext to set
	 */
	public void setIstext(boolean istext) {
		this.istext = istext;
	}

	/**
	 * @return the hascaption
	 */
	public boolean isHascaption() {
		return hascaption;
	}

	/**
	 * @param hascaption
	 *            the hascaption to set
	 */
	public void setHascaption(boolean hascaption) {
		this.hascaption = hascaption;
	}

	public boolean new_chat_photo = false;
	public boolean delete_chat_photo = false;
	public boolean group_chat_created = false;

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the audio
	 */
	public boolean isAudio() {
		return audio;
	}

	/**
	 * @param audio
	 *            the audio to set
	 */
	public void setAudio(boolean audio) {
		this.audio = audio;
	}

	/**
	 * @return the document
	 */
	public boolean isDocument() {
		return document;
	}

	/**
	 * @param document
	 *            the document to set
	 */
	public void setDocument(boolean document) {
		this.document = document;
	}

	/**
	 * @return the photo
	 */
	public boolean isPhoto() {
		return photo;
	}

	/**
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(boolean photo) {
		this.photo = photo;
	}

	/**
	 * @return the voice
	 */
	public boolean isVoice() {
		return voice;
	}

	/**
	 * @param voice
	 *            the voice to set
	 */
	public void setVoice(boolean voice) {
		this.voice = voice;
	}

	/**
	 * @return the sticker
	 */
	public boolean isSticker() {
		return sticker;
	}

	/**
	 * @param sticker
	 *            the sticker to set
	 */
	public void setSticker(boolean sticker) {
		this.sticker = sticker;
	}

	/**
	 * @return the video
	 */
	public boolean isVideo() {
		return video;
	}

	/**
	 * @param video
	 *            the video to set
	 */
	public void setVideo(boolean video) {
		this.video = video;
	}

	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @param caption
	 *            the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @return the location
	 */
	public boolean isLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(boolean location) {
		this.location = location;
	}

	/**
	 * @return the new_chat_member
	 */
	public boolean isNew_chat_member() {
		return new_chat_member;
	}

	/**
	 * @param new_chat_member
	 *            the new_chat_member to set
	 */
	public void setNew_chat_member(boolean new_chat_member) {
		this.new_chat_member = new_chat_member;
	}

	/**
	 * @return the left_chat_member
	 */
	public boolean isLeft_chat_member() {
		return left_chat_member;
	}

	/**
	 * @param left_chat_member
	 *            the left_chat_member to set
	 */
	public void setLeft_chat_member(boolean left_chat_member) {
		this.left_chat_member = left_chat_member;
	}

	/**
	 * @return the new_chat_title
	 */
	public boolean isNew_chat_title() {
		return new_chat_title;
	}

	/**
	 * @param b
	 *            the new_chat_title to set
	 */
	public void setNew_chat_title(boolean b) {
		this.new_chat_title = b;
	}

	/**
	 * @return the new_chat_photo
	 */
	public boolean isNew_chat_photo() {
		return new_chat_photo;
	}

	/**
	 * @param new_chat_photo
	 *            the new_chat_photo to set
	 */
	public void setNew_chat_photo(boolean new_chat_photo) {
		this.new_chat_photo = new_chat_photo;
	}

	/**
	 * @return the delete_chat_photo
	 */
	public boolean isDelete_chat_photo() {
		return delete_chat_photo;
	}

	/**
	 * @param delete_chat_photo
	 *            the delete_chat_photo to set
	 */
	public void setDelete_chat_photo(boolean delete_chat_photo) {
		this.delete_chat_photo = delete_chat_photo;
	}

	/**
	 * @return the group_chat_created
	 */
	public boolean isGroup_chat_created() {
		return group_chat_created;
	}

	/**
	 * @param group_chat_created
	 *            the group_chat_created to set
	 */
	public void setGroup_chat_created(boolean group_chat_created) {
		this.group_chat_created = group_chat_created;
	}

	/**
	 * @param from
	 */
	public Message(Usuario from) {
		super();
		this.usuario = from;
	}

	public void populaTipoMensagem(JSONObject message) {
		/**
		 * Tentativa por texto
		 */
		if (message.has("text")) {
			this.setText(message.getString("text"));
			this.setIstext(true);
		} else {
			this.setPhoto(message.has("photo"));
			this.setDocument(message.has("document"));
			this.setAudio(message.has("audio"));
			this.setSticker(message.has("sticker"));
			this.setVideo(message.has("video"));
			this.setVoice(message.has("voice"));
			this.setLocation(message.has("location"));
			this.setNew_chat_member(message.has("new_chat_member"));
			this.setLeft_chat_member(message.has("left_chat_member"));
			this.setNew_chat_title(message.has("new_chat_title"));
			this.setNew_chat_photo(message.has("new_chat_photo"));
			this.setDelete_chat_photo(message.has("delete_chat_photo"));

			if (this.isAudio() || this.isVideo()) {
				if (message.has("caption")) {
					this.setCaption(message.getString("caption"));
				}

			} else if (this.isNew_chat_member()) {
				Usuario user = new Usuario(message.getJSONObject("new_chat_member").getInt("id"),
						message.getJSONObject("new_chat_member").getString("first_name"));

				this.setUsuario(user);
			}
		}
	}

}
