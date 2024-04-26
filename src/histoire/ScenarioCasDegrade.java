package histoire;

import villagegaulois.Etal;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal();
		etal.acheterProduit(2, null);
		etal.acheterProduit(2, null);
		System.out.println("Fin de test");
	}
}
