package br.com.denisluna.telegrambot;

import com.mashape.unirest.http.HttpResponse;

import br.com.denisluna.bots.Bot;
import br.com.denisluna.utils.*;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public final class TelegramAPI {

	private final String endpoint = "https://api.telegram.org/";
	private final String token;

	public TelegramAPI(String token) {
		this.token = token;
	}

	/**
	 * Método que envia a mensagem para o grupo
	 * 
	 * @param chatId
	 *            Id do chat no qual o bot recebeu a mensagem
	 * @param text
	 *            ArrayList que contém todas as mensagens a serem enviadas
	 * @throws UnirestException
	 */

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

				String mensagem = "";
				String aux;
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
					 * Nem toda mensagem recebida é uma string Muitas mensagens
					 * podem ser alteração de título, foto, adição/remoção de
					 * usuários Dentro do try, será atribuído o texto da
					 * mensagem Se for string, segue a lógica do bot Caso
					 * contrário, no momento mensagem recebe null
					 */
					
					try {						
						mensagem = message.getString("text");
					} catch (Exception e) {
						/**
						 * No momento, não será tratada a exception
						 */
						mensagem = null;
					}

					/**
					 * Se a string foi atribuída corretamente, o fluxo do
					 * sistema segue normalmente
					 */
					if (mensagem != null && !mensagem.isEmpty()) {
						/**
						 * Atribui o texto da mensagem em uma varável auxiliar
						 * em maiúsculo
						 */
						aux = DenisUtils.removeAcentos(mensagem).toUpperCase();

						/**
						 * Pega o tipo da conversa ao receber uma mensagem
						 * Possíveis tipos: group para grupo, private para
						 * privado
						 */
						String tipo = message.getJSONObject("chat").getString("type").trim();

						if (mensagem.contains("/fwd")) {
							List<String> encaminha = bot.encaminha(mensagem, tipo, user);
							sendMessage(bot.getChat_id(), encaminha);
						}

						if (mensagem.contains("/rpt")) {
							sendMessage(bot.getChat_id(), bot.repete(mensagem, tipo, user));
						} else {
							/**
							 * Pega título do chat caso seja grupo
							 */
							String titulo = "";

							if (tipo.contains("group")) {
								titulo = message.getJSONObject("chat").getString("title");
								DenisUtils.gravaUsuarios(titulo, user);
							}

							sendMessage(bot.getChat_id(), bot.responde(aux, titulo, tipo, user));

						}
					}
				}
			}
		}
	}
}