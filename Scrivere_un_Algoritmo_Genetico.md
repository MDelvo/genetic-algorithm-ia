# Struttura della libreria #

La libreria è composta dalle seguenti classi:

  * una classe Mondo (World.java), che crea diverse popolazioni prestabilite e che è in grado di avviare l'evoluzione di tutte le popolazioni.

  * una classe Cromosoma (Chromosome.java) che rappresenta il cromosoma con i geni in un array di stringhe e il valore della funzione di fitness relativa al cromosoma.

  * una classe astratta Popolazione (Population.java) che rappresenta la popolazione e le caratteristiche di evoluzione (astratte e quindi da implementare), contiene i metodi astratti per generare gli individui iniziali della popolazione, la valutazione del cromosoma in termini di fitness, il crossover e la mutazione. In pratica ogni algoritmo genetico deve estendere la classe popolazione e ridefinire queste funzioni a seconda dello scopo dell'algoritmo.

  * una classe GeneticAlgorithm.java che viene istanziata ed eseguita da una istanza di popolazione che avvia il vero e proprio algoritmo genetico con i passi standard di un algoritmo genetico (generazione random della prima generazione di individui, valutazione in termini di fitness, scelta elementi migliori sottoposti ad una probabilità di crossover (mating pool), crossover, mutazione dell'individuo (sul singolo gene con una determinata probabilità), rimpiazzamento per la definizione della nuova generazione e così via un numero prestabilito di volte).

# Scrivere un Algoritmo Genetico #

Per implementare un algoritmo genetico è necessario e sufficiente estendere la classe Population.
Le funzioni obbligatorie da sovrascrivere sono:

  * **generateFirstPopulation()**: funzione che genererà la popolazione iniziale.

  * **computeFitness()**: funzione che serve a capire quali sono gli individui migliori secondo determinati parametri, migliore è la funzione di fitness e meglio lavorerà l'algoritmo genetico.

  * **bestSolutionIsSatisfactory()**: funzione che serve a determinare se la soluzione ottima trovata dall'algoritmo è soddisfacente\ammissibile.

Le altre funzioni se non sovrascritte opereranno con funzioni di default. Per modificare le altre funzioni si dia uno sguardo alle loro implementazioni nella classe Population.java della libreria. Nel progetto sono presenti degli esempi concreti di implementazione di algoritmi genetici più o meno complessi, come il problema TSP e il problema (semplificato) del Course TimeTabling.

Nella directory del progetto deve essere presente un file di configurazione chiamato config.properties avente il seguente schema:

```
NumberOfPopulations=5
NPointCrossover=2
ProbabilityOfMutation=5
ProbabilityOfCrossover=95
DimensionOfPopulation=100
NumberOfGenerations=1000
NumberOfGenerationsOfMergedPopulations=1000
```

Il codice di base per programmare il proprio algoritmo genetico è il seguente:

```
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
```



# Download e Import della Libreria #

Per il download della libreria si visiti la sezione download del progetto e si scarichi l'ultima libreria .jar rilasciata. Una volta scaricata per importare la libreria su eclipse basta andare sul progetto, tasto destro, build path, configure build path, andare sul tab libraries, cliccare sul bottone add external JARs e selezionare il file scaricato. Per verificare che tutto funziona correttamente creare la classe sopra riportata con il medesimo contenuto, creare il file config.properties e provare ad avviare il progetto, se non vengono riscontrati errori è tutto configurato opportunamente per scrivere il proprio Algoritmo Genetico.