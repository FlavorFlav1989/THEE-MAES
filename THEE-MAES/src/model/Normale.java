package model;

public class Normale {
	public static double next_random(double m, double s){
		 double r = Math.sqrt(-2 * Math.log(Math.random())) * s;
         double x = 2 * Math.PI * Math.random();
         System.out.println("Random : " + (m + r * Math.sin(x)));
         return m + r * Math.sin(x);
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
		return alpha*alpha;
	}
}
