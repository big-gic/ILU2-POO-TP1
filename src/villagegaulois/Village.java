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
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
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
			 Etal[] etals_produit = new Etal[etals.length];
			 for (int i=0; i<etals.length; i++) {
				 if (etals[i].contientProduit(produit)) {
					 etals_produit[i] = etals[i];
				 }
			 }
			 return etals_produit;
		 }
		 
		 public Etal trouverVendeur(Gaulois gaulois) {
			 int i=0;
			 int l = etals.length;
			 while (i<l) {
				if (etals[i].isEtalOccupe()==true && etals[i].getVendeur()==gaulois) {
					return etals[i];
				}
				i++;
			 }
			 return null;
		 }
		 
		 public void afficherMarche() {
			 int compt = 0;
			 for (int i=0; i<etals.length; i++) {
				 if (etals[i].isEtalOccupe()==true) {
					 etals[i].afficherEtal();
				 }else {
					 compt++;
				 }
			 }
			 if (compt>0) {
				 System.out.println("Il reste " +compt + " étals non utilisés dans le marché.\n");
			 }
		 }
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur+" cherche un endroit pour vendre "+nbProduit+" "+produit+".");
		int i = marche.trouverEtalLibre();
		marche.utiliserEtal(i, vendeur, produit, nbProduit);
		chaine.append("Le vendeur "+vendeur+" vend des fleurs à l'étal n°"+i+1+".");
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etals_produit = marche.trouverEtals(produit);
		if (etals_produit.length==0) {
			chaine.append("Il n'y a pas de vendeur qui propose des "+produit+" au marché.");
		}else if(etals_produit.length==1){
			chaine.append("Seul le vendeur "+etals_produit[0].getVendeur()+" propose des fleurs au marché.");
		}
	}
	
}