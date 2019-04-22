package model;

public class MaintTest {

	public static void main(String[] args) {
		/*Test loi uniforme*/
//		Generatrice gen = new Generatrice(1000, 20, TypeDistribution.UNIFORME);
//		gen.getDistribution(null, null);
//		gen.repartir_class();
//		gen.affiche_classes();
//		gen.test_ki2();
//		System.out.println("Moyenne : " + gen.moyenne());
//		System.out.println("Ecart type : " + Math.sqrt(gen.variance_uniforme()));
		
		/*Test loi expo*/
		Generatrice gen_2 = new Generatrice(100, 10, TypeDistribution.EXPONENTIELLE);
		gen_2.getDistribution(0.1, null);
		gen_2.repartir_class();
		gen_2.affiche_classes();
		gen_2.test_ki2();
		System.out.println("Moyenne : " + gen_2.moyenne());
		System.out.println("Ecart type : " + Math.sqrt(gen_2.variance_uniforme()));
	}

}
