package it.uniroma3.dia;

import it.uniroma3.dia.events.EventFinishGA;

public class GeneticAlgorithm implements Runnable {
	
	private Config config;
	private Population population;
	
	public GeneticAlgorithm(Population population) {
		this.config = population.getConfig();
		this.population = population;
	}
	
	@Override
	public void run() {
		if(this.population.getChromosomes()==null){
			this.population.initPopulation();
			this.population.generateFirstPopulation();
		}
		
		this.population.evaluateFitnessOfPopulation();
		int i;
		for(i = 0; i<this.config.getNumberOfGenerations() || !this.population.bestSolutionIsSatisfactory(this.population.bestChromosome()); i++){
			this.population.sortPopulation();
			
			Chromosome[] matingPool = this.population.generateMatingPool();
			this.population.randomizeChromosome(matingPool);
			
			Chromosome[] newGeneration = new Chromosome[matingPool.length];
			for(int j = 0; j<newGeneration.length; j++){
				newGeneration[j] = matingPool[j].clone();
				newGeneration[j].setGeneration(i+1);
			}
			
			for(int j = 0; j<=newGeneration.length/2 && newGeneration.length>=2; j+=2){
				this.population.doCrossover(newGeneration[j], newGeneration[j+1]);

				this.population.doMutation(newGeneration[j]);
				this.population.doMutation(newGeneration[j+1]);

				newGeneration[j].setFitnessValue(this.population.computeFitness(newGeneration[j]));
				newGeneration[j+1].setFitnessValue(this.population.computeFitness(newGeneration[j+1]));
			}
			
			this.population.replacePopulation(newGeneration);	

			this.population.fireMyEvent(new EventFinishGA(this, this.population, i));
		}	
		
		try { Thread.sleep(100); } catch (InterruptedException e) {}
		
		this.population.setInEvolution(false);

		this.population.fireMyEvent(new EventFinishGA(this, this.population, i));
	}
}
