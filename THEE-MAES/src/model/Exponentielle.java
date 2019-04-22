package model;

public class Exponentielle {
	
	public static double next_random(double alpha){
		System.out.println("Random : " + (alpha * Math.exp(-alpha * Math.random())));
		return (alpha * Math.exp(-alpha * Math.random()));
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
		return 1 / (alpha * alpha);
	}
}
