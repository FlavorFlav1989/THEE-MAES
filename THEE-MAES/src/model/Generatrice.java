package model;

public class Generatrice {
    private double echantillon[];
    private double classes[][];
    private int nb_value;
    private int nb_class;
    private TypeDistribution distrib;
    private double param1;
    private double param2;
    private double val_max;
    public Generatrice(int n, int nb_class, TypeDistribution distrib){
        echantillon = new double[n];
        classes = new double[nb_class][n];
        nb_value = n;
        this.nb_class = nb_class;
        this.distrib = distrib;
    }

    public double[] getDistribution(Double alpha, Double beta){
    	switch(distrib){
	    	case UNIFORME : return uniforme();
	    	case POISSON : return poisson(alpha);
	    	case EXPONENTIELLE : return exponentielle(alpha);
	    	case NORMALE : return normale(alpha, beta);
	    	default : return null;
    	}
    }
    
    private double[] uniforme(){
        for(int i = 0; i < nb_value; i++){
            echantillon[i] = Uniforme.next_random();
        }
        tri();
        return echantillon;
    }
    
    private double[] poisson(double alpha){
    	this.param1 = alpha;
        for(int i = 0; i < nb_value; i++){
            echantillon[i] = Poisson.next_random(alpha, i);
        }
        tri();
        return echantillon;
    }
    
    private double[] exponentielle(double alpha){
    	this.param1 = alpha;
        for(int i = 0; i < nb_value; i++){
            echantillon[i] = Exponentielle.next_random(alpha);
        }
        tri();
        return echantillon;
    }
    
    private double[] normale(double alpha, double beta){
    	this.param1 = alpha;
    	this.param2 = beta;
    	for(int i = 0; i < nb_value; i++){
            echantillon[i] = Normale.next_random(alpha, beta)+0.5;
        }
        tri();
        return echantillon;
    }

    public double[][] repartir_class(){
    	int k = 0;
        for(int i = 1; i <= nb_class; i++){
        	/*Borne supérieur de la classe*/
        	double max_class = i * (val_max/(double)nb_class);
        	/*Borne inféireur de la classe*/
        	double min_class = (i == 1 ? 0 : ((i-1) * (val_max/(double)nb_class)));
        	System.out.println("max_class : " + max_class);
        	System.out.println("min_class : " + min_class);
        	for(int j = 0; j < nb_value; j++){
        		if(echantillon[j] <= max_class && echantillon[j] > min_class){
        			classes[i-1][k] = echantillon[j];
        			k++;
        		}
            }
        	k = 0;
        }
        reduce_class();
        return classes;
    }
    
    private void reduce_class(){
    	for(int i = classes.length-1 ; i > 0 ; i--){
    		if(compte_valeur(classes[i]) < 5){
    			migrer_valeur(i, i--, compte_valeur(classes[i]));
    		}
    	}
    }
    
    private void migrer_valeur(int index_out, int index_in, int nb_value){
    	int index_to_insert = compte_valeur(classes[index_in]);
    	for(int i = 0; i < nb_value; i++){
    		classes[index_in][index_to_insert] = classes[index_out][i];
    		classes[index_out][i] = 0;
    		index_to_insert++;
    	}
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
        val_max = echantillon[nb_value-1];
    }

    public void affiche_echantilon(){
    	for(int i = 0; i < nb_value; i++){
    		System.out.println("Valeur : " + echantillon[i]);
    	}
    }

    public void affiche_classes(){
    	for(int i = 0; i < nb_class; i++){
    		for(int j = 0; j < nb_value; j++){
    			if(classes[i][j] != 0)
    				System.out.println("Class : " + i + " Valeur " + j + " : " + classes[i][j]);
    			else
    				break;
        	}
    	}
    }

    public double moyenne(){
    	switch(distrib){
    	case UNIFORME : return Uniforme.moyenne(echantillon);
    	case POISSON : return Poisson.moyenne(echantillon);
    	case EXPONENTIELLE : return Exponentielle.moyenne(echantillon);
    	case NORMALE : return Normale.moyenne(echantillon);
    	default : return 0;
    	}
    }

    public double variance(){
    	switch(distrib){
    	case UNIFORME : return Uniforme.variance(echantillon);
    	case POISSON : return Poisson.variance(this.param1);
    	case EXPONENTIELLE : return Exponentielle.variance(this.param1);
    	case NORMALE : return Normale.variance(this.param2);
    	default : return 0;
    	}
    }
    
    public double test_ki2(){
    	int classes_theorique = nb_value / nb_class;
    	int classes_reel;
    	double q = 0;
    	for(int i = 0; i < nb_class; i++){
    		classes_reel = compte_valeur(classes[i]);
    		System.out.println("Classe : " + i + ", Théorique : " + classes_theorique + ", Réel : " + classes_reel);
    		double classes_reel_conv = (double) classes_reel;
    		double classes_theorique_conv = (double) classes_theorique;
    		q += (((classes_reel_conv - classes_theorique_conv) * (classes_reel_conv - classes_theorique_conv)) / classes_theorique_conv);
    	}
    	System.out.println("Q = " + q);
    	return q;
    }
    
    public int compte_valeur(double tab[]){
    	int i = 0;
    	for(i = 0; i < tab.length; i++){
    		if(tab[i] == 0)break;
    	}
    	return i;
    }

}
