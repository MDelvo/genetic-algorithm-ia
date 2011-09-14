package it.uniroma3.dia;

public class World {
	private Config config;
	private Population[] populations;
	private Class<? extends Population> clazz;
	
	public World(Class<? extends Population> clazz) throws Exception{
		this.config = new Config();
		this.clazz = clazz;
		this.populations = new Population[config.getNumberOfPopulations()];
		
		for(int i = 0; i<this.populations.length; i++)
		{
			this.populations[i] = clazz.newInstance();
			this.populations[i].setConfig(config);
			this.populations[i].setNumberOfPopulation(String.valueOf(i+1));
		}
	}
	
	public void evolve(){
		for(int i = 0; i<this.populations.length; i++)
			this.populations[i].evolve();
	}

	public void evolveBestOfAllPopulations() throws Exception{
		Population population = clazz.newInstance();
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
}
