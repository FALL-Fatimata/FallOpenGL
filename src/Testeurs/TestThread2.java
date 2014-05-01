package Testeurs;

import Affichage.Affichage;
import Jeu.Manitou;

public class TestThread2 extends Thread {

	private Affichage afficheur ;
	public TestThread2(Affichage pong){
		this.afficheur=pong;
	}
	public TestThread2(Manitou manitou){
		afficheur = new Affichage(manitou);
	}
	public void run(){
		afficheur.play();
	}

}
