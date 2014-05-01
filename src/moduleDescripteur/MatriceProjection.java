package moduleDescripteur;

import Jama.Matrix;
import java.lang.Math;

public class MatriceProjection {



//	public MatriceProjection(Matrix Xg, Matrix Xd) {
//		this.Xg = Xg;
//		this.Xd = Xd;
//	}
	double[][] A = { { 3, 2, 1, 2, 4 }, { 4, 2, 8, 1, 4 },
			{ 6, 3, 1, 6, 8 }, { 1, 4, 6, 8, 5 }, { 4, 2, 5, 7, 4 } };
	double[][] B = { { 4, 2, 9, 4, 1 }, { 3, 2, 1, 4, 7 },
			{ 4, 7, 0, 4, 7 }, { 7, 4, 1, 8, 9 }, { 7, 4, 9, 5, 8 } };
	
	Matrix Xg = new Matrix(A);
	Matrix Xd = new Matrix(B);
	
	public Matrix Apprentissage(){
		Matrix matriceCov =	this.Cov(Xg).plus(this.Cov(Xd));
		
		Matrix Ut;
		Matrix D;

		Ut = matriceCov.svd().getU().transpose();
		D = matriceCov.svd().getS();

		// Calcul de D^(-1/2)

		for (int i = 0; i < D.getRowDimension(); i++) {
			D.set(i, i, 1 / Math.sqrt((D.get(i, i))));
		}

		return (Ut.times(D.times(Ut)));
	}

	public Matrix Cov(Matrix X) {
		Matrix matCov;
		int N = X.getRowDimension();
		int T = X.getColumnDimension();
		double[] total = new double[N];
		;

		// Calcul d'une matrice centrée
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < T; j++) {
				total[i] += X.get(i, j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < T; j++) {

				X.set(i, j, X.get(i, j) - total[i] / N);

			}
		}

		// Calcul de la matrice de covariance
		matCov = X.times(X.transpose());
		matCov = matCov.times(1 / matCov.trace());

		return (matCov);
	}
}
