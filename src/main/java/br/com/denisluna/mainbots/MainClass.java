package br.com.denisluna.mainbots;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainPedro pedro = new MainPedro();
		Thread threadPedro = new Thread(pedro);
		threadPedro.start();

		MainBahia bahia = new MainBahia();
		Thread threadBahia = new Thread(bahia);
		threadBahia.start();

		MainLucio lucio = new MainLucio();
		Thread threadLucio = new Thread(lucio);
		threadLucio.start();

	}

}
