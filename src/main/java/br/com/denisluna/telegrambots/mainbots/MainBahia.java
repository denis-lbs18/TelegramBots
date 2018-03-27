package br.com.denisluna.telegrambots.mainbots;

import br.com.denisluna.bots.BahiaBot;
import br.com.denisluna.bots.Bot;
import br.com.denisluna.telegrambots.utils.TelegramAPIUtils;

public class MainBahia implements Runnable {
	public void run() {
		/**
		 * inicia uma nova instância do bot, passando o id do chat
		 */
		Bot bahia = new BahiaBot(-141839020, "Bahia", "239602877:AAG203xAxUfO64C_8zWRjuz7kog1cW9dbFQ");
		TelegramAPIUtils tb = new TelegramAPIUtils(bahia.getToken());
		bahia.setTelegram(tb);

		bahia.run(tb);

	}
}
