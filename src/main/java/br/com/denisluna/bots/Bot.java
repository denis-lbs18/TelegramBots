package br.com.denisluna.bots;

import java.util.ArrayList;
import java.util.List;

import br.com.denisluna.Telegram.Message;
import br.com.denisluna.Telegram.Usuario;

public abstract class Bot {
	/**
	 * Classe abstrata que permite a criação de qualquer bot Nesta classe estão
	 * todos os métodos base como getters e setters, além das assinaturas das
	 * rotinas básicas como responder, repetir e encaminhar mensagens
	 * 
	 * @param nome
	 *            = nome do bot
	 * @param chat_id
	 *            = chat no qual o bot participa
	 * @param token
	 *            = token recebido pelo @botfather no telegram
	 */
	protected int chat_id;
	protected String nomebot;
	protected String token;

	/**
	 * Construtor do bot Todo bot para ser instanciado, precisa de um id de
	 * chat, nome e token
	 * 
	 * @param chat_id
	 * @param nomebot
	 * @param token
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
	 * Envia resposta para uma mensagem em um determinado grupo ou contato
	 * 
	 * @param mensagem
	 *            = mensagem a ser respondida;
	 * @param titulo
	 *            = titulo do chat;
	 * @param tipo
	 *            = tipo de chat ("group" ou "private");
	 * @return um arraylist com as respostas, permitindo várias respostas
	 */
	public abstract List<String> responde(Message mensagem, String titulo, String tipo, Usuario usuario);

	/**
	 * Encaminha uma mensagem para um determinado grupo Este método é único para
	 * todos os bots
	 * 
	 * @param mensagem
	 *            = mensagem a ser encaminhada;
	 * @param tipo
	 *            = tipo de chat ("group" ou "private");
	 * @return ArrayList com a resposta a ser encaminhada
	 */
	public List<String> encaminha(Message mensagem, String tipo, Usuario usuario) {
		List<String> resposta = new ArrayList<String>();
		System.out.println("Recebi uma mensagem para ser encaminhada do usuário " + usuario.getNome());

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
			return resposta;
		} else if (texto.isEmpty()) {
			resposta.add("Koeh, faltou o texto, aí. Use a sintaxe: '/fwd grupo-mensagem'!");
			return resposta;
		}

		// remove /fwd e deixa texto em minúsculo grupo =
		grupo = grupo.substring(5);
		grupo = grupo.toLowerCase();

		if (grupo.isEmpty()) {
			resposta.add("Koeh, faltou o grupo, aí. Use a sintaxe: '/fwd grupo-mensagem'!");
			return resposta;
		}

		// -50004620 é o id do chat mypst 3.0 // -141839020 é o
		// id do chat teste int chat = 0;

		if (grupo.equals("mypst")) {
			this.chat_id = -50004620;
		} else if (grupo.equals("teste")) {
			this.chat_id = -141839020;
		}

		// -50004620 é o id do chat mypst 3.0
		// -141839020 é o id do chat teste

		if (!grupo.isEmpty()) {
			resposta.add(texto);
			return resposta;

		} else
			return null;

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
	public List<String> repete(Message mensagem, String tipo, Usuario usuario) {
		List<String> resposta = new ArrayList<String>();
		System.out.println("Recebi uma mensagem para ser repetida do usuário " + usuario.getNome());

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

}
