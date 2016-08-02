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
	 * Este método é o formato antigo, que retornava um Json node, permitindo o envio de apenas uma mensagem
	 * Foi alterado para permitir que várias mensagens fossem enviadas, percorrendo um array list
	 */
	// public HttpResponse<JsonNode> sendMessage(Integer chatId, String text)
	// throws UnirestException {
	/*public HttpResponse<JsonNode> sendMessage(Integer chatId, ArrayList<String> text) throws UnirestException {
		System.out.println(text.size());
		for (int i = 0; i <= text.size() - 1; i++) {
			System.out.println(text.get(i));
			return Unirest.post(endpoint + "bot" + token + "/sendMessage").field("chat_id", chatId)
					.field("text", text.get(i)).asJson();
		}
		return null;
	} */
	
	/**
	 * Método que envia a mensagem para o grupo 
	 * @param chatId Id do chat no qual o bot recebeu a mensagem
	 * @param text ArrayList que contém todas as mensagens a serem enviadas
	 * @throws UnirestException
	 */
	
	public void sendMessage(Integer chatId, List<String> text) throws UnirestException {
		if (text == null) return;
		else {
			for (int i = 0; i <= text.size() - 1; i++) {
				if (!text.isEmpty() && text.get(i) != null) {
							
					Unirest.post(endpoint + "bot" + token + "/sendMessage").field("chat_id", chatId)
							.field("text", text.get(i)).asJson();
					
				} else continue;
				
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
					} catch(Exception e){						
						continue;
					}				
					
					user.setId(message.getJSONObject("from").getInt("id"));				
					user.setNome(message.getJSONObject("from").getString("first_name"));
					
					try {						
						user.setSobrenome(message.getJSONObject("from").getString("last_name"));						
					} catch (Exception e) {
						System.out.println("O usuário (id, nome): " + user.getId() + ", " + user.getNome() + " não possui sobrenome.");
					}
					
					try {
						user.setUsername(message.getJSONObject("from").getString("username"));						
					} catch (Exception e) {
						System.out.println("O usuário (id, nome): " + user.getId() + ", " + user.getNome() + " não possui username.");
					}					
						
					int chat_id = message.getJSONObject("chat").getInt("id");
					bot.setChat_id(chat_id);

					/**
					 * Nem toda mensagem recebida é uma string
					 * Muitas mensagens podem ser alteração de título, foto, adição/remoção de usuários
					 * Dentro do try, será atribuído o texto da mensagem
					 * Se for string, segue a lógica do bot
					 * Caso contrário, no momento mensagem recebe null
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
					 * Se a string foi atribuída corretamente, o fluxo do sistema segue normalmente 
					 */
					if (mensagem != null && !mensagem.isEmpty()) {						
						// Atribui o texto da mensagem em uma varável auxiliar em maiúsculo
						aux = DenisUtils.removeAcentos(mensagem).toUpperCase();
										
						// Pega o tipo da conversa ao receber uma mensagem
						// Possíveis tipos: group para grupo, private para privado
						String tipo = message.getJSONObject("chat").getString("type").trim();
	
						if (mensagem.contains("/fwd")) {
														
							List<String> encaminha = bot.encaminha(mensagem, tipo, user);
							sendMessage(bot.getChat_id(), encaminha);
							
						} if (mensagem.contains("/rpt")) {
							
							sendMessage(bot.getChat_id(), bot.repete(mensagem, tipo, user));
	
						} else {
							// Pega título do chat caso seja grupo
							String titulo = "";
							if (tipo.contains("group")) {
								titulo = message.getJSONObject("chat").getString("title");
								DenisUtils.gravaUsuarios(titulo, user);
							}
								
								
													
							sendMessage(bot.getChat_id(), bot.responde(aux, titulo, tipo, user));
						}					
					}
					//Código antigo
					/*
					 * if (tipo.contains("group")) {
					 * 
					 * System.out.println(
					 * "Estou conversando com alguém no grupo " +
					 * message.getJSONObject("chat").getString("title") +
					 * ", chat_id: " + chat_id + ", usuário: " + nome);
					 * 
					 * } else if (tipo.contains("private")) {
					 * 
					 * System.out.println(
					 * "Estou conversando com alguém no privado com o usuário: "
					 * + nome);
					 * 
					 * } // Mensagens padrão para conteúdos da mensagem if
					 * (aux.contains("PORRA PEDRO")) {
					 * 
					 * sendMessage(chat_id, "Mermão"); sendMessage(chat_id,
					 * "Aí, o que é pra fazer?");
					 * 
					 * } else if (aux.contains("BAZOOKA") ||
					 * aux.contains("RPG")) {
					 * 
					 * System.out.println(
					 * "Alguém atirou com a rpg no helicóptero");
					 * sendMessage(chat_id, "KOEH " + nome.toUpperCase() +
					 * ", TU É DOENTE???");
					 * 
					 * } else if (aux.contains("VAMOS JOGAR")) {
					 * 
					 * System.out.println("Alguém me chamou para jogar");
					 * sendMessage(chat_id, "Estou no trabalho, " + nome + ".");
					 * 
					 * } else if (aux.contains("BORA JOGAR GTA") ||
					 * aux.contains("GTA")) { System.out.println(
					 * "Alguém me chamou para jogar GTA"); sendMessage(chat_id,
					 * "Aí " + nome + ", não posso jogar"); sendMessage(chat_id,
					 * "Estou sem o save do meu GTA"); sendMessage(chat_id,
					 * "Serinho");
					 * 
					 * } else if (aux.contains("RONALDINHO")) {
					 * 
					 * System.out.println("Alguém falou do Ronaldinho");
					 * sendMessage(chat_id,
					 * "Mermão, com certeza o Ronaldinho será bom para o Fluminense"
					 * ); sendMessage(chat_id, "Vai ajeitar o time");
					 * sendMessage(chat_id, "Vai direcionar a base");
					 * sendMessage(chat_id, "Servir de exemplo para a molecada"
					 * );
					 * 
					 * } else if (aux.contains("VAI SE FODER") || aux.contains(
					 * "VAI SE FUDER")) {
					 * 
					 * System.out.println("Alguém mandou eu me foder");
					 * sendMessage(chat_id, nome + " está triste.");
					 * 
					 * } else if (aux.contains("NO CU")) {
					 * 
					 * sendMessage(chat_id, "Koeh " + nome +
					 * ", tá de cu raspagem?");
					 * 
					 * } else if (aux.contains("BOM DIA") || aux.contains(
					 * "BOA TARDE") || aux.contains("BOA NOITE")) {
					 * 
					 * System.out.println("Mensagem padrão"); switch (aux) {
					 * case "BOM DIA": sendMessage(chat_id, "Bom dia senhores");
					 * break; case "BOA TARDE": sendMessage(chat_id,
					 * "Boa tarde senhores"); break; case "BOA NOITE":
					 * sendMessage(chat_id, "Boa noite senhores"); break; }
					 * 
					 * Calendar c = Calendar.getInstance(); Date data = new
					 * Date(); c.setTime(data);
					 * 
					 * int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); String
					 * dia_da_semana = "";
					 * 
					 * switch (dayOfWeek) { case 1: dia_da_semana =
					 * "Um bom domingo"; break; case 2: dia_da_semana =
					 * "Uma boa segunda"; break; case 3: dia_da_semana =
					 * "Uma boa terça"; break; case 4: dia_da_semana =
					 * "Uma boa quarta"; break; case 5: dia_da_semana =
					 * "Uma boa quinta"; break; case 6: dia_da_semana =
					 * "Uma boa sexta"; break; case 7: dia_da_semana =
					 * "Um bom sábado"; break; // default: dia_da_semana =
					 * "Uma boa semana";
					 * 
					 * }
					 * 
					 * sendMessage(chat_id, dia_da_semana + " a todos");
					 * 
					 * } else if (aux.contains("FODA-SE PEDRO")) {
					 * 
					 * String[] frases = new String[5]; frases[0] = "Koeh";
					 * frases[1] = "Na moral"; frases[2] = "Sério mesmo";
					 * frases[3] = nome + ", tu é pica, aí!"; frases[4] =
					 * "Serinho";
					 * 
					 * for (int j = 0; j < 5; j++) { sendMessage(chat_id,
					 * frases[j]); }
					 * 
					 * } else if (aux.contains("PEDRO")) {
					 * 
					 * sendMessage(chat_id, "Oi, " + nome);
					 * 
					 * // Mensagens padrão para usuários } else if
					 * (nome.contains("Marcos")) {
					 * 
					 * sendMessage(chat_id, "Barcox, eu te amo");
					 * 
					 * } else if (nome.contains("André")) {
					 * 
					 * System.out.println("Manga"); sendMessage(chat_id,
					 * "Manga, EU TE AMO!");
					 * 
					 * // Mensagens encaminhadas //} else if
					 * (mensagem.contains("/fwd") && tipo != "group") { } else
					 * if (mensagem.contains("/fwd")) {
					 * 
					 * 
					 * // quebra a string separada pelo traço "-" String[]
					 * forward = mensagem.split("-"); String grupo =
					 * forward[0].trim(); String texto = forward[1].trim();
					 * 
					 * // remove /fwd e deixa texto em minúsculo grupo =
					 * grupo.substring(5); grupo = grupo.toLowerCase();
					 * 
					 * System.out.println(grupo); System.out.println(texto);
					 * 
					 * // -50004620 é o id do chat mypst 3.0 // -141839020 é o
					 * id do chat teste int chat = 0;
					 * 
					 * if (grupo.equals("mypst")) { System.out.println("mypst");
					 * chat = -50004620; } else if (grupo.equals("teste")) {
					 * System.out.println("teste"); chat = -141839020; }
					 * 
					 * System.out.println(
					 * "Recebi uma mensagem para ser encaminhada do usuário " +
					 * nome); sendMessage(chat, texto);
					 * 
					 * }
					 */
				}
			}
		}
	}
}