package it.uniroma3.dia.examples.coursetimetabling;

import it.uniroma3.dia.Chromosome;
import it.uniroma3.dia.Population;
import it.uniroma3.dia.World;
import it.uniroma3.dia.events.EventFinishGA;

public class CourseTimeTablingGA extends Population {
	public static void main(String[] args) throws Exception {				
		World world = new World(CourseTimeTablingGA.class);	
		world.evolve();			
	}

	private Calendar calendar;
	
	public CourseTimeTablingGA() {
		super();
		calendar = new Calendar();
	}

	@Override
	public void generateFirstPopulation() {
		int dimensionOfChromosome = calendar.getDimensionOfChromosome();
				
		for(int i = 0; i<config.getDimensionOfPopulation(); i++){
			this.chromosomes[i] = new Chromosome(dimensionOfChromosome);
			
			for(int j = 0; j<dimensionOfChromosome; j++)
				this.chromosomes[i].setGene("0", j);
			
			calendar.fillInitialSolution(chromosomes[i]);
		}			
	}
	
	@Override
	public double computeFitness(Chromosome chromosome) {
		return calendar.computeFitness(chromosome);
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
	public void populationEvolutionFinished(EventFinishGA evt) {
		if(!this.isInEvolution())
			System.out.println("Best in Population "+this.nameOfPopulation+" in "+evt.getNumberOfGenerations()+" generations : "+calendar.printCalendar(this.bestChromosome()));
	}

	@Override
	public boolean bestSolutionIsSatisfactory(Chromosome chromosome) {
		return true;
	}
}
