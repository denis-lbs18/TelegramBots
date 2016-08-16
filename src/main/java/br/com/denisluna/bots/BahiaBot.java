package br.com.denisluna.bots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.denisluna.Telegram.Message;
import br.com.denisluna.Telegram.Usuario;
import br.com.denisluna.utils.DenisUtils;

public class BahiaBot extends Bot {

	public BahiaBot(int chat_id, String nomebot, String token) {
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
			telegram.sendMessage(this.getChat_id(), this.repete(mensagem, usuario));
			return;
		}

		mensagem.setText(DenisUtils.removeAcentos(mensagem.getText()).toUpperCase());

		if (mensagem.getText().contains("ESCREVE DIREITO") && mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add("Tomar no cu!!");
			resposta.add("Eu sou analfabeto funcional");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (mensagem.getText().contains("VEM FAZER BOQUETE")
				&& mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add(usuario.getNome() + ", vem aqui fazer um bola gata, vem...");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (mensagem.getText().contains("COMER") && mensagem.getText().contains("SUA MAE")) {

			resposta.add("Ahahahahahaha... Comer a minha mãe é normal, pior é quem come vc " + usuario.getNome()
					+ "!! Feio e viadao ");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (mensagem.getText().contains("COMER SUA BUNDA DE ACARAJÉ")
				&& mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add("Confirmo, " + usuario.getNome() + ".");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (mensagem.getText().contains(getNomeBot().toUpperCase())
				&& (mensagem.getText().contains("CEARENSE") || mensagem.getText().contains("PARAIBA"))) {

			resposta.add(
					usuario.getNome() + " do cacete... Além do mais é burro!! Sou baiano imbecil de Pinto pequeno");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if ((mensagem.getText().contains("WINDOWS 10") || mensagem.getText().contains("WINDOWS"))
				&& mensagem.getText().contains("BOSTA")) {

			resposta.add(usuario.getNome() + " seu macflag do caralho!!");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (mensagem.getText().contains("NAMORADA") || mensagem.getText().contains("ESPOSA")) {

			resposta.add(usuario.getNome() + ", tua mina?");
			resposta.add("Manda ela p cá!!");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (mensagem.getText().contains("DORMIR") || mensagem.getText().contains("SONO")) {

			resposta.add("To com sono!!! Me fodam");
			resposta.add("Ai no paraíso");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (mensagem.getText().contains("PALMEIRAS") || mensagem.getText().contains("PORCO")) {

			resposta.add("Mesmo cheio de reserva o porcao ganhou...");
			resposta.add("Somos fofas");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (mensagem.getText().contains("VELLEGO") || mensagem.getText().contains("VELHO ESCROTO")) {

			resposta.add("Eu ti amo vellego, venha fazer amor cmigo!!");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (mensagem.getText().contains("QUE?")) {

			resposta.add("QUE O " + mensagem.getText());
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (mensagem.getText().contains("CHUPA") && mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add("Vou chupar é o CARALHO, " + usuario.getNome());
			resposta.add("Não, pera...");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (mensagem.getText().contains("HAHA") || mensagem.getText().contains("HUE")) {

			resposta.add("Ababananadas");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (mensagem.getText().contains("BOT")) {

			resposta.add("Eu não sou um bot, sou baiano imbecil de pinto pequeno!");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if ((mensagem.getText().contains("TA VIVO") || mensagem.getText().contains("CAIU"))
				&& mensagem.getText().contains(getNomeBot().toUpperCase()) && mensagem.getText().contains("?")) {

			resposta.add("Naum seu idiota");
			resposta.add("Eu tava na rede");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (mensagem.getText().contains("CASSIO") || mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add("Aí " + usuario.getNome() + ", meu nome é BAHOA");
			resposta.add("Putz");
			resposta.add("Errei meu próprio nome");
			resposta.add("E burro eh vc seu velho escroto!!");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

			// Mensagens padrão para usuários
		} else if (usuario.getNome().contains("George")) {

			resposta.add("Jordy, eu te amo");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (usuario.getNome().contains("José")) {

			resposta.add("Vá se foder, Zé");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;

		} else if (usuario.getNome().contains("Bruno")) {

			resposta.add("BRUNO, EU TE AMO!");
			telegram.sendMessage(this.getChat_id(), resposta);
			return;
		}
	}

}
