package it.uniroma3.dia;

public class Chromosome implements Comparable<Object> {
	private String[] genes;
	private double fitnessValue;
	private int generation;
	
	public Chromosome(int dimensionOfChromosome){
		this.genes = new String[dimensionOfChromosome];
		this.generation = 1;
	}
	
	public Chromosome(int dimensionOfChromosome, int generation){
		this.genes = new String[dimensionOfChromosome];
		this.generation = generation;
	}

	public void setGenes(String[] genes) {
		this.genes = genes;
	}

	public String[] getGenes() {
		return this.genes;
	}
	
	public String getGenes(int i) {
		return this.genes[i];
	}
	
	public void setGene(String gene, int positionOfGene) {
		this.genes[positionOfGene] = gene;
	}

	public double getFitnessValue() {
		return this.fitnessValue;
	}

	public void setFitnessValue(double fitnessValue) {
		this.fitnessValue = fitnessValue;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	@Override
	public String toString() {
		String strGenes = "";
		for(int i = 0;i<this.genes.length; i++)
			strGenes+=genes[i];
		strGenes += ", Generation: "+this.generation+", FitnessValue: "+this.fitnessValue;
		return strGenes;
	}
	
	@Override
	public int compareTo(Object arg) {
		Chromosome tmp = (Chromosome)arg;
		if(this.fitnessValue < tmp.fitnessValue)
			return -1;
		else if(this.fitnessValue > tmp.fitnessValue)
			return 1;
		else if(this.generation<tmp.generation)
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
