package br.com.denisluna.Telegram;

import java.util.List;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public final class TelegramAPI {

	private final String endpoint = "https://api.telegram.org/";
	private final String token;

	public TelegramAPI(String token) {
		this.token = token;
	}

	/**
	 * Método que envia a mensagem.getText() para o grupo
	 * 
	 * @param chatId
	 *            Id do chat no qual o bot recebeu a mensagem.getText()
	 * @param text
	 *            ArrayList que contém todas as mensagens a serem enviadas
	 * @throws UnirestException
	 */

	public void sendVoice(int chatId, String fileId) throws UnirestException {
		if (chatId == 0) {
			return;
		}

		if (fileId.isEmpty() || fileId == null) {
			return;
		}

		Unirest.post(endpoint + "bot" + token + "/sendVoice").field("chat_id", chatId).field("voice", fileId).asJson();
	}

	public void sendPhoto(int chatId, String fileId, String caption) throws UnirestException {
		if (chatId == 0) {
			return;
		}

		if (fileId.isEmpty() || fileId == null) {
			return;
		}

		Unirest.post(endpoint + "bot" + token + "/sendPhoto").field("chat_id", chatId).field("photo", fileId)
				.field("caption", caption).asJson();
	}

	public void sendVideo(int chatId, String fileId, String caption) throws UnirestException {
		if (chatId == 0) {
			return;
		}

		if (fileId.isEmpty() || fileId == null) {
			return;
		}

		Unirest.post(endpoint + "bot" + token + "/sendVideo").field("chat_id", chatId).field("video", fileId)
				.field("caption", caption).asJson();
	}

	public void sendDocument(int chatId, String fileId, String caption) throws UnirestException {
		if (chatId == 0) {
			return;
		}

		if (fileId.isEmpty() || fileId == null) {
			return;
		}

		Unirest.post(endpoint + "bot" + token + "/sendDocument").field("chat_id", chatId).field("document", fileId)
				.field("caption", caption).asJson();
	}

	public void sendAudio(int chatId, String fileId) throws UnirestException {
		if (chatId == 0) {
			return;
		}

		if (fileId.isEmpty() || fileId == null) {
			return;
		}

		Unirest.post(endpoint + "bot" + token + "/sendAudio").field("chat_id", chatId).field("audio", fileId).asJson();
	}

	public void sendMessage(Integer chatId, List<String> text) throws UnirestException {
		if (text == null)
			return;
		else {
			for (int i = 0; i <= text.size() - 1; i++) {
				if (!text.isEmpty() && text.get(i) != null) {
					Unirest.post(endpoint + "bot" + token + "/sendMessage").field("chat_id", chatId)
							.field("text", text.get(i)).asJson();
				} else
					continue;
			}
		}
	}

	public HttpResponse<JsonNode> getUpdates(Integer offset) throws UnirestException {
		return Unirest.post(endpoint + "bot" + token + "/getUpdates").field("offset", offset).asJson();
	}
}
