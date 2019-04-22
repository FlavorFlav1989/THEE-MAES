package model;

public class Poisson {
	
	public static double next_random(double alpha, int k){
	      double elambda = Math.exp(-1*alpha);
	      double product = 1;
	      int count =  0;
	      do {
	        product *= Math.random();
	        count++ ;
	      } while( product >= elambda ) ;
	      return count;
	}
	
	public static double moyenne(double[] tab){
		if(tab == null) return 0;
    	double tot = 0;
    	int nb_value = tab.length;
    	int i = 0;
    	for(i = 0; i < nb_value; i++){
    		if(tab[i] == 0) break;
    		tot += tab[i];
    	}
    	if(i == 0) return 0;
    	return tot/i;
	}
	
	public static double variance(double alpha){
		return alpha;
	}
}
