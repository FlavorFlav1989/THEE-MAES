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
//		System.out.println("Ecart type : " + Math.sqrt(gen.variance()));
		
		/*Test loi expo*/
//		Generatrice gen_2 = new Generatrice(100, 10, TypeDistribution.EXPONENTIELLE);
//		gen_2.getDistribution(1.0, null);
//		gen_2.repartir_class();
//		gen_2.affiche_classes();
//		gen_2.test_ki2();
//		System.out.println("Moyenne : " + gen_2.moyenne());
//		System.out.println("Ecart type : " + Math.sqrt(gen_2.variance()));
		
		/*Test loi normale*/
		Generatrice gen_3 = new Generatrice(1000, 10, TypeDistribution.NORMALE);
		gen_3.getDistribution(0.0, 0.1);
		gen_3.repartir_class();
		gen_3.affiche_classes();
		gen_3.test_ki2();
		System.out.println("Moyenne : " + gen_3.moyenne());
		System.out.println("Ecart type : " + Math.sqrt(gen_3.variance()));
	}

}
