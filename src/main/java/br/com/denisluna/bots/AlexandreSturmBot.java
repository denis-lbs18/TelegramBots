package br.com.denisluna.bots;

import java.util.ArrayList;
import java.util.List;

import br.com.denisluna.telegrambots.types.Message;
import br.com.denisluna.telegrambots.types.MessageEntity;
import br.com.denisluna.telegrambots.utils.PadraoDeTags;

public class AlexandreSturmBot extends Bot {
	public static final String TOKEN = "268994751:AAGQUiHc4FpxlGlLPNdTTUciA6TJ0ifB03U";

	public AlexandreSturmBot(int chatId, String nomebot, String token) {
		super(chatId, nomebot, token);
	}

	@Override
	public void responde(Message mensagem) {
		if (falouComigo(mensagem)) {
			List<String> resposta = new ArrayList<>();
			resposta.add("Mano");
			resposta.add("Inter nas semis");
			resposta.add("Foda...");
			this.telegram.sendMessage(this.getChatId(), resposta);
		}
	}

	private boolean falouComigo(Message mensagem) {
		boolean temNome = (mensagem.getText().trim().toUpperCase().contains(this.nomeBot.toUpperCase()));
		boolean mencao = false;
		if (mensagem.getEntities() != null) {
			for (MessageEntity entity : mensagem.getEntities()) {
				mencao = (entity.getType().equals(PadraoDeTags.TEXT_MENTION))
						|| (entity.getType().equals(PadraoDeTags.MENTION));
			}
		}
		return (temNome || mencao);
	}

	@Override
	public boolean verificaComandosValidos(String texto) {
		// TODO Auto-generated method stub
		return false;
	}

}
