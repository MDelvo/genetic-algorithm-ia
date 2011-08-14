package it.uniroma3.dia;

public class Chromosome implements Comparable<Object> {
	String[] genes;
	double fitnessValue;
	
	public Chromosome(int dimensionOfChromosome){
		genes = new String[dimensionOfChromosome];
	}

	public void setGenes(String[] genes) {
		this.genes = genes;
	}

	public String[] getGenes() {
		return genes;
	}
	
	public void setGene(String gene, int positionOfGene) {
		this.genes[positionOfGene] = gene;
	}
		
	public void setGeneFromStr(String gene) {
		for(int i = 0; i<gene.length(); i++)
			this.genes[i] = gene.substring(i, i+1);
	}
	
	public String strGenes(){
		String strgenes = "";
		for(int i = 0;i<this.genes.length; i++)
			strgenes+=genes[i];
		return strgenes;
	}

	public double getFitnessValue() {
		return fitnessValue;
	}

	public void setFitnessValue(double fitnessValue) {
		this.fitnessValue = fitnessValue;
	}

	@Override
	public String toString() {
		String strgenes = "";
		for(int i = 0;i<this.genes.length; i++)
			strgenes+=genes[i];
		strgenes += ", "+this.fitnessValue;
		return strgenes;
	}
	@Override
	public int compareTo(Object arg) {
		Chromosome tmp = (Chromosome)arg;
		if(this.fitnessValue < tmp.fitnessValue)
			return -1;
		else if(this.fitnessValue > tmp.fitnessValue)
			return 1;
		
		return 0; 
	}

	public Chromosome clone() {
		Chromosome chromosome = new Chromosome(this.genes.length);
		chromosome.setGenes(this.genes.clone());
		chromosome.setFitnessValue(this.fitnessValue);		
		return chromosome;
	}
}
