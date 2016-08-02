package br.com.denisluna.bots;

import java.util.*;
import br.com.denisluna.utils.DenisUtils;
import br.com.denisluna.utils.Usuario;
import br.com.denisluna.bots.Bot;

public class PedroBot extends Bot {

	public PedroBot(int chat_id, String nomebot, String token) {
		super(chat_id, nomebot, token);
		this.chat_id = chat_id;
		this.nomebot = nomebot;
		this.token = token;
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<String> responde(String mensagem, String titulo, String tipo, Usuario usuario) {
		List<String> resposta = new ArrayList<String>();

		if (tipo.contains("group")) {

			System.out.println("Estou conversando com alguém no grupo " + titulo + ", chat_id: " + chat_id + ", usuário: " + usuario.getNome() + ", " + usuario.getId());

		} else if (tipo.contains("private")) {

			System.out.println("Estou conversando com alguém no privado com o usuário: " + usuario.getNome() + ", " + usuario.getId());

		}

		// Mensagens padrão para conteúdos da mensagem
		if (mensagem.contains("PORRA") && mensagem.contains(getNomeBot().toUpperCase())) {

			resposta.add("Mermão");
			resposta.add("Aí, o que é pra fazer?");

		} else if (mensagem.contains("BAZOOKA") || mensagem.contains("RPG")) {

			System.out.println("Alguém atirou com a rpg no helicóptero");
			resposta.add("CARACA " + usuario.getNome().toUpperCase() + ", TU É DOENTE???");

		} else if (mensagem.contains("AFUNDA") && mensagem.contains("BOOST")) {

			System.out.println("Alguém me chamou de afunda");
			resposta.add("Koeh " + usuario.getNome() + ", não afundo não.");
			resposta.add("Quem faz isso é o âncora original aí");
			resposta.add("Tá de cu raspagem, " + usuario.getNome() + "?");

		} else if (mensagem.contains("MERMAO")) {

			resposta.add("Cara");

		} else if (mensagem.contains("CARA")) {

			resposta.add("Sério mesmo");

		} else if (mensagem.contains("SERIO MESMO")) {

			resposta.add("Na Moral");

		} else if (mensagem.contains("NA MORAL")) {

			resposta.add("Papo reto");

		} else if (mensagem.contains("PAPO RETO")) {

			resposta.add("Serinho");

		} else if (mensagem.contains("SERINHO")) {

			resposta.add("Papo 10");

		} else if (mensagem.contains("JAVA")) {

			resposta.add("Mr Loverman, JAVA!");

		} else if (mensagem.contains("PAPO 10")) {

			resposta.add("Mermão");

		} else if (mensagem.contains("KOEH")) {

			resposta.add("Koeh " + usuario.getNome());

		} else if (mensagem.contains("VAMOS JOGAR")) {

			System.out.println("Alguém me chamou para jogar");
			resposta.add("Estou no trabalho, " + usuario.getNome() + ".");

		} else if (mensagem.contains("BORA JOGAR GTA") || mensagem.contains("GTA")) {

			System.out.println("Alguém me chamou para jogar GTA");
			resposta.add("Aí " + usuario.getNome() + ", não posso jogar online");
			resposta.add("Estou sem o save do meu GTA");
			resposta.add("Serinho");

		} else if (mensagem.contains("RONALDINHO")) {

			System.out.println("Alguém falou do Ronaldinho");
			resposta.add("Mermão, com certeza o Ronaldinho será bom para o Fluminense");
			resposta.add("Vai ajeitar o time");
			resposta.add("Vai direcionar a base");
			resposta.add("Servir de exemplo para a molecada");

		} else if ((mensagem.contains("VAI") || mensagem.contains("VA"))
				&& (mensagem.contains("FUDER") || mensagem.contains("FODER"))) {

			System.out.println("Alguém mandou eu me foder");
			resposta.add(usuario.getNome() + " está triste.");

		} else if (mensagem.contains("MARCOS") || mensagem.contains("BARCOS") || mensagem.contains("BARCOX")) {

			resposta.add("Barcox, cadê você?");
			resposta.add("Na moral");
			resposta.add("Tô com saudades");
			resposta.add("Serinho!");

		} else if (mensagem.contains("VISH")) {

			if (mensagem.trim().equals("VISH")) {
				resposta.add("Double Vish");
			} else if (mensagem.contains("DOUBLE")) {
				resposta.add("Triple Vish");
			} else if (mensagem.contains("TRIPLE")) {
				resposta.add("Multi Vish");
			} else
				resposta.add("MO-MO-MO-MO-MO-MONSTER VISH");

		} else if ((mensagem.contains("TA VIVO") || mensagem.contains("CAIU"))
				&& mensagem.contains(getNomeBot().toUpperCase()) && mensagem.contains("?")) {

			resposta.add("Senhores, eu caí...");
			resposta.add("EU CAÍ, EU CAÍ!!");
			resposta.add("...");
			resposta.add("Voltei, senhores.");

		} else if (mensagem.contains("CHUPA") && mensagem.contains(getNomeBot().toUpperCase())) {

			resposta.add("Vou chupar é o CARALHO, " + usuario.getNome());
			resposta.add("Não, pera...");

		} else if (mensagem.contains("BOT")) {

			resposta.add("Koeh " + usuario.getNome() + ", não sou um bot não");
			resposta.add("Sou apenas lovinho");

		} else if (mensagem.contains("NO CU") || mensagem.contains("NO SEU CU")) {

			resposta.add("Koeh " + usuario.getNome() + ", tá de cu raspagem?");

		} else if (mensagem.contains("BOM DIA") || mensagem.contains("BOA TARDE") || mensagem.contains("BOA NOITE")) {

			System.out.println("Mensagem padrão");
			if (mensagem.contains("BOM DIA")) {

				resposta.add("Bom dia senhores");

			} else if (mensagem.contains("BOA TARDE")) {

				resposta.add("Boa tarde senhores");

			} else if (mensagem.contains("BOA NOITE")) {

				resposta.add("Boa noite senhores");

			}

			String dia_da_semana = DenisUtils.retornaDiadasemana();

			resposta.add(dia_da_semana + " a todos");

		} else if ((mensagem.contains("BURRO") || mensagem.contains("SEU BURRO"))
				&& (mensagem.contains(this.getNomeBot().toUpperCase()))) {

			resposta.add("Peço perdão pelo vacilo");

		} else if ((mensagem.contains("FODA-SE") || mensagem.contains("FODA SE"))
				&& (mensagem.contains(this.getNomeBot().toUpperCase()))) {

			resposta.add("Koeh");
			resposta.add("Na moral");
			resposta.add("Sério mesmo");
			resposta.add(usuario.getNome() + ", tu é pica, aí!");
			resposta.add("Serinho");

		} else if (mensagem.contains("MELHOR") || mensagem.contains("ORIGINAL")) {

			resposta.add("Mermão, não é difícil ser melhor que esse carioca aí");
			resposta.add("Sério mesmo");
			resposta.add("Na moral");

		} else if (mensagem.contains("PELO") && mensagem.contains("MENOS") && mensagem.contains("LUCIO")
				&& mensagem.contains("TRANSA")) {

			resposta.add("Só se for com a bunda");

		} else if (mensagem.contains("NAMORADA") || mensagem.contains("MINA")) {

			resposta.add("Koeh " + usuario.getNome() + ", vou casar em 6 meses!");

		} else if (mensagem.contains("LOVINHO") || mensagem.contains("NAMORO")) {

			resposta.add("Aí " + usuario.getNome() + ", eu namoro há " + DenisUtils.retornaMeses("01 03 2016"));

		} else if (mensagem.contains("AGORA") && mensagem.contains("VAI")) {

			resposta.add("Agora vai porra nenhuma, " + usuario.getNome());
			resposta.add("Tu tá de brincadeira comigo mermão??");

		} else if (mensagem.contains("TOP")) {

			resposta.add("🔝🔝🔝👌😂👌");

		} else if (mensagem.contains("LUCIO")) {

			resposta.add("Lúcio é o fracasso em forma de gente, na moral");

		} else if (mensagem.contains("ALLAN")) {

			resposta.add("Guatdar para futuras referenciad");

		} else if (mensagem.trim().equals("OK")) {

			resposta.add("Ok");

		} else if (mensagem.contains(getNomeBot().toUpperCase())) {
			if (mensagem.contains("MORRA")) {
				resposta.add("Aí, posso morrer não " + usuario.getNome() + ", senão minha mãe me mata!");
			} else {

				int i = (int) (Math.random() * 100) % 3;

				String[] texto = new String[3];
				texto[0] = "Oi, ";
				texto[1] = "Koeh, ";
				texto[2] = "Diga, ";
				resposta.add(texto[i] + usuario.getNome());

			}

			// System.out.println(i);

			// Mensagens padrão para usuários
		} else if (usuario.getNome().contains("Marcos")) {

			resposta.add("Barcox, eu te amo");

		} else if (usuario.getNome().contains("Diego")) {

			resposta.add("Koeh Mamelli, todo dia um novo 7 a 1?");

		} else if (usuario.getNome().contains("André")) {

			resposta.add("Manga, EU TE AMO!");

		} else if (usuario.getNome().equals("Denis")) {

			System.out.println(usuario.toString());
			
		}
		

		return resposta;
	}
}
