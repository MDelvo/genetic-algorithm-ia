package it.uniroma3.dia;

import it.uniroma3.dia.events.EventFinishGA;
import it.uniroma3.dia.events.LogEventGA;

public class World implements LogEventGA {
	private Config config;
	private Population[] populations;
	private Class<? extends Population> clazz;
	
	public World(Class<? extends Population> clazz) throws Exception{
		this.config = new Config();
		this.clazz = clazz;
		this.populations = new Population[config.getNumberOfPopulations()];
		
		for(int i = 0; i<this.populations.length; i++)
		{
			try {
				populations[i] = clazz.newInstance();
			} catch (Exception e) {
				throw new RuntimeException("Error in instantion of a population");
			}
			this.populations[i].setConfig(config);
			this.populations[i].setNumberOfPopulation(String.valueOf(i+1));
			this.populations[i].setInEvolution(true);
			this.populations[i].addLogEventListener(this);
		}
	}
	
	public void evolve(){
		for(int i = 0; i<this.populations.length; i++)
			this.populations[i].evolve();
	}

	public void evolveBestOfAllPopulations() {
		Population population;
		try {
			population = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Error in instantion of a population");
		}
		config.setNumberOfGenerations(config.getNumberOfGenerationsOfMergedPopulations());
		population.setConfig(config);
		population.setNumberOfPopulation("Merged Best Population");

		Chromosome[] chromosomes = new Chromosome[config.getDimensionOfPopulation()];
		
		for(int i = 0;i<config.getDimensionOfPopulation(); i++){
			int index = i % this.populations.length;
			Chromosome bestMutated = this.populations[index].bestChromosome().clone();
			population.doMutation(bestMutated);
			chromosomes[i] = bestMutated;
		}
		population.setChromosomes(chromosomes);
		population.evolve();
	}

	@Override
	public void populationEvolutionFinished(EventFinishGA evt) {
		Population population = evt.getPopulation();
		population.setInEvolution(false);
		
		boolean evolveBestOfAllPopulations = true;
		for(Population tempPopulation : populations){
			if(tempPopulation.isInEvolution()){
				evolveBestOfAllPopulations = false;
				break;
			}
		}
		
		if(evolveBestOfAllPopulations)
			this.evolveBestOfAllPopulations();
	}	
}
