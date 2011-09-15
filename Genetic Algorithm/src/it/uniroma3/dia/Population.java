package it.uniroma3.dia;

import it.uniroma3.dia.events.EventFinishGA;
import it.uniroma3.dia.events.LogEventGA;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.EventListenerList;

public abstract class Population {
	
	protected EventListenerList listenerList = new EventListenerList();
	
	protected String nameOfPopulation;
	protected Config config;
	protected Chromosome[] chromosomes;
	protected boolean inEvolution;
	
	public void evolve(){
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(this);
		Thread thread = new Thread(geneticAlgorithm);
		thread.start();
	}
	
	public abstract void generateFirstPopulation();
	
	public abstract double computeFitness(Chromosome chromosome);
	
	public abstract boolean bestSolutionIsSatisfactory(Chromosome chromosome);
	
	public void doCrossover(Chromosome chromosome1, Chromosome chromosome2){
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
	
	public void doMutation(Chromosome chromosome){
		String[] genes = chromosome.getGenes();
		int dimensionOfChromosome = genes.length;
		
		for(int i = 0; i<genes.length; i++){
			double x = Math.random()*100 + 1;
			if( config.getProbabilityOfMutation() >= x )
			{								
				int changeGene = (int) (Math.random()* dimensionOfChromosome);
				String temp = chromosome.getGenes(changeGene);
				chromosome.setGene(genes[i], changeGene);
				chromosome.setGene(temp, i);
			}
		}
	}
	
	public void replacePopulation(Chromosome[] newGeneration)
	{
		Chromosome[] newPopulation = new Chromosome[this.chromosomes.length];
		
		for(int i = 0; i<newGeneration.length; i++)
			newPopulation[i] = newGeneration[i];

		//TODO: add tuning parameter ?
		//this.randomizeChromosome(this.chromosomes);
		
		for(int i = newGeneration.length; i<this.chromosomes.length; i++)
			newPopulation[i] = this.chromosomes[i-newGeneration.length];
		
		this.chromosomes = newPopulation;
	}
	
	public Chromosome[] generateMatingPool()
	{
		int dimensionOfPopulation = this.config.getDimensionOfPopulation();
		int dimensionOfMatingPool = dimensionOfPopulation/2;
		if(dimensionOfMatingPool%2==1)
			dimensionOfMatingPool++;
		
		List<Chromosome> matingPool = new LinkedList<Chromosome>();
		
		for(int i = 0; i<dimensionOfMatingPool; i++)
		{
			double x = Math.random()*100 + 1;
			if(this.config.getProbabilityOfCrossover() >= x )
				matingPool.add(this.chromosomes[i]);
		}
		
		return matingPool.toArray(new Chromosome[0]);
	}
		
	public void initPopulation()
	{
		this.chromosomes = new Chromosome[config.getDimensionOfPopulation()];
	}

	public void evaluateFitnessOfPopulation()
	{
		for(int i = 0; i<this.chromosomes.length; i++)
		{
			double fitnessValue = this.computeFitness(chromosomes[i]);
			this.chromosomes[i].setFitnessValue(fitnessValue);
		}		
	}
	
	public Chromosome bestChromosome(){
		sortPopulation();
		return this.chromosomes[0];
	}
	
	public String bestChromosomeDecode(){
		return bestChromosome().toString();
	}
	
	public void sortPopulation()
	{
		Arrays.sort(this.chromosomes, Collections.reverseOrder());
	}
	
	public void randomizeChromosome(Chromosome[] array)
	{
		for (int i=0; i<array.length; i++) {
		    int randomPosition = (int)(Math.random()*array.length);
		    Chromosome temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
	}
	
	public void addLogEventListener(LogEventGA listener) {
        listenerList.add(LogEventGA.class, listener);
    }
	
	public void removeLogEventListener(LogEventGA listener) {
        listenerList.remove(LogEventGA.class, listener);
    }

    void fireMyEvent(EventFinishGA evt) {
        Object[] listeners = listenerList.getListenerList();
        for (int i=0; i<listeners.length; i+=2) {
            if (listeners[i]==LogEventGA.class) {
                ((LogEventGA)listeners[i+1]).populationEvolutionFinished(evt);
            }
        }
    }
    
	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public String getNumberOfPopulation() {
		return this.nameOfPopulation;
	}

	public void setNumberOfPopulation(String numberOfPopulation) {
		this.nameOfPopulation = numberOfPopulation;
	}

	public Chromosome[] getChromosomes() {
		return chromosomes;
	}
	
	public void setChromosomes(Chromosome[] chromosomes) {
		this.chromosomes = chromosomes;
	}

	public boolean isInEvolution() {
		return inEvolution;
	}

	public void setInEvolution(boolean inEvolution) {
		this.inEvolution = inEvolution;
	}

	@Override
	public String toString() {
		String population = "";
		
		for(int i = 0; i<this.chromosomes.length; i++)
			population+=this.chromosomes[i].toString()+"\n";
		
		return population;
	}
}