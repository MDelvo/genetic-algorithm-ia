package it.uniroma3.dia.examples.tsp;

import it.uniroma3.dia.Chromosome;
import it.uniroma3.dia.events.EventFinishGA;
import it.uniroma3.dia.Population;
import it.uniroma3.dia.World;
import it.uniroma3.dia.events.LogEventGA;

public class TravellingSalesmanProblemGA extends Population implements LogEventGA {

	public static void main(String[] args) throws Exception {		
		World world = new World(TravellingSalesmanProblemGA.class);	
		world.evolve();	
	}
	
	private int[] cities;
	private int[][] cost;
	private int numberOfCities = 20;
	
	public TravellingSalesmanProblemGA() {
		super();
		this.addLogEventListener(this);
		
		this.cities = new int[numberOfCities];
		for(int i = 0; i<numberOfCities; i++)
			this.cities[i]=i+1;
		
		// Best in Population 1 in 100000 generations : 1312162711720199181068431551114, 0.5197505197505198
		// Best in Population 3 in 10000 generations : 2181071172019951534861114131216, 0.4914004914004914
		// Best in Population Merged Best Population in 100000 generations : 1018431559192017172161213141186, 0.49115913555992136
		// Best in Population Merged Best Population in 10000 generations : 1286115153416271018919117201413, 0.4830917874396135
		this.cost = new int[numberOfCities][];
		this.cost[0] = new int[]{0,877,489,303,884,566,108,374,770,395,953,352,267,468,669,440,2,900,45,253};
		this.cost[1] = new int[]{877,0,254,81,987,765,94,167,331,918,274,854,461,379,811,86,7,134,843,441};
		this.cost[2] = new int[]{489,254,0,68,434,102,734,566,396,602,826,959,285,139,177,659,772,485,403,721};
		this.cost[3] = new int[]{303,81,68,0,625,223,582,53,558,865,512,221,980,408,802,139,267,60,199,562};
		this.cost[4] = new int[]{884,987,434,625,0,181,884,797,265,564,103,809,784,423,152,606,595,991,494,686};
		this.cost[5] = new int[]{566,765,102,223,181,0,759,16,738,41,214,232,807,217,437,473,94,964,619,681};
		this.cost[6] = new int[]{108,94,734,582,884,759,0,883,597,39,747,299,706,852,665,289,473,667,471,482};
		this.cost[7] = new int[]{374,167,566,53,797,16,883,0,579,298,246,173,771,252,349,543,833,678,882,543};
		this.cost[8] = new int[]{770,331,396,558,265,738,597,579,0,770,876,552,525,494,941,574,957,303,122,751};
		this.cost[9] = new int[]{395,918,602,865,564,41,39,298,770,0,610,687,276,873,878,658,816,36,553,669};
		this.cost[10] = new int[]{953,274,826,512,103,214,747,246,876,610,0,278,411,239,852,797,122,652,793,424};
		this.cost[11] = new int[]{352,854,959,221,809,232,299,173,552,687,278,0,17,90,398,194,299,217,475,666};
		this.cost[12] = new int[]{267,461,285,980,784,807,706,771,525,276,411,17,0,11,524,538,421,946,457,246};
		this.cost[13] = new int[]{468,379,139,408,423,217,852,252,494,873,239,90,11,0,537,343,236,229,688,261};
		this.cost[14] = new int[]{669,811,177,802,152,437,665,349,941,878,852,398,524,537,0,971,160,854,508,604};
		this.cost[15] = new int[]{440,86,659,139,606,473,289,543,574,658,797,194,538,343,971,0,483,640,162,941};
		this.cost[16] = new int[]{2,7,772,267,595,94,473,833,957,816,122,299,421,236,160,483,0,625,322,12};
		this.cost[17] = new int[]{900,134,485,60,991,964,667,678,303,36,652,217,946,229,854,640,625,0,991,899};
		this.cost[18] = new int[]{45,843,403,199,494,619,471,882,122,553,793,475,457,688,508,162,322,991,0,90};
		this.cost[19] = new int[]{253,441,721,562,686,681,482,543,751,669,424,666,246,261,604,941,12,899,90,0};
		
		
		/*int maxCost = 1000;
		this.cost = new int[numberOfCities][];
		for(int i = 0; i<numberOfCities; i++)
		{
			this.cost[i]=new int[numberOfCities];
			for(int j = 0; j<numberOfCities; j++){
				if(i>j){
					int cost = (int)(Math.random()*maxCost+1);
					this.cost[i][j] = cost;
					this.cost[j][i] = cost;
				}
			}
		}
		
		for(int i = 0; i<numberOfCities; i++)
		{
			System.out.print("this.cost["+i+"] = new int[]{");
			for(int j = 0; j<numberOfCities; j++){
				System.out.print(this.cost[i][j]+",");
			}
			System.out.print("};");
			System.out.println();
		}*/
	}

	@Override
	public void generateFirstPopulation() {
		int dimensionOfChromosome = this.cities.length;
		for(int i = 0; i<config.getDimensionOfPopulation(); i++){
			this.chromosomes[i] = new Chromosome(dimensionOfChromosome);
			
			int[] temp = this.cities.clone();
			randomizeArray(temp);
			
			for(int j = 0; j<dimensionOfChromosome; j++){
				this.chromosomes[i].setGene(String.valueOf(temp[j]), j);
			}
		}
	}

	@Override
	public double computeFitness(Chromosome chromosome) {
		String[] genes = chromosome.getGenes();
		int costPath = 0;
		int lastCost = this.cost[Integer.parseInt(genes[0])-1][Integer.parseInt(genes[genes.length-1])-1];
		
		for(int i = 0; i<numberOfCities-1; i++ ){
			costPath+=this.cost[Integer.parseInt(genes[i])-1][Integer.parseInt(genes[i+1])-1];
		}
		
		costPath += lastCost;
		
		int mult = 1;
		if(this.bestSolutionIsSatisfactory(chromosome))
			mult=1000;
		
		return mult*(1./costPath);
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
		String[] genes = chromosome.getGenes();
		boolean satisfy = true;
		
		for(int i = 0; i<numberOfCities && satisfy; i++)
		{
			satisfy = false;
			for(int j = 0; j<genes.length && !satisfy; j++){
				if(cities[i]==Integer.valueOf(genes[j]))
					satisfy = true;
			}
		}
		
		return satisfy;
	}
	
	public void randomizeArray(int[] array)
	{
		for (int i=0; i<array.length; i++) {
		    int randomPosition = (int)(Math.random()*array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
	}

	@Override
	public void populationEvolutionFinished(EventFinishGA evt) {
		System.out.println("Best in Population "+this.nameOfPopulation+" in "+evt.getNumberOfGenerations()+" generations : "+this.bestChromosomeDecode());
	}
}
