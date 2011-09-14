package it.uniroma3.dia.examples.allone;

import it.uniroma3.dia.Chromosome;
import it.uniroma3.dia.Population;
import it.uniroma3.dia.World;

/*
 * Prima semplice implementazione di popolazione dove l'elemento migliore è quello che contiene tra i geni un maggior numero di "1".
 * L'obiettivo dell'algoritmo è quindi quello di far evolvere la popolazione per avere un abitante con tutti "1".
 */

public class AllOneGenesGA extends Population {

	public static void main(String[] args) throws Exception {		
		World world = new World(AllOneGenesGA.class);	
		world.evolve();			
		world.evolveBestOfAllPopulations();
	}
	
	@Override
	public void generateFirstPopulation() {
		int dimensionOfChromosome = 50;
		for(int i = 0; i<config.getDimensionOfPopulation(); i++){
			this.chromosomes[i] = new Chromosome(dimensionOfChromosome);
			
			for(int j = 0; j<dimensionOfChromosome; j++){
				double x = Math.random();
				if(x>0.5)
					this.chromosomes[i].setGene("1", j);
				else
					this.chromosomes[i].setGene("0", j);
			}
		}
	}

	@Override
	public double computeFitness(Chromosome chromosome) {
		int fitness = 0;
		String[] genes = chromosome.getGenes();
		for(int i = 0; i<genes.length; i++)
			if(genes[i].equals("1") )
				fitness++;
		
		return fitness;
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
	public boolean bestSolutionIsSatisfactory(Chromosome chromosome) {
		boolean satisfy = true;
		String[] genes = chromosome.getGenes();
		for(String gene : genes)
		{
			if(gene.equals("0"))
				satisfy = false;
		}
		return satisfy;
	}
}
