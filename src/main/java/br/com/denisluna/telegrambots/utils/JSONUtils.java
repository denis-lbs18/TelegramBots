package br.com.denisluna.telegrambots.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.denisluna.telegrambots.types.Audio;
import br.com.denisluna.telegrambots.types.Chat;
import br.com.denisluna.telegrambots.types.Document;
import br.com.denisluna.telegrambots.types.Message;
import br.com.denisluna.telegrambots.types.Photo;
import br.com.denisluna.telegrambots.types.Usuario;
import br.com.denisluna.telegrambots.types.Video;
import br.com.denisluna.telegrambots.types.Voice;

public class JSONUtils {

	public static Message pegaMessageJSON(JSONObject json) {
		int messageId = pegaCampoIntJSON(json, PadraoDeTags.MESSAGE_ID);
		Usuario user = pegaUsuarioJSON(json);
		int date = pegaCampoIntJSON(json, PadraoDeTags.DATE);
		Chat chat = pegaChatJSON(json);

		Message mensagem = new Message(messageId, user, date, chat);

		setaTipoMensagem(json, mensagem);

		return mensagem;
	}

	private static void setaTipoMensagem(JSONObject json, Message mensagem) {
		if (verificaExisteCampoJSON(json, PadraoDeTags.TEXT)) {
			mensagem.setText(pegaCampoStringJSON(json, PadraoDeTags.TEXT));
			mensagem.setMessageType(PadraoDeTags.TEXT);
		} else if (verificaExisteCampoJSON(json, PadraoDeTags.AUDIO)) {
			mensagem.setAudio(pegaAudioJSON(json));
			mensagem.setMessageType(PadraoDeTags.AUDIO);
		} else if (verificaExisteCampoJSON(json, PadraoDeTags.DOCUMENT)) {
			mensagem.setDocument(pegaDocumentJSON(json));
			mensagem.setMessageType(PadraoDeTags.DOCUMENT);
		} else if (verificaExisteCampoJSON(json, PadraoDeTags.PHOTO)) {
			mensagem.setPhoto(pegaPhotosJSON(json));
			mensagem.setMessageType(PadraoDeTags.PHOTO);
		} else if (verificaExisteCampoJSON(json, PadraoDeTags.VIDEO)) {
			mensagem.setVideo(pegaVideoJSON(json));
			mensagem.setMessageType(PadraoDeTags.VIDEO);
		} else if (verificaExisteCampoJSON(json, PadraoDeTags.VOICE)) {
			mensagem.setVoice(pegaVoiceJSON(json));
			mensagem.setMessageType(PadraoDeTags.VOICE);
		} else if (verificaExisteCampoJSON(json, PadraoDeTags.CAPTION))
			mensagem.setCaption(pegaCampoStringJSON(json, PadraoDeTags.CAPTION));
		else if (verificaExisteCampoJSON(json, PadraoDeTags.NEW_CHAT_MEMBER))
			mensagem.setMessageType(PadraoDeTags.NEW_CHAT_MEMBER);
		else if (verificaExisteCampoJSON(json, PadraoDeTags.NEW_CHAT_MEMBERS))
			mensagem.setMessageType(PadraoDeTags.NEW_CHAT_MEMBERS);
		else if (verificaExisteCampoJSON(json, PadraoDeTags.LEFT_CHAT_MEMBER))
			mensagem.setMessageType(PadraoDeTags.LEFT_CHAT_MEMBER);
		else if (verificaExisteCampoJSON(json, PadraoDeTags.NEW_CHAT_TITLE))
			mensagem.setMessageType(PadraoDeTags.NEW_CHAT_TITLE);
		else if (verificaExisteCampoJSON(json, PadraoDeTags.NEW_CHAT_PHOTO))
			mensagem.setMessageType(PadraoDeTags.NEW_CHAT_PHOTO);
		else if (verificaExisteCampoJSON(json, PadraoDeTags.DELETE_CHAT_PHOTO))
			mensagem.setMessageType(PadraoDeTags.DELETE_CHAT_PHOTO);
		else if (verificaExisteCampoJSON(json, PadraoDeTags.GROUP_CHAT_CREATED))
			mensagem.setMessageType(PadraoDeTags.GROUP_CHAT_CREATED);
	}

	public static Usuario pegaUsuarioJSON(JSONObject json) {
		JSONObject from = json.getJSONObject(PadraoDeTags.FROM);

		int usuarioId = pegaCampoIntJSON(from, PadraoDeTags.ID);
		boolean isBot = pegaCampoBooleanJSON(from, PadraoDeTags.IS_BOT);
		String nome = pegaCampoStringJSON(from, PadraoDeTags.FIRST_NAME);
		String sobreNome = pegaCampoStringJSON(from, PadraoDeTags.LAST_NAME);
		String nomeUsuario = pegaCampoStringJSON(from, PadraoDeTags.USERNAME);

		return new Usuario(usuarioId, isBot, nome, sobreNome, nomeUsuario);
	}

	public static Chat pegaChatJSON(JSONObject json) {
		JSONObject chat = json.getJSONObject(PadraoDeTags.CHAT);

		int id = pegaCampoIntJSON(chat, PadraoDeTags.ID);
		String type = pegaCampoStringJSON(chat, PadraoDeTags.TYPE);
		String title = pegaCampoStringJSON(chat, PadraoDeTags.TITLE);
		String userName = pegaCampoStringJSON(chat, PadraoDeTags.USERNAME);
		String firstName = pegaCampoStringJSON(chat, PadraoDeTags.FIRST_NAME);
		String lastName = pegaCampoStringJSON(chat, PadraoDeTags.LAST_NAME);
		String description = pegaCampoStringJSON(chat, PadraoDeTags.DESCRIPTION);

		return new Chat(id, type, title, userName, firstName, lastName, description);
	}

	public static Audio pegaAudioJSON(JSONObject json) {
		JSONObject audio = json.getJSONObject(PadraoDeTags.AUDIO);

		String fileId = pegaCampoStringJSON(audio, PadraoDeTags.FILE_ID);
		int duration = pegaCampoIntJSON(audio, PadraoDeTags.DURATION);
		String title = pegaCampoStringJSON(audio, PadraoDeTags.TITLE);
		String mimeType = pegaCampoStringJSON(audio, PadraoDeTags.MIME_TYPE);
		int fileSize = pegaCampoIntJSON(audio, PadraoDeTags.FILE_SIZE);

		return new Audio(fileId, duration, title, mimeType, fileSize);
	}

	public static Document pegaDocumentJSON(JSONObject json) {
		JSONObject document = json.getJSONObject(PadraoDeTags.DOCUMENT);

		String fileId = pegaCampoStringJSON(document, PadraoDeTags.FILE_ID);
		String fileName = pegaCampoStringJSON(document, PadraoDeTags.FILE_NAME);
		String mimeType = pegaCampoStringJSON(document, PadraoDeTags.MIME_TYPE);
		int fileSize = pegaCampoIntJSON(document, PadraoDeTags.FILE_SIZE);

		return new Document(fileId, fileName, mimeType, fileSize);
	}

	public static ArrayList<Photo> pegaPhotosJSON(JSONObject json) {
		JSONArray photos = json.getJSONArray(PadraoDeTags.PHOTO);
		ArrayList<Photo> listaPhotos = new ArrayList<Photo>();

		for (int i = 0; i <= photos.length(); i++) {
			JSONObject photo = photos.getJSONObject(i);

			String fileId = pegaCampoStringJSON(photo, PadraoDeTags.FILE_ID);
			int width = pegaCampoIntJSON(photo, PadraoDeTags.WIDTH);
			int heigth = pegaCampoIntJSON(photo, PadraoDeTags.HEIGHT);
			int fileSize = pegaCampoIntJSON(photo, PadraoDeTags.FILE_SIZE);
			listaPhotos.add(new Photo(fileId, width, heigth, fileSize));
		}

		return listaPhotos;
	}

	public static Video pegaVideoJSON(JSONObject json) {
		JSONObject video = json.getJSONObject(PadraoDeTags.VIDEO);

		String fileId = pegaCampoStringJSON(video, PadraoDeTags.FILE_ID);
		int width = pegaCampoIntJSON(video, PadraoDeTags.WIDTH);
		int heigth = pegaCampoIntJSON(video, PadraoDeTags.HEIGHT);
		int duration = pegaCampoIntJSON(video, PadraoDeTags.DURATION);
		String mimeType = pegaCampoStringJSON(video, PadraoDeTags.MIME_TYPE);
		int fileSize = pegaCampoIntJSON(video, PadraoDeTags.FILE_SIZE);

		return new Video(fileId, width, heigth, duration, mimeType, fileSize);
	}

	public static Voice pegaVoiceJSON(JSONObject json) {
		JSONObject voice = json.getJSONObject(PadraoDeTags.VOICE);

		String fileId = pegaCampoStringJSON(voice, PadraoDeTags.FILE_ID);
		int duration = pegaCampoIntJSON(voice, PadraoDeTags.DURATION);
		String mimeType = pegaCampoStringJSON(voice, PadraoDeTags.MIME_TYPE);
		int fileSize = pegaCampoIntJSON(voice, PadraoDeTags.FILE_SIZE);

		return new Voice(fileId, duration, mimeType, fileSize);
	}

	private static int pegaCampoIntJSON(JSONObject objetoJson, String campo) {
		if (verificaExisteCampoJSON(objetoJson, campo))
			return objetoJson.getInt(campo);
		else
			return 0;
	}

	private static String pegaCampoStringJSON(JSONObject objetoJson, String campo) {
		if (verificaExisteCampoJSON(objetoJson, campo))
			return objetoJson.getString(campo);
		else
			return "";
	}

	private static boolean pegaCampoBooleanJSON(JSONObject objetoJson, String campo) {
		if (verificaExisteCampoJSON(objetoJson, campo))
			return objetoJson.getBoolean(campo);
		else
			return false;
	}

	private static boolean verificaExisteCampoJSON(JSONObject objetoJson, String campo) {
		return objetoJson.has(campo);
	}
}
