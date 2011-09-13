package it.uniroma3.dia;

public class GeneticAlgorithm implements Runnable {
	
	private Config config;
	private Population population;
	
	public GeneticAlgorithm(Population population) {
		this.config = population.getConfig();
		this.population = population;
	}
	
	@Override
	public void run() {
		if(population.chromosomes==null){
			population.initPopulation();
			population.generateFirstPopulation();
		}
		
		population.evaluateFitnessOfPopulation();
		
		for(int i = 0; i<this.config.getNumberOfGeneration(); i++){
			population.sortPopulation();
			
			Chromosome[] matingPool = population.generateMatingPool();
			Utils.randomizeArray(matingPool);
			
			Chromosome[] newGeneration = new Chromosome[matingPool.length];
			for(int j = 0; j<newGeneration.length; j++)
				newGeneration[j] = matingPool[j].clone();
			
			for(int j = 0; j<=newGeneration.length/2 && newGeneration.length>=2; j+=2){
				population.doCrossover(newGeneration[j], newGeneration[j+1]);

				population.doMutation(newGeneration[j]);
				population.doMutation(newGeneration[j+1]);

				newGeneration[j].setFitnessValue(population.computeFitness(newGeneration[j]));
				newGeneration[j+1].setFitnessValue(population.computeFitness(newGeneration[j+1]));
			}
			
			population.replacePopulation(newGeneration);			
		}	
		
		System.out.println("Best in Population "+population.numberOfPopulation+": "+population.bestChromosomeDecode());
	}
}
