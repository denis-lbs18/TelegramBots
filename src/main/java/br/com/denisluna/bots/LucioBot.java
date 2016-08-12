package br.com.denisluna.bots;

import java.util.ArrayList;
import java.util.List;

import br.com.denisluna.Telegram.Message;
import br.com.denisluna.Telegram.Usuario;

public class LucioBot extends Bot {

	public LucioBot(int chat_id, String nomebot, String token) {
		super(chat_id, nomebot, token);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<String> responde(Message mensagem, String titulo, String tipo, Usuario usuario) {
		// TODO Auto-generated method stub
		List<String> resposta = new ArrayList<String>();

		if (tipo.contains("group")) {

			System.out.println("Estou conversando com alguém no grupo " + titulo + ", chat_id: " + chat_id
					+ ", usuário: " + usuario.getNome() + ", " + usuario.getId());

		} else if (tipo.contains("private")) {

			System.out.println("Estou conversando com alguém no privado com o usuário: " + usuario.getNome() + ", "
					+ usuario.getId());

		}
		if (mensagem.getText().contains("PORRA") && mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add("Tomá no cu " + usuario.getNome());

		} else if (mensagem.getText().contains("PEDRO")) {

			resposta.add("Dá o cu pro Pedio então, " + usuario.getNome());

		}

		return resposta;
	}

}
