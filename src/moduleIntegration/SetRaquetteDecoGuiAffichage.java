package moduleIntegration;

import moduleIG.ConfigData;

//cet interface impl�ment�e dans Affichage permettra � GUI d'envoyer la d�co et la raquette choisis � Affichage

public interface SetRaquetteDecoGuiAffichage {
	
	void setRaquette(int choixRaquette);
	void setDeco(int choixDeco);
	
	void traiterDonnees(ConfigData donnees);

}

