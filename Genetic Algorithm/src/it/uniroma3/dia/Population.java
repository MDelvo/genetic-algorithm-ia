package it.uniroma3.dia;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Population {
	
	protected int numberOfPopulation;
	protected Config config;
	protected Chromosome[] chromosomes;
	
	public abstract void generateFirstPopulation();
	
	public abstract double computeFitness(Chromosome chromosome);
	
	public abstract void doCrossover(Chromosome chromosome1, Chromosome chromosome2);	
	
	public abstract void doMutation(Chromosome chromosome);		

	public void evolve(){
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(this);
		geneticAlgorithm.run();
		//Thread thread = new Thread(geneticAlgorithm);
		//thread.start();
	}
	
	public void initPopulation()
	{
		this.chromosomes = new Chromosome[config.getDimensionOfPopulation()];
	}

	public void evaluateFitnessOfPopulation()
	{
		for(int i = 0; i<this.chromosomes.length; i++)
		{
			double fitnessValue = this.computeFitness(chromosomes[i]);
			this.chromosomes[i].setFitnessValue(fitnessValue);
		}		
	}
	
	public Chromosome bestChromosome(){
		sortPopulation();
		return this.chromosomes[0];
	}
	
	public void sortPopulation()
	{
		Arrays.sort(this.chromosomes, Collections.reverseOrder());
	}
	
	public Chromosome[] generateMatingPool()
	{
		int dimensionOfPopulation = this.config.getDimensionOfPopulation();
		int dimensionOfMatingPool = dimensionOfPopulation/2;
		if(dimensionOfMatingPool%2==1)
			dimensionOfMatingPool++;
		
		List<Chromosome> matingPool = new LinkedList<Chromosome>();
		
		for(int i = 0; i<dimensionOfMatingPool; i++)
		{
			double x = Math.random()*100 + 1;
			if(this.config.getProbabilityOfCrossover() >= x )
				matingPool.add(this.chromosomes[i]);
		}
		
		return matingPool.toArray(new Chromosome[0]);
	}
	
	public void replacePopulation(Chromosome[] newGeneration)
	{
		Chromosome[] newPopulation = new Chromosome[this.chromosomes.length];
		
		for(int i = 0; i<newGeneration.length; i++)
			newPopulation[i] = newGeneration[i];

		Utils.randomizeArray(this.chromosomes);
		
		for(int i = newGeneration.length; i<this.chromosomes.length; i++)
			newPopulation[i] = this.chromosomes[i-newGeneration.length];
		
		this.chromosomes = newPopulation;
	}
	
	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public int getNumberOfPopulation() {
		return numberOfPopulation;
	}

	public void setNumberOfPopulation(int numberOfPopulation) {
		this.numberOfPopulation = numberOfPopulation;
	}

	public Chromosome[] getChromosomes() {
		return chromosomes;
	}
	
	public void setChromosomes(Chromosome[] chromosomes) {
		this.chromosomes = chromosomes;
	}

	@Override
	public String toString() {
		String population = "";
		
		for(int i = 0; i<this.chromosomes.length; i++)
			population+=this.chromosomes[i].toString()+"\n";
		
		return population;
	}
}