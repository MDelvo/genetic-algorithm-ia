package it.uniroma3.dia.scheme;

import it.uniroma3.dia.Chromosome;
import it.uniroma3.dia.Population;
import it.uniroma3.dia.World;
import it.uniroma3.dia.events.EventFinishGA;

public class SchemeClassGA extends Population{
	
	public static void main(String[] args) throws Exception {		
		World world = new World(SchemeClassGA.class);	
		world.evolve();	
	}
	
	public SchemeClassGA() {
		super();
	}

	@Override
	public void generateFirstPopulation() {
		// TODO Auto-generated method stub
		int dimensionOfChromosome = 10;
		for(int i = 0; i<config.getDimensionOfPopulation(); i++){
			this.chromosomes[i] = new Chromosome(dimensionOfChromosome);
			
			for(int j = 0; j<dimensionOfChromosome; j++){
				this.chromosomes[i].setGene("X", j);
			}
		}
	}

	@Override
	public double computeFitness(Chromosome chromosome) {
		// TODO Auto-generated method stub
		return Math.random();
	}

	@Override
	public boolean bestSolutionIsSatisfactory(Chromosome chromosome) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void doCrossover(Chromosome chromosome1, Chromosome chromosome2) {
		super.doCrossover(chromosome1, chromosome2);
	}

	@Override
	public void doMutation(Chromosome chromosome) {
		super.doMutation(chromosome);
	}
	
	@Override
	public void replacePopulation(Chromosome[] newGeneration){
		super.replacePopulation(newGeneration);
	}
	
	@Override
	public Chromosome[] generateMatingPool(){
		return super.generateMatingPool();
	}

	@Override
	public void populationEvolutionFinished(EventFinishGA evt) {
		if(!this.isInEvolution())
			System.out.println("Best in Population "+this.nameOfPopulation+" in "+evt.getNumberOfGenerations()+" generations : "+this.bestChromosome());
	}
}
