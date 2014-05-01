package Jeu;

public class MouvementRaquette  {

	Raquette raquette;

	TableDePingPong decor;
	int j;

	boolean balleStaysInTheField;
	public MouvementRaquette(Raquette raquette,TableDePingPong decor, int direction){

		this.raquette=raquette;

		this.decor=decor;
		this.j=direction;
		
	}

	public void setDirection(int direction){
		this.j=direction;
	}

	
	public void run(boolean v){
		if (v){
		raquette.setDy(20*(2*j-1));
		}
		else{
			raquette.setDy(5*(2*j-1));
		}
		raquette.move();
		




		if (raquette.isAtLimitsY(decor)){
			if (raquette.getY()<0){
				raquette.setY(-decor.getY()/2+raquette.getWidth()/2);
			}
			else{
				raquette.setY(decor.getY()/2-raquette.getWidth()/2);
			}
		}




		
	}
}
