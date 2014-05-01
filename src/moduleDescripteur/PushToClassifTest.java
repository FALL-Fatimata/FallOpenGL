package moduleDescripteur;

import Jama.Matrix;

public class PushToClassifTest {
	
	Matrix Xd;
	Matrix Xg;
	
	public PushToClassifTest( Matrix Xd, Matrix Xg){
		this.Xd=Xd;
		this.Xg=Xg;
	
	}
	
	public Matrix getFinal(Matrix X,int k){
		Matrix W;
		Matrix Final;

		
		MatriceProjection Ma = new MatriceProjection(Xg, Xd);
		W = Ma.Projection();
		Projection Po = new Projection(W, X);
		Final = Po.Descripteur(k);
		return(Final);
		
	}
	
	public double[] Push(Matrix X,int k){
		
		
		double[] VectorOfFeature = new double[this.getFinal(X,k).getRowDimension()];
		
		for ( int i=0; i<this.getFinal(X,k).getRowDimension() ; i++){
			VectorOfFeature[i]=this.getFinal(X,k).get(i, 1);
		}
		
		return(VectorOfFeature);
		
	}

}
