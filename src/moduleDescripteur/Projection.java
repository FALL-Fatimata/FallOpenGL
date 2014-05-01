package moduleDescripteurPAN4;

import Jama.Matrix;

public class Projection {

	private Matrix X;
	private Matrix W;

	public Projection(Matrix X, Matrix W) {
		this.X = X;
		this.W = W;
	}

	// Attention à la dimension de X, il doit avoir un nombre pair de colonne

	public Matrix MatriceRedimensionnee(int k) {

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

	public Matrix Descripteur(int k) {

		Matrix S;
		Matrix Xd;

	
		S = this.MatriceRedimensionnee(k).times(X);
		Mediane Me = new Mediane(S);
		Xd = Me.GetMediane();

		return (Xd);

	}

}
