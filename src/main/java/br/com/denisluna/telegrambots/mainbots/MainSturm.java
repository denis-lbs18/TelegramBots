package br.com.denisluna.telegrambots.mainbots;

import br.com.denisluna.bots.AlexandreSturmBot;
import br.com.denisluna.bots.Bot;
import br.com.denisluna.telegrambots.utils.TelegramAPIUtils;

/**
 * 
 * @author denisluna
 * @category Bots
 * @version 1.0.0 @
 */
public class MainSturm implements Runnable {
	public void run() {
		/**
		 * Inicia o bot do telegram com o token dado pelo botfather Para o bot
		 * funcionar, alterar a privacidade do bot para DISABLE, enviando o comando
		 * /setprivacy para o @botfather
		 */

		/**
		 * inicia uma nova instância do bot, passando o id do chat
		 */
		Bot sturm = new AlexandreSturmBot(Bot.CHAT_ID_MYPST, "Sturm", AlexandreSturmBot.TOKEN);
		TelegramAPIUtils tb = new TelegramAPIUtils(sturm.getToken());
		sturm.setTelegram(tb);

		sturm.run(tb);
	}
}
