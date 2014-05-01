package moduleDescripteurPAN4;
import Jama.Matrix;
import java.util.Arrays;

public class Mediane {
	
	Matrix M;
	
	public Mediane(Matrix M){
		this.M=M;
	}

	public Matrix GetMediane() {

		int N = M.getRowDimension();
		int T = M.getColumnDimension();
		double[] Middle = new double[N];

		double[][] tab = new double[N][T];

		// on trie les lignes de la matrice dans tab
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < T; j++) {
				tab[i][j] = M.get(i, j);
			}
			Arrays.sort(tab[i]);
		}

		
		 for (int i=0 ; i<N ; i++){ for (int j=0 ; j<T ; j++){
		  System.out.println(tab[i][j]); } }
		 
		
		//On détermine la médiane de chaque ligne
		
		for (int i = 0; i < N; i++) {
			if (T % 2 == 0) {
				Middle[i] = tab[i][T / 2];
			} else {
				Middle[i] = (tab[i][(T - 1) / 2] + tab[i][(T + 1) / 2]) / 2;

			}
		}
		
		Matrix Ma = new Matrix(Middle,1);
		
		
		
		
		
		/*
		//On retire cette médiane à chaque élément de chaque ligne
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < T; j++) {
				M.set(i, j, M.get(i, j)-Middle[i]);
			}
		
		}
		*/
		
		
		
		
		
		/*
		
		// Calcul de la médiane de la matrice
		
		double[] tab2 = new double[N*T];
		int t=0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < T; j++) {
				tab2[t] = M.get(i, j);
				t=t+1;
			}
			t=t+1;
		}
		Arrays.sort(tab2);
		
		double Middle2;
			if ((N*T) % 2 == 0) {
				Middle2 = tab2[(N*T) / 2];
			} else {
				Middle2 = (tab2[((N*T) - 1) / 2] + tab2[((N*T) + 1) / 2]) / 2;

			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < T; j++) {
					M.set(i, j, M.get(i, j)-Middle2);
				}
			
			}
		
		*/
		
		return (Ma);
	}
}
