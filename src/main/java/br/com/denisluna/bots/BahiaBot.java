package br.com.denisluna.bots;

import java.util.*;

import br.com.denisluna.utils.Usuario;

public class BahiaBot extends Bot {

	public BahiaBot(int chat_id, String nomebot, String token) {
		super(chat_id, nomebot, token);
		// TODO Auto-generated constructor stub
		this.chat_id = chat_id;
		this.nomebot = nomebot;
		this.token = token;
	}


	@Override
	public List<String> responde(String mensagem, String titulo, String tipo, Usuario usuario) {
		// TODO Auto-generated method stub
		List<String> resposta = new ArrayList<String>();
		
		if (tipo.contains("group")) {

			System.out.println("Estou conversando com alguém no grupo " + titulo
							 + ", chat_id: " + chat_id + ", usuário: " + usuario.getNome());

		} else if (tipo.contains("private")) {

			System.out.println("Estou conversando com alguém no privado com o usuário: " + usuario.getNome());

		}
		
		// Mensagens padrão para conteúdos da mensagem
		if (mensagem.contains("ESCREVE DIREITO") && mensagem.contains(getNomeBot().toUpperCase())) {

			resposta.add("Tomar no cu!!");
			resposta.add("Eu sou analfabeto funcional");

		} else if (mensagem.contains("VEM FAZER BOQUETE") && mensagem.contains(getNomeBot().toUpperCase())) {

			System.out.println("Alguém me mandou fazer um sexo oral");
			resposta.add(usuario.getNome() + ", vem aqui fazer um bola gata, vem...");

		} else if (mensagem.contains("COMER") && mensagem.contains("SUA MAE")) {
			
			System.out.println("Alguém vai comer minha mãe");
			resposta.add("Ahahahahahaha... Comer a minha mãe é normal, pior é quem come vc " + usuario.getNome() + "!! Feio e viadao ");			
			
		} else if (mensagem.contains("COMER SUA BUNDA DE ACARAJÉ") && mensagem.contains(getNomeBot().toUpperCase())) {

			System.out.println("Confirmo");
			resposta.add("Confirmo, " + usuario.getNome() + ".");

		} else if 	(mensagem.contains(getNomeBot().toUpperCase()) &&
					 (mensagem.contains("CEARENSE") || mensagem.contains("PARAIBA"))) 
		{

			System.out.println("Alguém errou minha origem");
			resposta.add(usuario.getNome() + " do cacete... Além do mais é burro!! Sou baiano imbecil de Pinto pequeno");

		} else if ((mensagem.contains("WINDOWS 10") || mensagem.contains("WINDOWS")) && mensagem.contains("BOSTA")) {
		
			System.out.println("Algum macfag respondeu");
			resposta.add(usuario.getNome() + " seu macflag do caralho!!");
			
		} else if (mensagem.contains("NAMORADA") || mensagem.contains("ESPOSA")) {
			
			resposta.add(usuario.getNome() + ", tua mina?");
			resposta.add("Manda ela p cá!!");
			
		} else if (mensagem.contains("DORMIR") || mensagem.contains("SONO")) {
			
			resposta.add("To com sono!!! Me fodam");
			resposta.add("Ai no paraíso");

		} else if (mensagem.contains("PALMEIRAS") || mensagem.contains("PORCO")) {
			
			resposta.add("Mesmo cheio de reserva o porcao ganhou...");
			resposta.add("Somos fofas");			

		} else if (mensagem.contains("VELLEGO") || mensagem.contains("VELHO ESCROTO")) {
			
			resposta.add("Eu ti amo vellego, venha fazer amor cmigo!!");
			
		} else if (mensagem.contains("QUE?")) {
			
			resposta.add("QUE O " + mensagem);
			
		} else if (mensagem.contains("CHUPA") && mensagem.contains(getNomeBot().toUpperCase())) {
			
			resposta.add("Vou chupar é o CARALHO, " + usuario.getNome());
			resposta.add("Não, pera...");			
		
		} else if (mensagem.contains("HAHA") || mensagem.contains("HUE")) {
			
			resposta.add("Ababananadas");
			
		} else if (mensagem.contains("BOT")) {
			
			resposta.add("Eu não sou um bot, sou baiano imbecil de pinto pequeno!");
			
		} else if ((mensagem.contains("TA VIVO") || mensagem.contains("CAIU")) && mensagem.contains(getNomeBot().toUpperCase()) && mensagem.contains("?")) {

			resposta.add("Naum seu idiota");
			resposta.add("Eu tava na rede");
			
			
		} else if (mensagem.contains("CASSIO") || mensagem.contains(getNomeBot().toUpperCase())) {
			
			System.out.println("Alguém me chamou pelo nome");
			resposta.add("Aí " + usuario.getNome() + ", meu nome é BAHOA");
			resposta.add("Putz");
			resposta.add("Errei meu próprio nome");
			
			resposta.add("E burro eh vc seu velho escroto!!");	
			
		// Mensagens padrão para usuários
		} else if (usuario.getNome().contains("George")) {

			resposta.add("Jordy, eu te amo");
		
		} else if (usuario.getNome().contains("José")) {
			
			resposta.add("Vá se foder, Zé");			

		} else if (usuario.getNome().contains("Bruno")) {
			
			resposta.add("BRUNO, EU TE AMO!");

		}
		
		return resposta;
	}

}