package Jeu;
import moduleIG.ConfigData;
import moduleIntegration.*;


//Modif par rapport au vbo

public class Manitou implements SetDirectionInterfaceClassifCon, GetPositionOfRaquetteBalleAffichageCon,
IGControleurInterface{

	public Manitou() {
		super();
		// TODO Auto-generated constructor stub
		directionP1=0;
		directionP2=0;
		laBalle=new MouvementBalle(balle, decor, raquetteP1, raquetteP2, score,this);
		lesRaquettes=new MouvementRaquettes(raquetteP1, raquetteP2, decor, directionP1, directionP2);
		
	}
	
	public void traiterDonnees(ConfigData donnees){
		this.speed=donnees.difficulte;
	}
	
	private TableDePingPong decor=new TableDePingPong(680,400,0);
	private int speed=1;
	private int directionP1;
	private int directionP2;
	private Balle balle = new Balle(0,0,0,speed,1);
	private Raquette raquetteP1=new Raquette((decor.getX()/2), 0,0,100);
	private Raquette raquetteP2=new Raquette(-(decor.getX()/2), 0,0,100);
	private Score score=new Score(0,0);
	private MouvementBalle laBalle;
	private MouvementRaquettes lesRaquettes;
	public boolean pause=true;


	public void mettreEnPause_Reprendre(){
		this.pause= false;
		System.out.println("Je mets le jeu en pause");
	}
	

	@Override
	public void setDirectionP1(int direction) {
		// TODO Auto-generated method stub
		lesRaquettes.run();
		this.directionP1=direction;
		lesRaquettes.setDirectionP1(direction);
	}
	@Override
	public void setDirectionP2(int direction) {
		// TODO Auto-generated method stub
		this.directionP2=direction;
		lesRaquettes.setDirectionP2(direction);
	}


	@Override
	public Balle getBalle() {
		// TODO Auto-generated method stub
		laBalle.run();
		if(score.getP1Score()>10 || score.getP2Score()>10){
			this.mettreEnPause_Reprendre();
		
		}
		return this.balle;
		
	}

	@Override
	public Raquette getRaquetteP1() {
		// TODO Auto-generated method stub

		return this.raquetteP1;
	}

	@Override
	public Raquette getRaquetteP2() {
		// TODO Auto-generated method stub
		return this.raquetteP2;
	}

	
	
	public void quitter(){
		
	}

	// Il faut rajouter des setters et getters concernant les 4 differents décors et les 4 différents types de raquette

}
