package br.com.denisluna.bots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.denisluna.Telegram.Message;
import br.com.denisluna.Telegram.Usuario;
import br.com.denisluna.utils.DenisUtils;

public class LucioBot extends Bot {

	public LucioBot(int chat_id, String nomebot, String token) {
		super(chat_id, nomebot, token);
	}

	@Override
	public void responde(Message mensagem, Usuario usuario) throws UnirestException, IOException {
		List<String> resposta = new ArrayList<String>();
		int chat_id_creator = 160440184;

		if (mensagem.chat.getType().equals("group")) {
			DenisUtils.gravaUsuarios(mensagem.chat.getTitle(), usuario);
		} else if (usuario.getId() != chat_id_creator) {
			enviaLogUsuarioEstranho(chat_id_creator, mensagem);
			return;
		}

		if (mensagem.getText().startsWith("/fwd")) {

			resposta.add(mensagem.getText());
			this.encaminha(mensagem, usuario);
			return;

		} else if (mensagem.getText().startsWith("/rpt")) {

			resposta.add(mensagem.getText());
			this.telegram.sendMessage(this.getChat_id(), this.repete(mensagem, usuario));
			return;
		}

		mensagem.setText(DenisUtils.removeAcentos(mensagem.getText()).toUpperCase());

		if (mensagem.getText().contains("PORRA") && mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add("Tomá no cu " + usuario.getNome());
			this.telegram.sendMessage(this.getChat_id(), this.repete(mensagem, usuario));
			return;

		} else if (mensagem.getText().contains("PEDRO")) {

			resposta.add("Dá o cu pro Pedio então, " + usuario.getNome());
			this.telegram.sendMessage(this.getChat_id(), this.repete(mensagem, usuario));
			return;

		}
	}

}
