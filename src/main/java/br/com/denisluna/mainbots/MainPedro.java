package br.com.denisluna.mainbots;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.denisluna.Telegram.TelegramAPI;
import br.com.denisluna.bots.PedroBot;

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

		PedroBot pedro = new PedroBot(-50004620, "Pedro", "269671710:AAEJtxNRatFkaD6Bk_ra3peyRfFDkQtpQPM");
		TelegramAPI tb = new TelegramAPI(pedro.getToken());
		pedro.setTelegram(tb);

		try {
			pedro.run(tb);
		} catch (UnirestException ex) {
			Logger.getLogger(MainPedro.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
