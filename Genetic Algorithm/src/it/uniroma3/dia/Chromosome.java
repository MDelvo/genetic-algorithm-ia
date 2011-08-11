package it.uniroma3.dia;

public class Chromosome {
	String[] genes;
	double fitnessValue;
	
	public Chromosome(int dimensionOfChromosome){
		genes = new String[dimensionOfChromosome];
	}

	public String[] getGenes() {
		return genes;
	}

	public void setGene(String gene, int positionOfGene) {
		this.genes[positionOfGene] = gene;
	}

	public double getFitnessValue() {
		return fitnessValue;
	}

	public void setFitnessValue(int fitnessValue) {
		this.fitnessValue = fitnessValue;
	}
}
