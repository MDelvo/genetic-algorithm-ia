package it.uniroma3.dia;

public abstract class GeneticAlgorithm implements Runnable {
	
	protected Config config;
	protected Chromosome[] population;
	
	public GeneticAlgorithm(Config config){
		population = new Chromosome[config.getDimensionOfPopulation()];
	}
	
	public abstract void generateFirstPopulation();
	
	public abstract double computeFitness(Chromosome chromosome);
	
	public abstract void doCrossover(Chromosome chromosome1, Chromosome chromosome2);

}
