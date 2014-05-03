package Affichage;

import Jeu.Manitou;
import Testeurs.DirectionGiver;

public class TESTAffichage {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Manitou manitou = new Manitou();
		Affichage pong = new Affichage(manitou);
		DirectionGiver testeur = new DirectionGiver(manitou);
		testeur.testManitou();
		pong.play();
		
		

	}
}
