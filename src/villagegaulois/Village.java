package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbetals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbetals);
	}	

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur+" cherche un endroit pour vendre "+nbProduit+" "+produit+".");
		int i = marche.trouverEtalLibre();
		marche.utiliserEtal(i, vendeur, produit, nbProduit);
		chaine.append("Le vendeur "+vendeur+" vend des fleurs � l'�tal n�"+i+1+".");
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalsProduit = marche.trouverEtals(produit);
		if (etalsProduit.length==0) {
			chaine.append("Il n'y a pas de vendeur qui propose des "+produit+" au march�.");
		}else if(etalsProduit.length==1){
			chaine.append("Seul le vendeur "+etalsProduit[0].getVendeur()+" propose des fleurs au march�.");
		}else {
			chaine.append("Les vendeurs qui proposent des fleurs sont :");
			for (int i = 0; i < etalsProduit.length; i++) {
				chaine.append("\n- "+etalsProduit[i].getVendeur().getNom());
			}
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(marche.trouverVendeur(vendeur).libererEtal());
		return chaine.toString();
	}
	
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le march� du village "+getNom()+" possede plusieurs etals : \n");
		chaine.append(marche.afficherMarche());
		return chaine.toString();
	}
	
	public class Marche {
		private Etal[] etals;
		
		public Marche(int nbetals) {
			etals = new Etal[nbetals];
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		public int trouverEtalLibre() {
			int i=0;
			int l = etals.length;
			while (etals[i].isEtalOccupe()==true && i<l) {
				i++;
			}
			if (i<l) {
				return i;
			}else {
				return -1;
			}
		}
		
		 public Etal[] trouverEtals(String produit) {
			 Etal[] etalsProduit = new Etal[etals.length];
			 for (int i=0; i<etals.length; i++) {
				 if (etals[i].contientProduit(produit)) {
					 etalsProduit[i] = etals[i];
				 }
			 }
			 return etalsProduit;
		 }
		 
		 public Etal trouverVendeur(Gaulois gaulois) {
			 int i=0;
			 int l = etals.length;
			 while (i<l) {
				if (etals[i].isEtalOccupe() && etals[i].getVendeur()==gaulois) {
					return etals[i];
				}
				i++;
			 }
			 return null;
		 }
		 
		 public String afficherMarche() {
			 StringBuilder chaine = new StringBuilder();
			 int compt = 0;
			 for (int i=0; i<etals.length; i++) {
				 if (etals[i].isEtalOccupe()) {
					 etals[i].afficherEtal();
				 }else {
					 compt++;
				 }
			 }
			 if (compt>0) {
				 chaine.append("Il reste " +compt + " �tals non utilis�s dans le march�.\n");
			 }
			 return chaine.toString();
		 }
	}
}