package moduleDescripteur;

import Jama.Matrix;

public class Projection {

	private Matrix X;
	private Matrix W;
	int k= 2;				// a modifier

	public Projection(Matrix X) {
		this.X = X;
	}

	// Attention à la dimension de X, il doit avoir un nombre pair de colonne

	
	public Matrix MatriceRedimensionnee() {
		
		MatriceProjection matriceProjection = new MatriceProjection();
		W = matriceProjection.Apprentissage();
		
		
		Matrix M = new Matrix(2 * k, W.getColumnDimension());

		for (int j = 0; j < M.getColumnDimension(); j++) {
			for (int i = 0; i < k; i++) {

				M.set(i, j, W.get(i, j));
			}

			for (int i = k; i < M.getRowDimension(); i++) {
				M.set(i, j, W.get((i - k) + W.getRowDimension() - k, j)); // si
																			// problème
																			// voir
																			// ici
			}

		}
		return (M);
	}

	public double[] Descripteur() {

		Matrix Z;
		Matrix Xd;

	
		Z = this.MatriceRedimensionnee().times(X);
		Mediane Me = new Mediane(Z);
		Xd = Me.GetMediane();

		double[] VectorOfFeature = new double[Xd.getRowDimension()];

		for (int i = 0; i < Xd.getRowDimension(); i++) {
			VectorOfFeature[i] = Xd.get(i, 1);
		}

		return (VectorOfFeature);

	}

}
