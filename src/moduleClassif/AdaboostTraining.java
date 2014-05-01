package moduleClassif;

public class AdaboostTraining {

	int rows =14;  
	int columns = 20;
	int nbrWClassifier = 100;
	double[][] MatrixOfFeatures = new double[rows][columns]; 
	int[] labels = new int[rows];
	double[] weight = new double[rows];
	
	double[] modelh = new double[4];
	double[][] modelH = new double[5][nbrWClassifier];//contient les valeurs de modelh + alphar ; chaque colonne pour un classifieur faible  
	
	double error;
	double betar, alphar;
	double[] nouveauxPoids = new double[rows];
	int valeur;

	public AdaboostTraining(double[][]MatrixOfFeatures, int[] labels){ 
		this.MatrixOfFeatures= MatrixOfFeatures;
		this.labels = labels;
	}

	public double[][] adaboostTraining(){

		//Initialisation des poids
		for(int i=0;i<weight.length;i++){ 
			this.weight[i] = 1.0/columns;
		}
		
		//generation de nbrWClassifier classifieurs
		for(int r=0;r<nbrWClassifier;r++){
			
			//faire la somme des poids
			double sommePoids= weight[0];
			for(int j=1; j<rows;j++){ //I
				sommePoids= sommePoids+weight[j];
			}

			//normalisation
			for(int i=0;i<rows;i++){
				weight[i]=weight[i]/sommePoids;
			}

			//entrainer le classifieur hr (generation de classifieur faibles)
			
			WeakClassifiers weakClassifier = new WeakClassifiers(MatrixOfFeatures, labels, weight);
			
			this.modelh= weakClassifier.Classifiers(); 
			
			for(int i=0;i<modelH.length-1;i++){ // -1 parceque la derniere ligne est pour le alphar
				this.modelH[i][r] = modelh[i]; 
			}

			error = modelh[1];
			betar= error/(1-error);

			//coef associé à hr
			alphar=Math.log(1/(Math.abs(betar)));
			
			this.modelH[4][r] = alphar;

			// modifier les poids
			this.weight = changerPoids(MatrixOfFeatures, labels, modelh, weight);

		}

		return modelH;
	}
	
	public double[] changerPoids(double[][] MatrixOfFeatures, int labels[], double[] modelh, double[] weight){
		
		double[] X = new double[MatrixOfFeatures[0].length];
		
		for(int j = 0; j<MatrixOfFeatures.length; j++ ){
			for (int h = 0; h<MatrixOfFeatures[0].length; h++){
				X[h] = MatrixOfFeatures[j][h]; 			//prendre la ligne j de la matrice
			}
		
			int indiceFeature=(int)modelh[0];
			double valeurThreshold = X[indiceFeature];
			int label;

			if(valeurThreshold <= modelh[3]){
				label = -(int)modelh[2];
			}
			else {
				label = (int)modelh[2];
			}
			
			//si bien classe changer poids 
			if(label==labels[j]){
				this.nouveauxPoids[j] = weight[j]*betar;
			}
			else{
				this.nouveauxPoids[j] = weight[j];
			}
		}
		
		return nouveauxPoids;
	}
}
