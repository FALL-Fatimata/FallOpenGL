package moduleIG;

import Affichage.Affichage;
import Jeu.Manitou;
import Testeurs.TestThread1;

public class MainTestIG {

	public MainTestIG() {
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manitou manitou= new Manitou();
		
		Affichage pong = new Affichage(manitou);
		
		TestThread1 t1= new TestThread1(manitou);
		
		t1.run();
		FenetreDemarrage window = new FenetreDemarrage(manitou,pong);
		window.show();

	}
}
