package br.com.denisluna.bots;

import java.util.ArrayList;
import java.util.List;

import br.com.denisluna.telegrambots.types.Message;
import br.com.denisluna.telegrambots.utils.DenisUtils;

public class BahiaBot extends Bot {

	public BahiaBot(int chat_id, String nomebot, String token) {
		super(chat_id, nomebot, token);
	}

	@Override
	public void responde(Message mensagem) {
		List<String> resposta = new ArrayList<String>();

		if (mensagem.getUsuarioFrom().getId() != Bot.CHAT_ID_CREATOR) {
			enviaLogUsuarioEstranho(mensagem);
			return;
		}

		if (mensagem.getText().startsWith("/")) {
			this.respondeComando(mensagem);
			return;
		}

		mensagem.setText(DenisUtils.removeAcentos(mensagem.getText()).toUpperCase());

		if (mensagem.getText().contains("ESCREVE DIREITO") && mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add("Tomar no cu!!");
			resposta.add("Eu sou analfabeto funcional");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if (mensagem.getText().contains("VEM FAZER BOQUETE")
				&& mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add(mensagem.getUsuarioFrom().getNome() + ", vem aqui fazer um bola gata, vem...");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if (mensagem.getText().contains("COMER") && mensagem.getText().contains("SUA MAE")) {

			resposta.add("Ahahahahahaha... Comer a minha mãe é normal, pior é quem come vc "
					+ mensagem.getUsuarioFrom().getNome() + "!! Feio e viadao ");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if (mensagem.getText().contains("COMER SUA BUNDA DE ACARAJÉ")
				&& mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add("Confirmo, " + mensagem.getUsuarioFrom().getNome() + ".");
			telegram.sendMessage(this.getChatId(), resposta);
			return;

		} else if (mensagem.getText().contains(getNomeBot().toUpperCase())
				&& (mensagem.getText().contains("CEARENSE") || mensagem.getText().contains("PARAIBA"))) {

			resposta.add(mensagem.getUsuarioFrom().getNome()
					+ " do cacete... Além do mais é burro!! Sou baiano imbecil de Pinto pequeno");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if ((mensagem.getText().contains("WINDOWS 10") || mensagem.getText().contains("WINDOWS"))
				&& mensagem.getText().contains("BOSTA")) {

			resposta.add(mensagem.getUsuarioFrom().getNome() + " seu macflag do caralho!!");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if (mensagem.getText().contains("NAMORADA") || mensagem.getText().contains("ESPOSA")) {

			resposta.add(mensagem.getUsuarioFrom().getNome() + ", tua mina?");
			resposta.add("Manda ela p cá!!");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if (mensagem.getText().contains("DORMIR") || mensagem.getText().contains("SONO")) {

			resposta.add("To com sono!!! Me fodam");
			resposta.add("Ai no paraíso");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if (mensagem.getText().contains("PALMEIRAS") || mensagem.getText().contains("PORCO")) {

			resposta.add("Mesmo cheio de reserva o porcao ganhou...");
			resposta.add("Somos fofas");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if (mensagem.getText().contains("VELLEGO") || mensagem.getText().contains("VELHO ESCROTO")) {

			resposta.add("Eu ti amo vellego, venha fazer amor cmigo!!");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if (mensagem.getText().contains("QUE?")) {

			resposta.add("QUE O " + mensagem.getText());
			telegram.sendMessage(this.getChatId(), resposta);

		} else if (mensagem.getText().contains("CHUPA") && mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add("Vou chupar é o CARALHO, " + mensagem.getUsuarioFrom().getNome());
			resposta.add("Não, pera...");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if (mensagem.getText().contains("HAHA") || mensagem.getText().contains("HUE")) {

			resposta.add("Ababananadas");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if (mensagem.getText().contains("BOT")) {

			resposta.add("Eu não sou um bot, sou baiano imbecil de pinto pequeno!");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if ((mensagem.getText().contains("TA VIVO") || mensagem.getText().contains("CAIU"))
				&& mensagem.getText().contains(getNomeBot().toUpperCase()) && mensagem.getText().contains("?")) {

			resposta.add("Naum seu idiota");
			resposta.add("Eu tava na rede");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if (mensagem.getText().contains("CASSIO") || mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add("Aí " + mensagem.getUsuarioFrom().getNome() + ", meu nome é BAHOA");
			resposta.add("Putz");
			resposta.add("Errei meu próprio nome");
			resposta.add("E burro eh vc seu velho escroto!!");
			telegram.sendMessage(this.getChatId(), resposta);

			// Mensagens padrão para usuários
		} else if (mensagem.getUsuarioFrom().getNome().contains("George")) {

			resposta.add("Jordy, eu te amo");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if (mensagem.getUsuarioFrom().getNome().contains("José")) {

			resposta.add("Vá se foder, Zé");
			telegram.sendMessage(this.getChatId(), resposta);

		} else if (mensagem.getUsuarioFrom().getNome().contains("Bruno")) {

			resposta.add("BRUNO, EU TE AMO!");
			telegram.sendMessage(this.getChatId(), resposta);
		}
	}

}
