package br.com.denisluna.bots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.denisluna.Telegram.Chat;
import br.com.denisluna.Telegram.Message;
import br.com.denisluna.Telegram.TelegramAPI;
import br.com.denisluna.Telegram.Usuario;

public abstract class Bot {
	/**
	 * (Portuguese/Português) Classe abstrata que permite a criação de qualquer
	 * bot Nesta classe estão todos os métodos base como getters e setters, além
	 * das assinaturas das rotinas básicas como responder, repetir e encaminhar
	 * mensagens.
	 * 
	 * (English) Abstract class that allows the creation of any telegram bot. In
	 * this class we have all the base methods, also all the needed getters and
	 * setters. We also have the signatures of the main methods, like answer,
	 * repeat and forward messages.
	 * 
	 * @param nome
	 *            = nome do bot (bot's name)
	 * @param chat_id
	 *            = chat no qual o bot participa (bot's chat_id)
	 * @param token
	 *            = token recebido pelo @botfather no telegram (bot's telegram
	 *            token)
	 */
	protected int chat_id;
	protected String nomebot;
	protected String token;
	protected TelegramAPI telegram;

	/**
	 * Construtor do bot. Todo bot para ser instanciado, precisa de um id de
	 * chat, nome e token.
	 * 
	 * Bot's constructor. Every bot needs this for being created, it needs a
	 * chat_id, name and token.
	 * 
	 * @param chat_id
	 *            = chat no qual o bot participa (bot's chat_id)
	 * @param nomebot
	 *            = nome do bot (bot's name)
	 * @param token
	 *            = token recebido pelo @botfather no telegram (bot's telegram
	 *            token)
	 * 
	 */
	public Bot(int chat_id, String nomebot, String token) {
		super();
		this.chat_id = chat_id;
		this.nomebot = nomebot;
		this.token = token;
	}

	/**
	 * @return token do bot recebito pelo botfather
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            = token do bot recebito pelo botfather
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @param chat_id
	 *            = id do chat no qual o bot participa
	 */
	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}

	/**
	 * @return chat_id, id do chat no qual o bot participa
	 */
	public int getChat_id() {
		return chat_id;
	}

	/**
	 * @param nome
	 *            atribui o nome do bot
	 */
	public void setNomeBot(String nome) {
		this.nomebot = nome;
	}

	/**
	 * @return nome do bot
	 */
	public String getNomeBot() {
		return nomebot;
	}

	/**
	 * @return the telegram
	 */
	public TelegramAPI getTelegram() {
		return telegram;
	}

	/**
	 * @param telegram
	 *            the telegram to set
	 */
	public void setTelegram(TelegramAPI telegram) {
		this.telegram = telegram;
	}

	/**
	 * Envia resposta para uma mensagem em um determinado grupo ou contato
	 * 
	 * @param mensagem
	 * @param usuario
	 * @param telegram
	 * @throws UnirestException
	 * @throws IOException
	 */
	public abstract void responde(Message mensagem, Usuario usuario) throws UnirestException, IOException;

	/**
	 * Encaminha uma mensagem para um determinado grupo Este método é único para
	 * todos os bots
	 * 
	 * @param mensagem
	 *            = mensagem a ser encaminhada;
	 * @param tipo
	 *            = tipo de chat ("group" ou "private");
	 * @return ArrayList com a resposta a ser encaminhada
	 * @throws UnirestException
	 */
	public void encaminha(Message mensagem, Usuario usuario) throws UnirestException {
		List<String> resposta = new ArrayList<String>();
		List<String> log = new ArrayList<String>();
		log.add("Recebi uma mensagem para ser encaminhada do usuário " + usuario.getNome());

		this.telegram.sendMessage(160440184, log);

		// quebra a string separada pelo traço "-" String[]
		String[] forward = mensagem.getText().split("-");
		String grupo;
		String texto;

		grupo = forward[0].trim();

		try {
			texto = forward[1].trim();
		} catch (Exception e) {
			texto = null;
		}

		if (texto == null) {
			resposta.add("Koeh, faltou o texto, aí. Use a sintaxe: '/fwd grupo-mensagem'!");
			this.telegram.sendMessage(mensagem.chat.getId(), resposta);
		} else if (texto.isEmpty()) {
			resposta.add("Koeh, faltou o texto, aí. Use a sintaxe: '/fwd grupo-mensagem'!");
			this.telegram.sendMessage(mensagem.chat.getId(), resposta);
		}

		// remove /fwd e deixa texto em minúsculo grupo =
		grupo = grupo.substring(5);
		grupo = grupo.toLowerCase();

		if (grupo.isEmpty()) {
			resposta.add("Koeh, faltou o grupo, aí. Use a sintaxe: '/fwd grupo-mensagem'!");
			this.telegram.sendMessage(mensagem.chat.getId(), resposta);
		}

		// -50004620 é o id do chat mypst 3.0 // -141839020 é o
		// id do chat teste int chat = 0;
		int fwdgrupo = 0;
		if (grupo.equals("mypst")) {
			fwdgrupo = -50004620;
		} else if (grupo.equals("teste")) {
			fwdgrupo = -141839020;
		} else if (grupo.equals("swee420"))
			fwdgrupo = -40747241;

		// -50004620 é o id do chat mypst 3.0
		// -141839020 é o id do chat teste

		if (!grupo.isEmpty()) {
			resposta.add(texto);
			this.telegram.sendMessage(fwdgrupo, resposta);

		} else
			return;

	}

	/**
	 * Repete a mensagem do usuário com a tag /rpt
	 * 
	 * @param mensagem
	 *            = mensagem a ser respondida;
	 * @param titulo
	 *            = titulo do chat;
	 * @param tipo
	 *            = tipo de chat ("group" ou "private");
	 * @return um arraylist com as respostas, permitindo várias respostas
	 */
	public List<String> repete(Message mensagem, Usuario usuario) {
		List<String> resposta = new ArrayList<String>();

		// quebra a string separada pelo traço "-" String[]
		String[] repeat = mensagem.getText().split("/rpt ");

		String texto;

		try {
			texto = repeat[1].trim();
		} catch (Exception e) {
			texto = null;
		}

		if (texto == null || texto.isEmpty()) {
			resposta.add("Porra, faltou o texto aí. Use a sintaxe: '/rpt mensagem'!");
			return resposta;
		}

		if (!texto.isEmpty()) {
			resposta.add(texto);
			return resposta;

		} else
			return null;
	}

	public void run(TelegramAPI telegram) throws UnirestException, IOException {
		System.out.println("Iniciando o método run() do bot " + this.getNomeBot() + ", chat_id: " + this.getChat_id());
		int last_update_id = 0; // controle das mensagens processadas
		HttpResponse<JsonNode> response;

		while (true) {
			response = telegram.getUpdates(last_update_id++);

			if (response.getStatus() == 200) {
				JSONArray responses = response.getBody().getObject().getJSONArray("result");

				if (responses.isNull(0)) {
					continue;
				} else {
					last_update_id = responses.getJSONObject(responses.length() - 1).getInt("update_id") + 1;
				}

				for (int i = 0; i < responses.length(); i++) {
					JSONObject message;

					try {
						message = responses.getJSONObject(i).getJSONObject("message");
					} catch (Exception e) {
						continue;
					}
					Usuario usuario = new Usuario(message.getJSONObject("from").getInt("id"),
							message.getJSONObject("from").getString("first_name"));

					if (message.getJSONObject("from").has("last_name"))
						usuario.setSobrenome(message.getJSONObject("from").getString("last_name"));

					if (message.getJSONObject("from").has("username"))
						usuario.setUsername(message.getJSONObject("from").getString("username"));

					int chat_id = message.getJSONObject("chat").getInt("id");
					this.setChat_id(chat_id);

					Message mensagem = new Message(usuario);
					mensagem.populaTipoMensagem(message);

					Chat chat = new Chat(chat_id, message.getJSONObject("chat").getString("type"));

					chat.populaCamposChat(message.getJSONObject("chat"));
					mensagem.setChat(chat);

					this.responde(mensagem, usuario);
				}
			}
		}
	}

	public void enviaLogUsuarioEstranho(int chat_id_creator, Message mensagem) throws UnirestException {
		List<String> resposta = new ArrayList<String>();
		resposta.add("Recebendo mensagem de usuário estranho:\n" + "Id: " + mensagem.getUsuario().getId() + ", Nome: "
				+ mensagem.getUsuario().getUsername());

		if (mensagem.istext()) {
			resposta.add("Texto: " + mensagem.getText());
			this.telegram.sendMessage(chat_id_creator, resposta);
		} else if (mensagem.isPhoto()) {
			this.telegram.sendPhoto(chat_id_creator, mensagem.getFileId(), mensagem.getCaption());
		} else if (mensagem.isVideo()) {
			this.telegram.sendVideo(chat_id_creator, mensagem.getFileId(), mensagem.getCaption());
		} else if (mensagem.isDocument()) {
			this.telegram.sendDocument(chat_id_creator, mensagem.getFileId(), mensagem.getCaption());
		} else if (mensagem.isVoice()) {
			this.telegram.sendVoice(chat_id_creator, mensagem.getFileId());
		} else if (mensagem.isAudio()) {
			this.telegram.sendAudio(chat_id_creator, mensagem.getFileId());
		}
	}

}
