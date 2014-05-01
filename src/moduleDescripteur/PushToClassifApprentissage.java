package moduleDescripteurPAN4;

import Jama.Matrix;

public class PushToClassifApprentissage {

	Matrix Xg;
	Matrix Xd;

	public PushToClassifApprentissage(Matrix Xg, Matrix Xd, Matrix[] tableau) {
		this.Xg = Xg;
		this.Xd = Xd;
	}

	public double[][] Push(Matrix[] tableau,int k) {
		
		PushToClassifTest T = new PushToClassifTest(Xg, Xd);
		double[][] tab2 = new double[tableau.length][];
		
		for (int i = 0; i < tableau.length; i++) {
			tab2[i]=T.Push(tableau[i],k);

		}
		
		return(tab2);

	}
}
