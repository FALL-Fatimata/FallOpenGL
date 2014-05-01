package Jeu;

public class MouvementRaquettes  {

	Raquette raquetteP1;
	Raquette raquetteP2;
	TableDePingPong decor;
	int j1;
	int j2;
	boolean balleStaysInTheField;
	public MouvementRaquettes(Raquette raquetteP1,Raquette raquetteP2,TableDePingPong decor, int directionP1, int directionP2){

		this.raquetteP1=raquetteP1;
		this.raquetteP2=raquetteP2;
		this.decor=decor;
		this.j1=directionP1;
		this.j2=directionP2;
	}

	public void setDirectionP1(int direction){
		this.j1=direction;
	}

	public void setDirectionP2(int direction){
		this.j2=direction;
	}
	public void run(){
		raquetteP1.setDy(4*(2*j1-1));
		raquetteP2.setDy(2*j2-1);
		raquetteP1.move();
		raquetteP2.move();




		if (raquetteP1.isAtLimitsY(decor)){
			if (raquetteP1.getY()<0){
				raquetteP1.setY(-decor.getY()/2+raquetteP1.getWidth()/2);
			}
			else{
				raquetteP1.setY(decor.getY()/2-raquetteP1.getWidth()/2);
			}
		}




		if (raquetteP2.isAtLimitsY(decor)){
			if (raquetteP2.getY()<0){
				raquetteP2.setY(-decor.getY()/2+raquetteP2.getWidth()/2);
			}
			else{
				raquetteP2.setY(decor.getY()/2-raquetteP2.getWidth()/2);
			}


			this.balleStaysInTheField=decor.getBalleStaysInTheField();

		}
	}
}
