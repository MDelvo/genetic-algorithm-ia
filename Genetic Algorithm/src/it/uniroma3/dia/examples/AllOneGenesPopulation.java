package it.uniroma3.dia.examples;

import it.uniroma3.dia.Chromosome;
import it.uniroma3.dia.Config;
import it.uniroma3.dia.Population;
import it.uniroma3.dia.World;

/*
 * Prima semplice implementazione di popolazione dove l'elemento migliore è quello che contiene tra i geni un maggior numero di "1".
 * L'obiettivo dell'algoritmo è quindi quello di far evolvere la popolazione per avere un abitante con tutti "1".
 */

public class AllOneGenesPopulation extends Population {

	public static void main(String[] args) throws Exception {
		
		for(int i = 1; i<=10; i++){
			int numberOfGenerations = i*5;
			System.out.println("NumeroGenerazioni: "+numberOfGenerations);
			//lunghezza cromosomi, numero abitanti, % crossover, % mutazione sul gene, tipo di crossover (non impl), numero di generazioni, numero di popolazioni
			Config config = new Config(50, 100, 95, 5, 1, numberOfGenerations, 5);
			World world = new World(config, AllOneGenesPopulation.class);	
			world.evolve();			
			world.evolveBestOfAllPopulations();
		}
	}
	
	@Override
	public void generateFirstPopulation() {
		for(int i = 0; i<config.getDimensionOfPopulation(); i++){
			this.chromosomes[i] = new Chromosome(config.getDimensionOfChromosome());
			
			for(int j = 0; j<config.getDimensionOfChromosome(); j++){
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
		// only one point crossover:
		String newChromosome1, newChromosome2, strChromosome1, strChromosome2;

        strChromosome1 = chromosome1.strGenes();
        strChromosome2 = chromosome2.strGenes();
        
        int dimensionOfChromosome = strChromosome1.length();
        int crossoverPoint = (int) (Math.random()* (dimensionOfChromosome - 2));

        newChromosome1 = strChromosome1.substring(0, crossoverPoint) + strChromosome2.substring(crossoverPoint, dimensionOfChromosome);
        newChromosome2 = strChromosome2.substring(0, crossoverPoint) + strChromosome1.substring(crossoverPoint, dimensionOfChromosome);
        
        chromosome1.setGeneFromStr(newChromosome1);
        chromosome2.setGeneFromStr(newChromosome2);
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
