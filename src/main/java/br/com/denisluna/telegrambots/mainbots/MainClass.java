package br.com.denisluna.telegrambots.mainbots;

public class MainClass {

	public static void main(String[] args) {
		MainPedro pedro = new MainPedro();
		Thread threadPedro = new Thread(pedro);
		threadPedro.start();

		MainSturm sturm = new MainSturm();
		Thread threadSturm = new Thread(sturm);
		threadSturm.start();
		/*
		 * MainBahia bahia = new MainBahia(); Thread threadBahia = new Thread(bahia);
		 * threadBahia.start();
		 */
	}
}
