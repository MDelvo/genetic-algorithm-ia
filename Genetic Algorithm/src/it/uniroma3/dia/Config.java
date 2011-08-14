package it.uniroma3.dia;

public class Config {
	private int dimensionOfChromosome;
	private int dimensionOfPopulation;
	private double probabilityOfCrossover;
	private double probabilityOfMutation;
	private int typeOfCrossover;
	private int numberOfGenerations;
	private int numberOfPopulations;
	
	public Config(int dimensionOfChromosome, int dimensionOfPopulation,
			double probabilityOfCrossover, double probabilityOfMutation,
			int typeOfCrossover, int numberOfGenerations, int numberOfPopulations) {
		this.dimensionOfChromosome = dimensionOfChromosome;
		this.dimensionOfPopulation = dimensionOfPopulation;
		this.probabilityOfCrossover = probabilityOfCrossover;
		this.probabilityOfMutation = probabilityOfMutation;
		this.typeOfCrossover = typeOfCrossover;
		this.numberOfGenerations = numberOfGenerations;
		this.numberOfPopulations = numberOfPopulations;
	}
	
	public int getDimensionOfChromosome() {
		return dimensionOfChromosome;
	}
	public void setDimensionOfChromosome(int dimensionOfChromosome) {
		this.dimensionOfChromosome = dimensionOfChromosome;
	}
	public int getDimensionOfPopulation() {
		return dimensionOfPopulation;
	}
	public void setDimensionOfPopulation(int dimensionOfPopulation) {
		this.dimensionOfPopulation = dimensionOfPopulation;
	}
	public double getProbabilityOfCrossover() {
		return probabilityOfCrossover;
	}
	public void setProbabilityOfCrossover(double probabilityOfCrossover) {
		this.probabilityOfCrossover = probabilityOfCrossover;
	}
	public double getProbabilityOfMutation() {
		return probabilityOfMutation;
	}
	public void setProbabilityOfMutation(double probabilityOfMutation) {
		this.probabilityOfMutation = probabilityOfMutation;
	}
	public int getTypeOfCrossover() {
		return typeOfCrossover;
	}
	public void setTypeOfCrossover(int typeOfCrossover) {
		this.typeOfCrossover = typeOfCrossover;
	}
	public int getNumberOfGeneration() {
		return numberOfGenerations;
	}
	public void setNumberOfGeneration(int numberOfGeneration) {
		this.numberOfGenerations = numberOfGeneration;
	}
	public int getNumberOfPopulations() {
		return numberOfPopulations;
	}
	public void setNumberOfPopulations(int threadNumber) {
		this.numberOfPopulations = threadNumber;
	}
}
