package moduleDescripteur;

import Jama.Matrix;

public class TestDescripteur {

	private static double[] vectorOfFeatures;

	public static void main(String[] args) {
		
		double[][] C = { { 4.1, 2, 8, 8.5, 1.2 }, { 3.1, 2, 7, 2.1, 6.4 },
				{ 5.5, 6.5, 0.2, 4.2, 7.2 }, { 6, 4, 2, 8, 8 },
				{ 6.8, 3.8, 6.8, 6.4, 7.8 } };
		
		Matrix X = new Matrix(C);
		vectorOfFeatures = new double[X.getRowDimension()];
		
		Projection descripteur = new Projection(X);
		vectorOfFeatures = descripteur.Descripteur();
		
		for (int i=0; i<vectorOfFeatures.length;i++){
			System.out.println(vectorOfFeatures[i]);
		}
	}

}
