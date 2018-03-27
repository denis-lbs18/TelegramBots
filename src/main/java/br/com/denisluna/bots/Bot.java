package br.com.denisluna.bots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.denisluna.telegrambots.types.Message;
import br.com.denisluna.telegrambots.types.Photo;
import br.com.denisluna.telegrambots.utils.DenisUtils;
import br.com.denisluna.telegrambots.utils.JSONUtils;
import br.com.denisluna.telegrambots.utils.PadraoDeTags;
import br.com.denisluna.telegrambots.utils.TelegramAPIUtils;

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
	protected TelegramAPIUtils telegram;
	protected static final int CHAT_ID_CREATOR = 160440184;

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
	public TelegramAPIUtils getTelegram() {
		return telegram;
	}

	/**
	 * @param telegram
	 *            the telegram to set
	 */
	public void setTelegram(TelegramAPIUtils telegram) {
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
	public abstract void responde(Message mensagem) throws UnirestException, IOException;

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
	public void encaminha(Message mensagem) throws UnirestException {
		List<String> resposta = new ArrayList<String>();
		List<String> log = new ArrayList<String>();
		log.add("Recebi uma mensagem para ser encaminhada do usuário " + mensagem.getUsuarioFrom().getNome());

		this.telegram.sendMessage(Bot.CHAT_ID_CREATOR, log);

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
			this.telegram.sendMessage(mensagem.getChat().getId(), resposta);
		} else if (texto.isEmpty()) {
			resposta.add("Koeh, faltou o texto, aí. Use a sintaxe: '/fwd grupo-mensagem'!");
			this.telegram.sendMessage(mensagem.getChat().getId(), resposta);
		}

		// remove /fwd e deixa texto em minúsculo grupo =
		grupo = grupo.substring(5);
		grupo = grupo.toLowerCase();

		if (grupo.isEmpty()) {
			resposta.add("Koeh, faltou o grupo, aí. Use a sintaxe: '/fwd grupo-mensagem'!");
			this.telegram.sendMessage(mensagem.getChat().getId(), resposta);
		}

		// -50004620 é o id do chat mypst 3.0 // -141839020 é o
		// id do chat teste int chat = 0;
		int fwdgrupo = 0;

		if (DenisUtils.isNumeric(grupo)) {
			fwdgrupo = Integer.parseInt(grupo);
		} else if (grupo.equals("mypst")) {
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
	public List<String> repete(Message mensagem) {
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

	public void run(TelegramAPIUtils telegram) throws UnirestException, IOException {
		System.out.println("Iniciando o método run() do bot " + this.getNomeBot() + ", chat_id: " + this.getChat_id());
		int last_update_id = 0; // controle das mensagens processadas
		HttpResponse<JsonNode> response;
		boolean wait = false;
		String command = "";
		Set<String> commandList = new HashSet<String>();

		commandList.add("/voice");
		commandList.add("/curaspagem");
		commandList.add("/getchatid");
		commandList.add("/getchatinfo");
		commandList.add("/rules");
		commandList.add("/cher");

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

					message = responses.getJSONObject(i).getJSONObject(PadraoDeTags.MESSAGE);
					Message mensagem = JSONUtils.pegaMessageJSON(message);

					this.setChat_id(chat_id);

					/**
					 * Verifica se o bot recebeu uma mensagem em um grupo e é um
					 * comando. Em grupos, os comandos do bot possuem a sintaxe
					 * "comando@botusername Caso seja verdadeiro, será efetuado
					 * um tratamento no comando, removendo o @ através do método
					 * removeArrobaComando
					 */
					// if (mensagem.getChat().getType().equals("group") &&
					// mensagem.getText().contains("@")
					// && mensagem.getText().startsWith("/"))
					// mensagem.setCommand(removeArrobaComando(mensagem.getText()));

					/**
					 * Se a mensagem contém os comandos /fwd ou /rpt + texto,
					 * apenas sinaliza a variável wait como false, e segue o
					 * fluxo normal mais abaixo
					 * 
					 * Caso o comando seja apenas /fwd ou /rpt, ou outros
					 * /getchatname, cai no else, atribuindo a variável wait
					 * como true, e atribuindo o comando na variável command.
					 * Ambas serão utilizadas no próximo passo do loop
					 */
					if (mensagem.getText().startsWith("/fwd ") || mensagem.getText().startsWith("/rpt "))
						wait = false;
					else if (mensagem.getText().startsWith("/") && !commandList.contains(mensagem.getText())) {
						wait = true;
						command = mensagem.getText();
						continue;
					}

					/**
					 * Se variável wait = true; é comando e atribuirá o texto do
					 * comando dentro do texto da mensagem.
					 * 
					 * Isso ocorre para que tanto comandos selecionados através
					 * da lista do Telegram quanto comandos digitados + texto do
					 * Parâmetro sejam interpretados pelo bot
					 * 
					 * Caso variável wait = false, apenas responde a mensagem
					 * com o texto original da mensagem.
					 */
					if (wait == true) {
						mensagem.setText(command + " " + mensagem.getText());

						this.responde(mensagem);
						wait = false;
						command = "";

					} else
						this.responde(mensagem);

				}
			}
		}

	}

	public void enviaLogUsuarioEstranho(Message mensagem) {
		List<String> resposta = new ArrayList<String>();
		resposta.add("Bot: " + this.getNomeBot() + "\nRecebendo mensagem de usuário estranho:\n" + "Id: "
				+ mensagem.getUsuarioFrom().getId() + ", Nome: " + mensagem.getUsuarioFrom().getNome() + ", Username: "
				+ mensagem.getUsuarioFrom().getUsername());

		if (mensagem.getMessageType().equals(PadraoDeTags.TEXT)) {
			resposta.add("Texto: " + mensagem.getText());
			this.telegram.sendMessage(Bot.CHAT_ID_CREATOR, resposta);
		} else if (mensagem.getMessageType().equals(PadraoDeTags.PHOTO)) {
			for (Photo photo : mensagem.getPhoto()) {
				this.telegram.sendPhoto(Bot.CHAT_ID_CREATOR, photo.getFileId(), mensagem.getCaption());
			}
		} else if (mensagem.getMessageType().equals(PadraoDeTags.VIDEO)) {
			this.telegram.sendVideo(Bot.CHAT_ID_CREATOR, mensagem.getVideo().getFileId(), mensagem.getCaption());
		} else if (mensagem.getMessageType().equals(PadraoDeTags.DOCUMENT)) {
			this.telegram.sendDocument(Bot.CHAT_ID_CREATOR, mensagem.getDocument().getFileId(), mensagem.getCaption());
		} else if (mensagem.getMessageType().equals(PadraoDeTags.VOICE)) {
			this.telegram.sendVoice(Bot.CHAT_ID_CREATOR, mensagem.getVoice().getFileId());
		} else if (mensagem.getMessageType().equals(PadraoDeTags.AUDIO)) {
			this.telegram.sendAudio(Bot.CHAT_ID_CREATOR, mensagem.getAudio().getFileID());
		}
	}

	public void respondeComando(Message mensagem) throws UnirestException {
		List<String> resposta = new ArrayList<String>();

		if (mensagem.getText().startsWith("/fwd")) {
			resposta.add(mensagem.getText());
			this.encaminha(mensagem);
		} else if (mensagem.getText().startsWith("/rpt")) {
			resposta.add(mensagem.getText());
			this.telegram.sendMessage(this.getChat_id(), this.repete(mensagem));
		} else if (mensagem.getText().startsWith("/getchatid")) {
			resposta.add("Id do chat: " + mensagem.getChat().getId());
			this.telegram.sendMessage(this.getChat_id(), resposta);
		} else if (mensagem.getText().startsWith("/getchatinfo")) {
			if (mensagem.getChat().getType().equals("group")) {
				resposta.add("Nome do chat: " + mensagem.getChat().getTitle() + "\n" + "Tipo de chat: "
						+ mensagem.getChat().getType());
			} else {
				resposta.add("Nome do chat: " + mensagem.getUsuarioFrom().getUsuarioNomeCompleto() + "\n"
						+ "Tipo de chat: " + mensagem.getChat().getType());
			}
			this.telegram.sendMessage(this.getChat_id(), resposta);
		} else if (mensagem.getText().equals("/voice") && this.getNomeBot().equals("Pedro"))
			this.telegram.sendVoice(this.getChat_id(), "AwADAQADNwADeB-QCWCjw6yRwG24Ag");
		else if (mensagem.getText().equals("/curaspagem") && this.getNomeBot().equals("Pedro"))
			this.telegram.sendVoice(this.getChat_id(), "AwADAQADMwADeB-QCWkyJBsQccilAg");

		else if (mensagem.getText().equals("/cher")) {
			resposta.add("Me chama de Cher! ");
			resposta.add("Me chama de Cher! ");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().equals("/rules")
				&& (this.getNomeBot().equals("Pedro") || this.getNomeBot().equals("Lucio"))) {
			resposta.add("Seguem as regras básicas do grupo:");
			resposta.add("1) Spoiler = Ban\n" + "2) Mudar imagem ou título do grupo = Ban\n"
					+ "3) Zoar moderação gratuitamente = Ban\n" + "4) Foto/vídeo de pênis = Ban\n"
					+ "5) Pedofilia = Ban\n" + "6) Votação atingir 10 votos = ban\n"
					+ "7) Errou a pergunta inicial = ban\n" + "8) Gore sem aviso = ban");

			this.telegram.sendMessage(this.getChat_id(), resposta);
		}
	}

}
