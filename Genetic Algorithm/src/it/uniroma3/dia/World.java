package it.uniroma3.dia;

public class World {
	private Config config;
	private Population[] populations;
	private Class<? extends Population> clazz;
	
	public World(Class<? extends Population> clazz) throws Exception{
		this.config = new Config();
		this.clazz = clazz;
		this.populations = new Population[config.getNumberOfPopulations()];
		
		for(int i = 0; i<populations.length; i++)
		{
			populations[i] = clazz.newInstance();
			populations[i].setConfig(config);
			populations[i].setNumberOfPopulation(i+1);
		}
	}
	
	public void evolve(){
		for(int i = 0; i<populations.length; i++)
			populations[i].evolve();
	}

	public void evolveBestOfAllPopulations() throws Exception{
		Population population = clazz.newInstance();
		population.setConfig(config);
		population.setNumberOfPopulation(9999);

		Chromosome[] chromosomes = new Chromosome[config.getDimensionOfPopulation()];
		
		for(int i = 0;i<config.getDimensionOfPopulation(); i++){
			int index = i%populations.length;
			Chromosome bestMutated = populations[index].bestChromosome().clone();
			population.doMutation(bestMutated);
			chromosomes[i] = bestMutated;
		}
		population.setChromosomes(chromosomes);
		population.evolve();
		
	}	
}
