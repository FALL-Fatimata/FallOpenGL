package moduleDescripteurPAN4;

import Jama.Matrix;
import java.lang.Math;

public class MatriceProjection {

	private Matrix Xg;
	private Matrix Xd;

	public MatriceProjection(Matrix Xg, Matrix Xd) {
		this.Xg = Xg;
		this.Xd = Xd;
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

	public Matrix Variance() {
		return (this.Cov(Xg).plus(this.Cov(Xd)));
	}

	public Matrix Projection() {
		Matrix Ut;
		Matrix D;

		Ut = this.Variance().svd().getU().transpose();
		D = this.Variance().svd().getS();

		// Calcul de D^(-1/2)

		for (int i = 0; i < D.getRowDimension(); i++) {
			D.set(i, i, 1 / Math.sqrt((D.get(i, i))));
		}

		return (Ut.times(D.times(Ut)));
	}
}