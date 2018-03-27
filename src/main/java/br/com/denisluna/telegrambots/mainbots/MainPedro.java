package br.com.denisluna.telegrambots.mainbots;

import br.com.denisluna.bots.Bot;
import br.com.denisluna.bots.PedroBot;
import br.com.denisluna.telegrambots.utils.TelegramAPIUtils;

/**
 * 
 * @author denisluna
 * @category Bots
 * @version 1.0.0 @
 */
public class MainPedro implements Runnable {
	public void run() {
		/**
		 * Inicia o bot do telegram com o token dado pelo botfather Para o bot
		 * funcionar, alterar a privacidade do bot para DISABLE, enviando o
		 * comando /setprivacy para o @botfather
		 */

		/**
		 * inicia uma nova instância do bot, passando o id do chat
		 */
		Bot pedro = new PedroBot(Bot.CHAT_ID_MYPST, "Pedro", PedroBot.TOKEN);
		TelegramAPIUtils tb = new TelegramAPIUtils(pedro.getToken());
		pedro.setTelegram(tb);

		pedro.run(tb);
	}
}
