package model;

public class Generatrice {
	private double echantillon[];
	private double classes[][];
	private int nb_value;
	private int nb_class;

	public Generatrice(int n, int nb_class){
		echantillon = new double[n];
		classes = new double[nb_class][n/nb_class+1];
		nb_value = n;
		this.nb_class = nb_class;
	}

	public double[] uniforme(){

		for(int i = 0; i < nb_value; i++){
			//echantillon[i] =
		}
		return echantillon;
	}

}
