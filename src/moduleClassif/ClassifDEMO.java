package moduleClassif;

import Jeu.Manitou;
import moduleIntegration.*;

public class ClassifDEMO implements DescripteurClassificationInterface {
	
	int[] matrice ;
	private String chemin = "data\\LOGS" ;
	private String message = "";
	Manitou manitou = new Manitou();
	private int counter0 = 0;
	private int counter1 = 0;
	
	public ClassifDEMO(){
		super();
	}
	
	@Override
	public void giveMatrix(int[] matrix) {
		this.matrice = matrix;
		message = message+"matrice du descripteur envoy�e � Classif";
		sendPosition();
	}
	
	public void sendPosition(){
		
		for(int i =0; i<matrice.length;i++){
			if(matrice[i]==0) counter0++;
			else counter1++;
		}
		if(counter0>=counter1){
			manitou.setDirectionP1(0);
			message=message+"\n DirectionP1 envoy�e : 0";
			System.out.println("direction 0");
		}
		else {
			manitou.setDirectionP1(1);
			message=message+"\n DirectionP1 envoy�e : 1";	
			System.out.println("direction 1");
		}
		ReproduireExec.ecrire(chemin, message);
	}
	
}
