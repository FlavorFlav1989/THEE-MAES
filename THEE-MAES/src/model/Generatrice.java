package model;

public class Generatrice {
    private double echantillon[];
    private double classes[][];
    private int nb_value;
    private int nb_class;

    public Generatrice(int n, int nb_class){
        echantillon = new double[n];
        classes = new double[nb_class][n];
        nb_value = n;
        this.nb_class = nb_class;
    }

    public double[] uniforme(){
        for(int i = 0; i < nb_value; i++){
            echantillon[i] = Uniforme.get_random();
        }
        tri();
        return echantillon;
    }

    public double[][] repartir_class(){
    	int k = 0;
        for(int i = 1; i <= nb_class; i++){
        	double max_class = i * (1.0/(double)nb_class);
        	System.out.println("max_class : " + max_class);
        	for(int j = 0; j < nb_value; j++){
        		if(echantillon[j] < max_class){
        			classes[i-1][k] = echantillon[j];
            		k++;
        		}
            }
        	k = 0;
        }
        return classes;
    }

    public void tri(){
        for(int i = 0; i < nb_value; i++){
        	for(int j = i; j < nb_value; j++){
        		if(echantillon[i] > echantillon[j]){
        			echantillon[j] = echantillon[i] + echantillon[j];
        			echantillon[i] = echantillon[j] - echantillon[i];
        			echantillon[j] = echantillon[j] - echantillon[i];
        		}
        	}
        }
    }

    public void affiche_echantilon(){
    	for(int i = 0; i < nb_value; i++){
    		System.out.println("Valeur : " + echantillon[i]);
    	}
    }

    public void affiche_classes(){
    	for(int i = 0; i < nb_class; i++){
    		for(int j = 0; j < nb_value/nb_class+1; j++){
    			if(classes[i][j] != 0)
    				System.out.println("Class : " + i + " Valeur " + j + " : " + classes[i][j]);
        	}
    	}
    }

    public double moyenne(){
    	double tot = 0;
    	for(int i = 0; i < nb_value; i++){
    		tot += echantillon[i];
    	}
    	return tot/nb_value;
    }

    public double variance_uniforme(){
    	double res = 0;
    	double moy = moyenne();
    	for(int i = 0; i < nb_value; i++){
    		res += ((echantillon[i] - moy) * (echantillon[i] - moy));
    	}
    	return res/nb_value;
    }

}
