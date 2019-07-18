package br.com.denisluna.bots;

import java.util.ArrayList;
import java.util.List;

import br.com.denisluna.telegrambots.types.Message;

public class AlexandreSturmBot extends Bot {
	public static final String TOKEN = "268994751:AAGQUiHc4FpxlGlLPNdTTUciA6TJ0ifB03U";

	public AlexandreSturmBot(int chatId, String nomebot, String token) {
		super(chatId, nomebot, token);
	}

	@Override
	public void responde(Message mensagem) {
		List<String> resposta = new ArrayList<String>();
		resposta.add("Mano");
		resposta.add("Inter nas semis");
		resposta.add("Foda...");
		this.telegram.sendMessage(this.getChatId(), resposta);
	}

	@Override
	public boolean verificaComandosValidos(String texto) {
		// TODO Auto-generated method stub
		return false;
	}

}
