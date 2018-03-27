package br.com.denisluna.telegrambots.utils;

import java.util.List;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public final class TelegramAPIUtils {
	private final static String URL_ENDPOINT = "https://api.telegram.org/bot%1$s/%2$s";
	private final static String SEND_VOICE = "sendVoice", SEND_PHOTO = "sendPhoto", SEND_VIDEO = "sendVideo",
			SEND_DOCUMENT = "sendDocument", SEND_AUDIO = "sendAudio", SEND_MESSAGE = "sendMessage",
			GET_UPTADES = "getUpdates";

	private final String token;

	public TelegramAPIUtils(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
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

	public void sendVoice(int chatId, String fileId) {
		if ((chatId == 0) || (fileId.isEmpty() || fileId == null))
			return;

		String endPoint = String.format(URL_ENDPOINT, this.getToken(), SEND_VOICE);
		try {
			Unirest.post(endPoint).field(PadraoDeTags.CHAT_ID, chatId).field(PadraoDeTags.VOICE, fileId).asJson();
		} catch (UnirestException e) {
			System.out.println("Erro ao enviar voz ao serviço do Telegram.");
			e.printStackTrace();
		}
	}

	public void sendPhoto(int chatId, String fileId, String caption) {
		if ((chatId == 0) || (fileId.isEmpty() || fileId == null))
			return;

		String endPoint = String.format(URL_ENDPOINT, this.getToken(), SEND_PHOTO);
		try {
			Unirest.post(endPoint).field(PadraoDeTags.CHAT_ID, chatId).field(PadraoDeTags.PHOTO, fileId)
					.field(PadraoDeTags.CAPTION, caption).asJson();
		} catch (UnirestException e) {
			System.out.println("Erro ao enviar imagem ao serviço do Telegram.");
			e.printStackTrace();
		}
	}

	public void sendVideo(int chatId, String fileId, String caption) {
		if ((chatId == 0) || (fileId.isEmpty() || fileId == null))
			return;

		String endPoint = String.format(URL_ENDPOINT, this.getToken(), SEND_VIDEO);
		try {
			Unirest.post(endPoint).field(PadraoDeTags.CHAT_ID, chatId).field(PadraoDeTags.VIDEO, fileId)
					.field(PadraoDeTags.CAPTION, caption).asJson();
		} catch (UnirestException e) {
			System.out.println("Erro ao enviar vídeo ao serviço do Telegram.");
			e.printStackTrace();
		}
	}

	public void sendDocument(int chatId, String fileId, String caption) {
		if ((chatId == 0) || (fileId.isEmpty() || fileId == null))
			return;

		String endPoint = String.format(URL_ENDPOINT, this.getToken(), SEND_DOCUMENT);
		try {
			Unirest.post(endPoint).field(PadraoDeTags.CHAT_ID, chatId).field(PadraoDeTags.DOCUMENT, fileId)
					.field(PadraoDeTags.CAPTION, caption).asJson();
		} catch (UnirestException e) {
			System.out.println("Erro ao enviar documento ao serviço do Telegram.");
			e.printStackTrace();
		}
	}

	public void sendAudio(int chatId, String fileId) {
		if ((chatId == 0) || (fileId.isEmpty() || fileId == null))
			return;

		String endPoint = String.format(URL_ENDPOINT, this.getToken(), SEND_AUDIO);
		try {
			Unirest.post(endPoint).field(PadraoDeTags.CHAT_ID, chatId).field(PadraoDeTags.AUDIO, fileId).asJson();
		} catch (UnirestException e) {
			System.out.println("Erro ao enviar audio ao serviço do Telegram.");
			e.printStackTrace();
		}
	}

	public void sendMessage(Integer chatId, List<String> text) {
		if (text == null)
			return;
		else {
			for (int i = 0; i <= text.size() - 1; i++) {
				if (!text.isEmpty() && text.get(i) != null) {
					String endPoint = String.format(URL_ENDPOINT, this.getToken(), SEND_MESSAGE);
					try {
						Unirest.post(endPoint).field("chat_id", chatId).field("text", text.get(i)).asJson();
					} catch (UnirestException e) {
						System.out.println("Erro ao enviar mensagem para o Telegram.");
						e.printStackTrace();
					}
				} else
					continue;
			}
		}
	}

	public void sendMessage(Integer chatId, String text) {
		if (text == null)
			return;
		else {
			String endPoint = String.format(URL_ENDPOINT, this.getToken(), SEND_MESSAGE);
			try {
				Unirest.post(endPoint).field("chat_id", chatId).field("text", text).asJson();
			} catch (UnirestException e) {
				System.out.println("Erro ao enviar mensagem para o Telegram.");
				e.printStackTrace();
			}
		}
	}

	public HttpResponse<JsonNode> getUpdates(Integer offset) {
		String endPoint = String.format(URL_ENDPOINT, this.getToken(), GET_UPTADES);
		HttpResponse<JsonNode> httpResponse = null;
		try {
			httpResponse = Unirest.post(endPoint).field("offset", offset).asJson();
		} catch (UnirestException e) {
			System.out.println("Erro ao receber atualizações do Telegram: Serviço indisponível");
			e.printStackTrace();
		}
		return httpResponse;
	}
}
