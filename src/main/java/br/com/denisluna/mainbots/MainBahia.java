package br.com.denisluna.mainbots;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.denisluna.Telegram.TelegramAPI;
import br.com.denisluna.bots.BahiaBot;

public class MainBahia implements Runnable {

	public void run() {
		// TODO Auto-generated method stub

		/**
		 * inicia uma nova instância do bot, passando o id do chat
		 */
		BahiaBot bahia = new BahiaBot(-141839020, "Bahia", "239602877:AAG203xAxUfO64C_8zWRjuz7kog1cW9dbFQ");
		TelegramAPI tb = new TelegramAPI(bahia.getToken());
		bahia.setTelegram(tb);

		try {
			bahia.run(tb);
		} catch (UnirestException ex) {
			Logger.getLogger(MainBahia.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
