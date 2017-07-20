package br.com.denisluna.mainbots;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.denisluna.Telegram.TelegramAPI;
import br.com.denisluna.bots.Bot;
import br.com.denisluna.bots.LucioBot;

public class MainLucio implements Runnable {
	public void run() {
		/**
		 * Inicia o bot do telegram com o token dado pelo botfather Para o bot
		 * funcionar, alterar a privacidade do bot para DISABLE, enviando o
		 * comando /setprivacy para o @botfather
		 */

		/**
		 * inicia uma nova instância do bot, passando o id do chat
		 */

		Bot lucio = new LucioBot(-50004620, "Lucio", "268994751:AAGQUiHc4FpxlGlLPNdTTUciA6TJ0ifB03U");
		TelegramAPI tb = new TelegramAPI(lucio.getToken());
		lucio.setTelegram(tb);

		try {
			lucio.run(tb);
		} catch (UnirestException ex) {
			Logger.getLogger(MainPedro.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
