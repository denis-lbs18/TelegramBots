package br.com.denisluna.Telegram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.denisluna.bots.Bot;
import br.com.denisluna.utils.DenisUtils;

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

	public void run(Bot bot) throws UnirestException, IOException {
		System.out.println("Iniciando o método run() do bot " + bot.getNomeBot() + ", chat_id: " + bot.getChat_id());
		int last_update_id = 0; // controle das mensagens processadas
		HttpResponse<JsonNode> response;

		while (true) {
			response = getUpdates(last_update_id++);
			if (response.getStatus() == 200) {
				JSONArray responses = response.getBody().getObject().getJSONArray("result");

				if (responses.isNull(0)) {
					continue;
				} else {
					last_update_id = responses.getJSONObject(responses.length() - 1).getInt("update_id") + 1;
				}

				Usuario user = new Usuario();

				for (int i = 0; i < responses.length(); i++) {
					JSONObject message;

					try {
						message = responses.getJSONObject(i).getJSONObject("message");
					} catch (Exception e) {
						continue;
					}

					user.setId(message.getJSONObject("from").getInt("id"));
					user.setNome(message.getJSONObject("from").getString("first_name"));

					try {
						user.setSobrenome(message.getJSONObject("from").getString("last_name"));
					} catch (Exception e) {
						System.out.println("O usuário (id, nome): " + user.getId() + ", " + user.getNome()
								+ " não possui sobrenome.");
					}

					try {
						user.setUsername(message.getJSONObject("from").getString("username"));
					} catch (Exception e) {
						System.out.println("O usuário (id, nome): " + user.getId() + ", " + user.getNome()
								+ " não possui username.");
					}

					int chat_id = message.getJSONObject("chat").getInt("id");
					bot.setChat_id(chat_id);

					/**
					 * Nem toda mensagem.getText() recebida é uma string Muitas
					 * mensagens podem ser alteração de título, foto,
					 * adição/remoção de usuários Dentro do try, será atribuído
					 * o texto da mensagem.getText() Se for string, segue a
					 * lógica do bot Caso contrário, no momento
					 * mensagem.getText() recebe null
					 */
					/*
					 * 
					 * try { mensagem.getText() = message.getString("text"); }
					 * catch (Exception e) {
					 * 
					 * mensagem.getText() = null; }
					 */

					Message mensagem = new Message(user);
					mensagem.populaTipoMensagem(message);

					/**
					 * Se é mensagem.getText() de texto, verifica se contém /rpt
					 * ou /fwd
					 */
					if (mensagem.istext()) {
						/**
						 * Se a mensagem não está vazia
						 */
						if (mensagem.getText() != null && !mensagem.getText().isEmpty()) {
							/**
							 * Pega o tipo da conversa ao receber uma
							 * mensagem.getText() Possíveis tipos: group para
							 * grupo, private para privado
							 */
							String tipo = message.getJSONObject("chat").getString("type").trim();

							if (mensagem.getText().startsWith("/fwd")) {
								List<String> encaminha = bot.encaminha(mensagem, tipo, user);
								sendMessage(bot.getChat_id(), encaminha);
								continue;
							} else if (mensagem.getText().startsWith("/rpt")) {
								sendMessage(bot.getChat_id(), bot.repete(mensagem, tipo, user));
								continue;
							} else if (bot.getNomeBot() == "Pedro"
									&& mensagem.getText().toUpperCase().contains("CU RASPAGEM")
									&& mensagem.getText().contains(bot.getNomeBot())) {
								// sendVoice(bot.getChat_id(),
								// "BQADAQADLQADeB-QCdbrjpcRm964Ag");
								sendVoice(bot.getChat_id(), "AwADAQADMwADeB-QCWkyJBsQccilAg");
								continue;
							}
						}
					}

					/**
					 * Pega título do chat caso seja grupo
					 */
					String titulo = "";
					String tipo = message.getJSONObject("chat").getString("type").trim();

					if (tipo.contains("group")) {
						titulo = message.getJSONObject("chat").getString("title");
						DenisUtils.gravaUsuarios(titulo, user);
					} else if (user.getId() != 160440184) {
						ArrayList<String> forward = new ArrayList<String>();
						forward.add(mensagem.getText());
						sendMessage(160440184, forward);
					}

					mensagem.setText(DenisUtils.removeAcentos(mensagem.getText()).toUpperCase());
					sendMessage(bot.getChat_id(), bot.responde(mensagem, titulo, tipo, user));

				}
			}
		}
	}
}
