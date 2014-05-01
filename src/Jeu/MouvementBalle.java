package Jeu;

public class MouvementBalle{

	Balle balle;
	TableDePingPong decor;
	Raquette raquetteP1;
	Raquette raquetteP2;
	Score score;
	boolean balleStaysInTheField;
	Manitou manitou;

	public MouvementBalle(Balle balle, TableDePingPong decor, Raquette raquetteP1, Raquette raquetteP2, Score score, Manitou manitou){
		this.balle=balle;
		this.decor=decor;
		this.raquetteP1=raquetteP1;
		this.raquetteP2=raquetteP2;
		this.score=score;

		this.manitou=manitou;
	};
	public void run() {
		if(manitou.pause){
			balle.move();
//			System.out.println(balle.toString());
			if (balle.isAtLimitsY(decor)){
				System.out.println("J'ai vu que j'étais à la limite");
				
				decor.Rebond(balle);
			}
//			if (balle.isAtLimitsZ(decor)){
//				decor.Rebond(balle);
//			}

			if (balle.isAtLimitsX(decor)){
				if (balle.getX()<0){
					if (balle.staysInTheField(raquetteP1)){
						raquetteP1.Rebond(balle);
					}
					else {
		
						int i=score.getP2Score();
						score.setP2Score(i+1);
						System.out.println("La balle est sortie est et le joueur 2 a gagné un point");
						balle.reInitialize();
						/*FIN DE LA MANCHE*/
					}
				}
				else {
					if (balle.staysInTheField(raquetteP2)){
						raquetteP2.Rebond(balle);
					}
					else { 
	
						int i=score.getP1Score();
						score.setP1Score(i+1);
						System.out.println("La balle est sortie est et le joueur 1 a gagné un point, son score est de "+score.getP1Score());
						balle.reInitialize();

						/*FIN DE LA MANCHE*/
					}
				}

			}
		}
	}
}

