package moduleClassif;

public class WeakClassifiers {

	int rows =14;  
	int columns = 20;
	double[][] MatrixOfFeatures = new double[rows][columns]; 
	double[] currentFeature = new double[rows];
	int[] labels = new int[rows];
	int[] newLabels = new int[rows]; 
	double[] weight = new double[rows];

	double[] errorThreshold = new double[rows]; 
	double[] errorFeatures = new double[columns];
	
	
	double[] tabValeur= new double[columns];
	
	double error1, error2;
	double error;
	int[] indiceThreshold = new int[columns];

	int[] labelShape1= new int[rows];
	int[] labelShape2= new int[columns];
	int labelShape;

	double valeurThreshold;
	int featureSelect;
	double[] modelh = new double[4]; 
	double errorCalculated;

	public WeakClassifiers(double[][] matrixOfFeatures, int[] labels, double[] weight){
		this.MatrixOfFeatures = matrixOfFeatures;
		this.labels = labels;
		this.weight = weight;
	}


	public double[] Classifiers() { 
		
		for(int p = 0; p<MatrixOfFeatures[0].length;p++){ //je vais jusqu a columns(20)
			
			// extract the feature
			for (int h = 0; h<MatrixOfFeatures.length; h++){ 
				currentFeature[h] = MatrixOfFeatures[h][p]; 			//prendre les valeurs de la colonne h de la matrice
			}
			
			for(int i =0; i<MatrixOfFeatures.length; i++){ //je vais jusqu a rows(12)

				
				//We assume in first time that to the right of threshold
				//it is +1 and to his left, -1

				newLabels=boubleForPourError1(currentFeature, i);
				error1 = error(labels,newLabels,weight);

				//In second time, +1 at left and -1 at right

				newLabels=boubleForPourError2(currentFeature, i);
				error2 = error(labels,newLabels,weight);

				if((error1 < error2)){			//&&(error1!=0)	///je ne veux pas des erreurs nulles car j'aurai betar = 0 et donc alphar = log(1/0) = infini et donc pour les labels de decision infini 
					this.errorThreshold[i] = error1;
					this.labelShape1[i] = +1;		//à droite c'est +1 et que à gauche -1 		
				}
				else{
					this.errorThreshold[i] = error2;
					this.labelShape1[i] = -1;		//à droite c'est -1 et que à gauche +1 
				}
			}

			// determinate the best threshold of the feature
			//which minimize the weighted error rate

			//select the best errorThreshold
			errorFeatures[p]=boucleForPourErrorThreshold(currentFeature, p);
		}
		
		//select the best errorFeatures
		error = boucleForPourErrorFeatures();

		modelh[0] = this.featureSelect;
		modelh[1] = this.error;
		modelh[2] = this.labelShape;
		modelh[3] = this.valeurThreshold; 

//		System.out.println("feature select "+modelh[0]+" error "+modelh[1]+" labelshape "+modelh[2]+" valeurThreshold "+modelh[3]);

		return modelh;
	}


	public double error(int[] labels2, int[] newLabels2, double[] weight){

		double errorCalculated = 0;


		for(int i=0;i<newLabels2.length;i++){
			if(newLabels2[i]!=labels2[i]){
				errorCalculated = errorCalculated + (weight[i]/rows);  // /rows   ou *lable(i)
			}
		}

		return errorCalculated;		
	}

	public int[] boubleForPourError1(double[]currentFeature, int i){
		for(int j=0 ; j<currentFeature.length;j++){ 

			if(currentFeature[j] >= currentFeature[i]){ //tout ceux qui sont à droite ont +1
				newLabels[j] = 1;
			}

			if(currentFeature[j] < currentFeature[i]){
				newLabels[j] = -1;
			}

		}
	
	return newLabels;
	}

	public int[] boubleForPourError2(double[] currentFeature,int i){

		for(int j=0 ; j<currentFeature.length;j++){ 

			if(currentFeature[j] >= currentFeature[i]){
				newLabels[j] = -1;
			}

			if(currentFeature[j] < currentFeature[i]){
				newLabels[j] = 1;
			}

		}

		return newLabels;
	}

	public double boucleForPourErrorThreshold(double[] currentFeature, int p){

		double valeur=errorThreshold[0];

		for(int j =0; j<errorThreshold.length;j++){	// j va jusqu'a 12
			if(errorThreshold[j] <= valeur ){ 
				valeur = errorThreshold[j];
				this.labelShape2[p] = labelShape1[j];
				this.tabValeur[p] = currentFeature[j];
			}
		}
		return valeur;
	}

	public double boucleForPourErrorFeatures(){
		
		double valeur=errorFeatures[0];
		
		for(int j =0; j<errorFeatures.length;j++){	//j va jusqu'à 20 
			if(errorFeatures[j] <= valeur ){
				this.featureSelect = j; // attention les tableaux en java commence par 0 donc featureSelect+1 pour matrice
				valeur = errorFeatures[j];
				this.labelShape = labelShape2[j];
				this.valeurThreshold = tabValeur[j];
			}
		}
		return valeur;
	}

}
