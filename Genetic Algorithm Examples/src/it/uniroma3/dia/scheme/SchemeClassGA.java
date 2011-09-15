package it.uniroma3.dia.scheme;

import it.uniroma3.dia.Chromosome;
import it.uniroma3.dia.Population;
import it.uniroma3.dia.World;
import it.uniroma3.dia.events.EventFinishGA;
import it.uniroma3.dia.events.LogEventGA;

public class SchemeClassGA extends Population implements LogEventGA{
	
	public static void main(String[] args) throws Exception {		
		World world = new World(SchemeClassGA.class);	
		world.evolve();	
	}
	
	public SchemeClassGA() {
		super();
		this.addLogEventListener(this);
	}

	@Override
	public void generateFirstPopulation() {
		
	}

	@Override
	public double computeFitness(Chromosome chromosome) {
		return 0;
	}

	@Override
	public boolean bestSolutionIsSatisfactory(Chromosome chromosome) {
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
		System.out.println("Best in Popudlation "+this.nameOfPopulation+" in "+evt.getNumberOfGenerations()+" generations : "+this.bestChromosomeDecode());
	}
}
