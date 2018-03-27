package br.com.denisluna.bots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.denisluna.telegrambots.types.Message;
import br.com.denisluna.telegrambots.utils.DenisUtils;

public class LucioBot extends Bot {

	public LucioBot(int chat_id, String nomebot, String token) {
		super(chat_id, nomebot, token);
	}

	@Override
	public void responde(Message mensagem) throws UnirestException, IOException {
		List<String> resposta = new ArrayList<String>();

		if (mensagem.getChat().getType().equals("group")) {
			DenisUtils.gravaUsuarios(mensagem.getChat().getTitle(), mensagem.getUsuarioFrom());
		} else if (mensagem.getUsuarioFrom().getId() != Bot.CHAT_ID_CREATOR) {
			enviaLogUsuarioEstranho(mensagem);
		}

		if (mensagem.getText().startsWith("/")) {
			this.respondeComando(mensagem);
			return;
		}

		mensagem.setText(DenisUtils.removeAcentos(mensagem.getText()).toUpperCase());

		if (mensagem.getText().contains("PORRA") && mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add("Tomá no cu " + mensagem.getUsuarioFrom().getNome());
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("PEDRO")) {

			resposta.add("Dá o cu pro Pedio então, " + mensagem.getUsuarioFrom().getNome());
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().toUpperCase().contains("KLEBER")
				|| mensagem.getUsuarioFrom().getNome().equals("Kléber")) {

			resposta.add("Ae Kebe otario");
			resposta.add("Entra no Destiny pra pegar baú com possível arma exótica ");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("FAUSTAO")) {

			resposta.add("Tá na hora de ir pra casa do coleguinha");
			resposta.add("Mas só na brotheragem, claro");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains(this.getNomeBot().toUpperCase())) {

			resposta.add("Fala, caraio");
			resposta.add("E Pedio, to te deveno quanto ?");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		}
	}

	@Override
	public void respondeComando(Message mensagem)
			throws UnirestException {/** Não executa comando **/
	}

}
