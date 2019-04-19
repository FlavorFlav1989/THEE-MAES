package model;

public class MaintTest {

	public static void main(String[] args) {
		Generatrice gen = new Generatrice(100, 10);
		gen.uniforme();
		gen.repartir_class();
		gen.affiche_classes();
		System.out.println("Moyenne : " + gen.moyenne());
		System.out.println("Ecart type : " + Math.sqrt(gen.variance_uniforme()));
	}

}
