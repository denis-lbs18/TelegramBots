package br.com.denisluna.telegrambot;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainPedro pedro = new MainPedro();
		Thread threadPedro = new Thread(pedro);
		threadPedro.start();
		
		MainBahia bahia = new MainBahia();
		Thread threadBahia = new Thread(bahia);
		threadBahia.start();		

	}

}
