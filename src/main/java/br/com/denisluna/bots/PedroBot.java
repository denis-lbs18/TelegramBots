package br.com.denisluna.bots;

import java.util.ArrayList;
import java.util.List;

import br.com.denisluna.Telegram.Message;
import br.com.denisluna.utils.DenisUtils;
import br.com.denisluna.utils.Usuario;

public class PedroBot extends Bot {

	public PedroBot(int chat_id, String nomebot, String token) {
		super(chat_id, nomebot, token);
		this.chat_id = chat_id;
		this.nomebot = nomebot;
		this.token = token;
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<String> responde(Message mensagem, String titulo, String tipo, Usuario usuario) {
		List<String> resposta = new ArrayList<String>();

		if (tipo.contains("group")) {

			System.out.println("Estou conversando com alguém no grupo " + titulo + ", chat_id: " + chat_id
					+ ", usuário: " + usuario.getNome() + ", " + usuario.getId());

		} else if (tipo.contains("private")) {

			System.out.println("Estou conversando com alguém no privado com o usuário: " + usuario.getNome() + ", "
					+ usuario.getId());

		}

		// Mensagens padrão para conteúdos da mensagem
		if (mensagem.getText().contains("PORRA") && mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add("Mermão");
			resposta.add("Aí, o que é pra fazer?");

		} else if (mensagem.getText().contains("BAZOOKA") || mensagem.getText().contains("RPG")) {

			System.out.println("Alguém atirou com a rpg no helicóptero");
			resposta.add("CARACA " + usuario.getNome().toUpperCase() + ", TU É DOENTE???");

		} else if (mensagem.getText().contains("AFUNDA") && mensagem.getText().contains("BOOST")) {

			System.out.println("Alguém me chamou de afunda");
			resposta.add("Koeh " + usuario.getNome() + ", não afundo não.");
			resposta.add("Quem faz isso é o âncora original aí");
			resposta.add("Tá de cu raspagem, " + usuario.getNome() + "?");

		} else if (mensagem.getText().contains("MERMAO")) {

			resposta.add("Cara");

		} else if (mensagem.getText().contains("CARA")) {

			resposta.add("Sério mesmo");

		} else if (mensagem.getText().contains("SERIO MESMO")) {

			resposta.add("Na Moral");

		} else if (mensagem.getText().contains("NA MORAL")) {

			resposta.add("Papo reto");

		} else if (mensagem.getText().contains("PAPO RETO")) {

			resposta.add("Serinho");

		} else if (mensagem.getText().contains("SERINHO")) {

			resposta.add("Papo 10");

		} else if (mensagem.getText().contains("JAVA")) {

			resposta.add("Mr Loverman, JAVA!");

		} else if (mensagem.getText().contains("PAPO 10")) {

			resposta.add("Mermão");

		} else if (mensagem.getText().contains("KOEH")) {

			resposta.add("Koeh " + usuario.getNome());

		} else if (mensagem.getText().contains("VAMOS JOGAR")) {

			System.out.println("Alguém me chamou para jogar");
			resposta.add("Estou no trabalho, " + usuario.getNome() + ".");

		} else if (mensagem.getText().contains("BORA JOGAR GTA") || mensagem.getText().contains("GTA")) {

			System.out.println("Alguém me chamou para jogar GTA");
			resposta.add("Aí " + usuario.getNome() + ", não posso jogar online");
			resposta.add("Estou sem o save do meu GTA");
			resposta.add("Serinho");

		} else if (mensagem.getText().contains("RONALDINHO")) {

			System.out.println("Alguém falou do Ronaldinho");
			resposta.add("Mermão, com certeza o Ronaldinho será bom para o Fluminense");
			resposta.add("Vai ajeitar o time");
			resposta.add("Vai direcionar a base");
			resposta.add("Servir de exemplo para a molecada");

		} else if ((mensagem.getText().contains("VAI") || mensagem.getText().contains("VA"))
				&& (mensagem.getText().contains("FUDER") || mensagem.getText().contains("FODER"))) {

			System.out.println("Alguém mandou eu me foder");
			resposta.add(usuario.getNome() + " está triste.");

		} else if (mensagem.getText().contains("MARCOS") || mensagem.getText().contains("BARCOS")
				|| mensagem.getText().contains("BARCOX")) {

			resposta.add("Barcox, cadê você?");
			resposta.add("Na moral");
			resposta.add("Tô com saudades");
			resposta.add("Serinho!");

		} else if (mensagem.getText().contains("VISH")) {

			if (mensagem.getText().trim().equals("VISH")) {
				resposta.add("Double Vish");
			} else if (mensagem.getText().contains("DOUBLE")) {
				resposta.add("Triple Vish");
			} else if (mensagem.getText().contains("TRIPLE")) {
				resposta.add("Multi Vish");
			} else
				resposta.add("MO-MO-MO-MO-MO-MONSTER VISH");

		} else if ((mensagem.getText().contains("TA VIVO") || mensagem.getText().contains("CAIU"))
				&& mensagem.getText().contains(getNomeBot().toUpperCase()) && mensagem.getText().contains("?")) {

			resposta.add("Senhores, eu caí...");
			resposta.add("EU CAÍ, EU CAÍ!!");
			resposta.add("...");
			resposta.add("Voltei, senhores.");

		} else if (mensagem.getText().contains("CHUPA") && mensagem.getText().contains(getNomeBot().toUpperCase())) {

			resposta.add("Vou chupar é o CARALHO, " + usuario.getNome());
			resposta.add("Não, pera...");

		} else if (mensagem.getText().contains("BOT")) {

			resposta.add("Koeh " + usuario.getNome() + ", não sou um bot não");
			resposta.add("Sou apenas lovinho");

		} else if (mensagem.getText().contains("NO CU") || mensagem.getText().contains("NO SEU CU")) {

			resposta.add("Koeh " + usuario.getNome() + ", tá de cu raspagem?");

		} else if (mensagem.getText().contains("BOM DIA") || mensagem.getText().contains("BOA TARDE")
				|| mensagem.getText().contains("BOA NOITE")) {

			System.out.println("Mensagem padrão");
			if (mensagem.getText().contains("BOM DIA")) {

				resposta.add("Bom dia senhores");

			} else if (mensagem.getText().contains("BOA TARDE")) {

				resposta.add("Boa tarde senhores");

			} else if (mensagem.getText().contains("BOA NOITE")) {

				resposta.add("Boa noite senhores");

			}

			String dia_da_semana = DenisUtils.retornaDiadasemana();

			resposta.add(dia_da_semana + " a todos");

		} else if ((mensagem.getText().contains("BURRO") || mensagem.getText().contains("SEU BURRO"))
				&& (mensagem.getText().contains(this.getNomeBot().toUpperCase()))) {

			resposta.add("Peço perdão pelo vacilo");

		} else if ((mensagem.getText().contains("FODA-SE") || mensagem.getText().contains("FODA SE"))
				&& (mensagem.getText().contains(this.getNomeBot().toUpperCase()))) {

			resposta.add("Koeh");
			resposta.add("Na moral");
			resposta.add("Sério mesmo");
			resposta.add(usuario.getNome() + ", tu é pica, aí!");
			resposta.add("Serinho");

		} else if (mensagem.getText().contains("MELHOR") || mensagem.getText().contains("ORIGINAL")) {

			resposta.add("Mermão, não é difícil ser melhor que esse carioca aí");
			resposta.add("Sério mesmo");
			resposta.add("Na moral");

		} else if (mensagem.getText().contains("PELO") && mensagem.getText().contains("MENOS")
				&& mensagem.getText().contains("LUCIO") && mensagem.getText().contains("TRANSA")) {

			resposta.add("Só se for com a bunda");

		} else if (mensagem.getText().contains("NAMORADA") || mensagem.getText().contains("MINA")) {

			resposta.add("Koeh " + usuario.getNome() + ", vou casar em 6 meses!");

		} else if (mensagem.getText().contains("LOVINHO") || mensagem.getText().contains("NAMORO")) {

			resposta.add("Aí " + usuario.getNome() + ", eu namoro há " + DenisUtils.retornaMeses("01 03 2016"));

		} else if (mensagem.getText().contains("AGORA") && mensagem.getText().contains("VAI")) {

			resposta.add("Agora vai porra nenhuma, " + usuario.getNome());
			resposta.add("Tu tá de brincadeira comigo mermão??");

		} else if (mensagem.getText().contains("TOP")) {

			resposta.add("🔝🔝🔝👌😂👌");

		} else if (mensagem.getText().contains("LUCIO")) {

			resposta.add("Lúcio é o fracasso em forma de gente, na moral");

		} else if (mensagem.getText().contains("ALLAN")) {

			resposta.add("Guatdar para futuras referenciad");

		} else if (mensagem.getText().trim().equals("OK")) {

			resposta.add("Ok");

		} else if (mensagem.getText().contains(getNomeBot().toUpperCase())) {
			if (mensagem.getText().contains("MORRA")) {
				resposta.add("Aí, posso morrer não " + usuario.getNome() + ", senão minha mãe me mata!");
			} else {

				int i = (int) (Math.random() * 100) % 3;

				String[] texto = new String[3];
				texto[0] = "Oi, ";
				texto[1] = "Koeh, ";
				texto[2] = "Diga, ";
				resposta.add(texto[i] + usuario.getNome());

			}

		} else if (mensagem.isAudio() || mensagem.isVoice()) {

			resposta.add("Koeh, vai ficar mandando áudio toda hora, " + usuario.getNome() + "?");

		} else if (mensagem.isDocument()) {

			resposta.add("Caraca, que documento é esse aí, " + usuario.getNome() + "?");

		} else if (mensagem.isVideo()) {

			resposta.add("Ih rapaz, lá vem putaria!");
			resposta.add("Isso é contra a moral e os bons costumes da familia tradicional, serinho!");

		} else if (mensagem.isLeft_chat_member()) {

			resposta.add("Ih rapaz, olha o ragequitter!");

		} else if (mensagem.isNew_chat_member()) {

			resposta.add("Seja bem vindo, " + mensagem.getUsuario().getNome() + "!");
			resposta.add("Seguem as regras básicas do grupo:");

			resposta.add("1) Spoiler = Ban\n" + "2) Mudar imagem ou título do grupo = Ban\n"
					+ "3) Zoar moderação gratuitamente = Ban\n" + "4) Foto/vídeo de pênis = Ban\n"
					+ "5) Pedofilia = Ban\n" + "6) Votação atingir 10 votos = ban\n"
					+ "7) Errou a pergunta inicial = ban");

		} else if (mensagem.isNew_chat_photo() || mensagem.isNew_chat_title() || mensagem.isDelete_chat_photo()) {

			resposta.add("Tá de cu raspagem, " + usuario.getNome() + "?");
			resposta.add("BAN NESSE FUDIDO!");

		} else if (usuario.getNome().contains("Marcos")) {

			resposta.add("Barcox, eu te amo");

		} else if (usuario.getUsuarioNomeCompleto().equals("Diego Mamelli")) {

			resposta.add("Koeh Mamelli, todo dia um novo 7 a 1?");

		} else if (usuario.getUsuarioNomeCompleto().equals("André Vasconcelos")) {

			resposta.add("Manga, EU TE AMO!");

		} else if (usuario.getNome().equals("Denis") && mensagem.isPhoto()) {

			resposta.add("Caraca " + usuario.getNome() + ", que imagem pica é essa?");

		}

		return resposta;
	}
}
