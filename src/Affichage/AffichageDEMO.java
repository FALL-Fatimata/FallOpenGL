package Affichage;

import Jeu.Balle;
import Jeu.Manitou;
import Jeu.Raquette;
import moduleIG.ConfigData;
import moduleIntegration.SetRaquetteDecoGuiAffichage;

public class AffichageDEMO implements SetRaquetteDecoGuiAffichage {

	@Override
	public void setRaquette(int choixRaquette) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDeco(int choixDeco) {
		// TODO Auto-generated method stub
		
	}
	
	public void afficher(){
		Manitou manitou = new Manitou();
		Balle balle = manitou.getBalle();
		//System.out.println(balle.getX()+"  "+balle.getY()+"  "+balle.getZ()+"  ");
		Raquette raquette = manitou.getRaquetteP1();
		
	}

	@Override
	public void traiterDonnees(ConfigData donnees) {
		// TODO Auto-generated method stub
		
	}

}
