package moduleDescripteurPAN4;

import Jama.Matrix;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double[][] A = { { 3, 2, 1, 2, 4 }, { 4, 2, 8, 1, 4 },
				{ 6, 3, 1, 6, 8 }, { 1, 4, 6, 8, 5 }, { 4, 2, 5, 7, 4 } };
		double[][] B = { { 4, 2, 9, 4, 1 }, { 3, 2, 1, 4, 7 },
				{ 4, 7, 0, 4, 7 }, { 7, 4, 1, 8, 9 }, { 7, 4, 9, 5, 8 } };
		double[][] C = { { 4.1, 2, 8, 8.5, 1.2 }, { 3.1, 2, 7, 2.1, 6.4 },
				{ 5.5, 6.5, 0.2, 4.2, 7.2 }, { 6, 4, 2, 8, 8 },
				{ 6.8, 3.8, 6.8, 6.4, 7.8 } };
		Matrix Xg = new Matrix(A);
		Matrix Xd = new Matrix(B);
		Matrix W;
		Matrix X = new Matrix(C);
		Matrix Final;
		int k = 2;

		MatriceProjection Ma = new MatriceProjection(Xg, Xd);
		W = Ma.Projection();
		Projection Po = new Projection(W, X);
		Final = Po.Descripteur(k);
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				System.out.println(Final.get(i, j));
			}
		}
	}

}
