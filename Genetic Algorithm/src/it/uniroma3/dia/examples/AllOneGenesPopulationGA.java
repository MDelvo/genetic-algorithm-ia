package it.uniroma3.dia.examples;

import java.util.Arrays;

import it.uniroma3.dia.Chromosome;
import it.uniroma3.dia.Config;
import it.uniroma3.dia.Population;
import it.uniroma3.dia.World;

/*
 * Prima semplice implementazione di popolazione dove l'elemento migliore è quello che contiene tra i geni un maggior numero di "1".
 * L'obiettivo dell'algoritmo è quindi quello di far evolvere la popolazione per avere un abitante con tutti "1".
 */

public class AllOneGenesPopulationGA extends Population {

	public static void main(String[] args) throws Exception {		
		World world = new World(AllOneGenesPopulationGA.class);	
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
		int dimensionOfChromosome = chromosome1.getGenes().length ;
		int NPoint = config.getTypeOfCrossover();
		int[] points = new int[NPoint];
		
		for(int i = 0; i<points.length; i++)
		{
			int crossoverPoint = (int) (Math.random()* (dimensionOfChromosome - 2));
			points[i] = crossoverPoint;
		}	
		Arrays.sort(points);
		
		int lastI = 0;
		boolean pari = true;
		
		for(int i = 0; i<points.length; i++)
		{
			for(int j = lastI; j<points[i]; j++)
			{
				if(!pari){	
					String temp = chromosome1.getGenes(j);
					chromosome1.setGene(chromosome2.getGenes(j), j);
					chromosome2.setGene(temp, j);	
				}			
			}
			pari=!pari;
			lastI = points[i];
		}	
	}

	@Override
	public void doMutation(Chromosome chromosome) {
		String[] genes = chromosome.getGenes();
		for(int i = 0; i<genes.length; i++){
			double x = Math.random()*100 + 1;
			if( config.getProbabilityOfMutation() >= x )
			{
				if(genes[i].equals("1"))
					chromosome.setGene("0", i);
				else
					chromosome.setGene("1", i);	
			}
		}
	}
}
