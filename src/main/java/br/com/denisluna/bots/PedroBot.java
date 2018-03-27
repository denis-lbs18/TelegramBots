package br.com.denisluna.bots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.denisluna.telegrambots.types.Message;
import br.com.denisluna.telegrambots.utils.DenisUtils;
import br.com.denisluna.telegrambots.utils.PadraoDeTags;

public class PedroBot extends Bot {

	public PedroBot(int chat_id, String nomebot, String token) {
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

		if (mensagem.getText().contains("PORRA") && mensagem.getText().contains(this.getNomeBot().toUpperCase())) {

			resposta.add("Mermão");
			resposta.add("Aí, o que é pra fazer?");
			this.telegram.sendMessage(this.getChat_id(), resposta);
		} else if (mensagem.getText().contains("LOVINHO") && mensagem.getText().contains("GRANDE")) {
			this.getTelegram().sendPhoto(this.getChat_id(), "AgADAQAD0KcxG4EjAUUhOAXM3dib_ZA39y8ABFiD12zslOracOkAAgI",
					"");
		} else if (mensagem.getText().contains("UHULL")) {
			this.getTelegram().sendPhoto(this.getChat_id(), "AgADAQAD0qcxG8KxCEUeW-pFVoh1jidqDDAABI5qtHMn73P26QQAAgI",
					"");
		} else if (mensagem.getText().contains("BAZOOKA") || mensagem.getText().contains("RPG")) {

			resposta.add("CARACA " + mensagem.getUsuarioFrom().getNome().toUpperCase() + ", TU É DOENTE???");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("AFUNDA") && mensagem.getText().contains("BOOST")) {

			resposta.add("Koeh " + mensagem.getUsuarioFrom().getNome() + ", não afundo não.");
			resposta.add("Quem faz isso é o âncora original aí");
			resposta.add("Tá de cu raspagem, " + mensagem.getUsuarioFrom().getNome() + "?");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("MERMAO")) {

			resposta.add("Cara");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("CARA")) {

			resposta.add("Sério mesmo");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("SERIO MESMO")) {

			resposta.add("Na Moral");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("NA MORAL")) {

			resposta.add("Papo reto");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("PAPO RETO")) {

			resposta.add("Serinho");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("SERINHO")) {

			resposta.add("Papo 10");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("JAVA")) {

			resposta.add("Mr Loverman, JAVA!");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("PAPO 10")) {

			resposta.add("Mermão");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("KOEH")) {

			resposta.add("Koeh " + mensagem.getUsuarioFrom().getNome());
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("VAMOS JOGAR")) {

			resposta.add("Estou no trabalho, " + mensagem.getUsuarioFrom().getNome() + ".");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("BORA JOGAR GTA") || mensagem.getText().contains("GTA")) {

			resposta.add("Aí " + mensagem.getUsuarioFrom().getNome() + ", não posso jogar online");
			resposta.add("Estou sem o save do meu GTA");
			resposta.add("Serinho");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("RONALDINHO")) {

			resposta.add("Mermão, com certeza o Ronaldinho será bom para o Fluminense");
			resposta.add("Vai ajeitar o time");
			resposta.add("Vai direcionar a base");
			resposta.add("Servir de exemplo para a molecada");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if ((mensagem.getText().contains("VAI") || mensagem.getText().contains("VA"))
				&& (mensagem.getText().contains("FUDER") || mensagem.getText().contains("FODER"))) {

			resposta.add(mensagem.getUsuarioFrom().getNome() + " está triste.");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("MARCOS") || mensagem.getText().contains("BARCOS")
				|| mensagem.getText().contains("BARCOX")) {

			resposta.add("Barcox, cadê você");
			resposta.add("Na moral");
			resposta.add("Tô com saudades");
			resposta.add("Serinho!");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().startsWith("VISH")) {

			if (mensagem.getText().trim().equals("VISH")) {
				resposta.add("Double Vish");
			} else if (mensagem.getText().contains("DOUBLE")) {
				resposta.add("Triple Vish");
			} else if (mensagem.getText().contains("TRIPLE")) {
				resposta.add("Multi Vish");
			} else
				resposta.add("MO-MO-MO-MO-MO-MONSTER VISH");

			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if ((mensagem.getText().contains("TA VIVO") || mensagem.getText().contains("CAIU"))
				&& mensagem.getText().contains(this.getNomeBot().toUpperCase()) && mensagem.getText().contains("?")) {

			resposta.add("Senhores, eu caí...");
			resposta.add("EU CAÍ, EU CAÍ!!");
			resposta.add("...");
			resposta.add("Voltei, senhores.");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (((mensagem.getText().contains("BUCETA")) || (mensagem.getText().contains("VAGINA"))
				|| (mensagem.getText().contains("BUÇA")))
				&& (mensagem.getText().contains("CHUPAR")
						&& mensagem.getText().contains(this.getNomeBot().toUpperCase()))) {

			resposta.add("Deu ruim nesse negócio que você falou aí, " + mensagem.getUsuarioFrom().getNome());
			resposta.add("Fiquei uma semana cagando uns 3kg por dia!");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("CHUPA")
				&& mensagem.getText().contains(this.getNomeBot().toUpperCase())) {

			resposta.add("Vou chupar aí o CARALHO, " + mensagem.getUsuarioFrom().getNome());
			resposta.add("Não, pera...");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if ((mensagem.getText().contains("FERIAS")
				&& mensagem.getText().contains(this.getNomeBot().toUpperCase()))) {

			resposta.add("Férias é um negócio muito ruim, " + mensagem.getUsuarioFrom().getNome());
			resposta.add("Tão ruim que quando tirei as minhas, tive que trabalhar aos sábados pra não passar mal.");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("BOT")) {

			resposta.add("Koeh " + mensagem.getUsuarioFrom().getNome() + ", não sou um bot não");
			resposta.add("Sou apenas lovinho");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("NO CU") || mensagem.getText().contains("NO SEU CU")) {

			resposta.add("Koeh " + mensagem.getUsuarioFrom().getNome() + ", tá de cu raspagem?");
			this.telegram.sendVoice(this.getChat_id(), "AwADAQADMwADeB-QCWkyJBsQccilAg");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("BOM DIA") || mensagem.getText().contains("BOA TARDE")
				|| mensagem.getText().contains("BOA NOITE")) {

			if (mensagem.getText().contains("BOM DIA")) {

				resposta.add("Bom dia senhores");

			} else if (mensagem.getText().contains("BOA TARDE")) {

				resposta.add("Boa tarde senhores");

			} else if (mensagem.getText().contains("BOA NOITE")) {

				resposta.add("Boa noite senhores");

			}

			String dia_da_semana = DenisUtils.retornaDiadasemana();

			resposta.add(dia_da_semana + " a todos");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if ((mensagem.getText().contains("BURRO") || mensagem.getText().contains("SEU BURRO"))
				&& (mensagem.getText().contains(this.getNomeBot().toUpperCase()))) {

			resposta.add("Peço perdão pelo vacilo");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if ((mensagem.getText().contains("FODA-SE") || mensagem.getText().contains("FODA SE"))
				&& (mensagem.getText().contains(this.getNomeBot().toUpperCase()))) {

			resposta.add("Koeh");
			resposta.add("Na moral");
			resposta.add("Sério mesmo");
			resposta.add(mensagem.getUsuarioFrom().getNome() + ", tu é pica, aí");
			resposta.add("Serinho");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("MELHOR") || mensagem.getText().contains("ORIGINAL")) {

			resposta.add("Mermão, não é difícil ser melhor que esse carioca aí");
			resposta.add("Sério mesmo");
			resposta.add("Na moral");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("PELO") && mensagem.getText().contains("MENOS")
				&& mensagem.getText().contains("LUCIO") && mensagem.getText().contains("TRANSA")) {

			resposta.add("Só se for com a bunda");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("NAMORADA") || mensagem.getText().contains("MINA")) {

			resposta.add("Koeh " + mensagem.getUsuarioFrom().getNome() + ", vou casar em 6 meses!");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("LOVINHO") || mensagem.getText().contains("NAMORO")) {

			resposta.add("Aí " + mensagem.getUsuarioFrom().getNome() + ", eu namoro há "
					+ DenisUtils.retornaMeses("01 03 2016"));
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("AGORA") && mensagem.getText().contains("VAI")) {

			resposta.add("Agora vai porra nenhuma, " + mensagem.getUsuarioFrom().getNome());
			resposta.add("Tu tá de brincadeira comigo mermão??");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("TOP")) {

			resposta.add("👌😂👌🔝");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("😂")) {

			resposta.add("👌😂👌🔝");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("LUCIO")) {

			resposta.add("Lúcio é o fracasso em forma de gente, na moral");
			this.telegram.sendVoice(this.getChat_id(), "AwADAQADOQADeB-QCXnimkgxz5LEAg");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains("ALLAN")) {

			resposta.add("Guatdar para futuras referenciad");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().trim().equals("OK")) {

			resposta.add("Ok");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getText().contains(this.getNomeBot().toUpperCase())) {

			if (mensagem.getText().contains("MORRA")) {

				resposta.add(
						"Aí, posso morrer não " + mensagem.getUsuarioFrom().getNome() + ", senão minha mãe me mata!");

			} else {

				int i = (int) (Math.random() * 100) % 3;

				String[] texto = new String[3];
				texto[0] = "Oi, ";
				texto[1] = "Koeh, ";
				texto[2] = "Diga, ";
				resposta.add(texto[i] + mensagem.getUsuarioFrom().getNome());

			}

			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getMessageType().equals(PadraoDeTags.AUDIO)
				|| mensagem.getMessageType().equals(PadraoDeTags.VOICE)) {

			resposta.add("Koeh, vai ficar mandando áudio toda hora, " + mensagem.getUsuarioFrom().getNome() + "?");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getMessageType().equals(PadraoDeTags.DOCUMENT)) {

			resposta.add("Caraca, que documento é esse aí, " + mensagem.getUsuarioFrom().getNome() + "?");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getMessageType().equals(PadraoDeTags.VIDEO)) {

			resposta.add("Ih rapaz, lá vem putaria!");
			resposta.add("Isso é contra a moral e os bons costumes da familia tradicional, serinho!");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getMessageType().equals(PadraoDeTags.LEFT_CHAT_MEMBER)) {

			resposta.add("Ih rapaz, olha o ragequitter!");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getMessageType().equals(PadraoDeTags.NEW_CHAT_MEMBER)) {

			resposta.add("Seja bem vindo, " + mensagem.getUsuarioFrom().getNome() + "!");
			resposta.add("Seguem as regras básicas do grupo:");

			resposta.add("1) Spoiler = Ban\n" + "2) Mudar imagem ou título do grupo = Ban\n"
					+ "3) Zoar moderação gratuitamente = Ban\n" + "4) Foto/vídeo de pênis = Ban\n"
					+ "5) Pedofilia = Ban\n" + "6) Votação atingir 10 votos = ban\n"
					+ "7) Errou a pergunta inicial = ban");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getMessageType().equals(PadraoDeTags.NEW_CHAT_PHOTO)
				|| mensagem.getMessageType().equals(PadraoDeTags.NEW_CHAT_TITLE)
				|| mensagem.getMessageType().equals(PadraoDeTags.DELETE_CHAT_PHOTO)) {

			resposta.add("Tá de cu raspagem, " + mensagem.getUsuarioFrom().getNome() + "?");
			resposta.add("BAN NESSE FUDIDO!");
			// "BQADAQADLQADeB-QCdbrjpcRm964Ag");
			this.telegram.sendVoice(this.getChat_id(), "AwADAQADMwADeB-QCWkyJBsQccilAg");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getUsuarioFrom().getNome().contains("Marcos")) {

			resposta.add("Barcox, eu te amo");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getUsuarioFrom().getUsuarioNomeCompleto().equals("Diego Mamelli")) {

			resposta.add("Koeh Mamelli, todo dia um novo 7 a 1?");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getUsuarioFrom().getUsuarioNomeCompleto().equals("AndrÃ© Vasconcelos")) {

			resposta.add("Manga, EU TE AMO!");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getUsuarioFrom().getNome().equals("Denis")
				&& mensagem.getMessageType().equals(PadraoDeTags.PHOTO)) {

			resposta.add("Caraca " + mensagem.getUsuarioFrom().getNome() + ", que imagem pica é essa?");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		} else if (mensagem.getUsuarioFrom().getNome().equals("Fernando")) {

			resposta.add("Fernando");
			resposta.add("Tá sumido");
			this.telegram.sendMessage(this.getChat_id(), resposta);

		}
	}
}
