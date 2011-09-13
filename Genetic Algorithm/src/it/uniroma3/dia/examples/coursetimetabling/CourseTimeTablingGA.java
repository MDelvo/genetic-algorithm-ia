package it.uniroma3.dia.examples.coursetimetabling;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import it.uniroma3.dia.Chromosome;
import it.uniroma3.dia.Config;
import it.uniroma3.dia.Population;
import it.uniroma3.dia.World;

public class CourseTimeTablingGA extends Population {
	public static void main(String[] args) throws Exception {				
		int numberOfLessonsInADay = 6;
		int numberOfLessonDays = 5; // da lunedi a venerdi 5 giorni
		int numberOfClassroom = 6;
		int dimensionOfChromosome = numberOfLessonsInADay*numberOfLessonDays*numberOfClassroom;
		
		int numberOfGenerations = 1000;
		//lunghezza cromosomi, numero abitanti, % crossover, % mutazione sul gene, tipo di crossover (non impl), numero di generazioni, numero di popolazioni
		Config config = new Config(dimensionOfChromosome, 100, 95, 5, 2, numberOfGenerations, 1);
		World world = new World(config, CourseTimeTablingGA.class);	
		world.evolve();			
		//world.evolveBestOfAllPopulations();
	}

	private List<CourseClass> courseClasses = new LinkedList<CourseClass>();
	private List<Classroom> classRooms = new LinkedList<Classroom>();
	private String[] hours = new String[]{ "8.00-9.30", "9.45-11.15", "11.30-13.00", "14.00-15.30", "15.45-17.15", "17.30-19.00" };
	private List<Faculty> faculties = new LinkedList<Faculty>(); // non hanno corsi in comune <_<
	private int numberOfLessonDays = 5;
	
	public CourseTimeTablingGA() {
		super();
		// Init model
		// Ingegneria Informatica e Ingegneria Gestionale e dell'Automazione 1° Anno Magistrale 2010-2011 Uniroma3
		Professor prof1 = new Professor(1, "Di Battista");
		Professor prof2 = new Professor(2, "Giunta");
		Professor prof3 = new Professor(3, "Nicosia");
		Professor prof4 = new Professor(4, "Cialdea");
		Professor prof5 = new Professor(5, "Ulivi");
		Professor prof6 = new Professor(6, "Adacher");
		Professor prof7 = new Professor(7, "Gasparri");
		Professor prof8 = new Professor(8, "Protto");

		Classroom room1 = new Classroom(1, "N13A", 100);
		Classroom room2 = new Classroom(2, "N1", 150);
		Classroom room3 = new Classroom(3, "DS3A", 50);
		Classroom room4 = new Classroom(4, "N7", 50);
		Classroom room5 = new Classroom(5, "N3", 50);
		Classroom room6 = new Classroom(6, "N10", 100);
		
		classRooms.add(room1);
		classRooms.add(room2);
		classRooms.add(room3);
		classRooms.add(room4);
		classRooms.add(room5);
		classRooms.add(room6);
		
		Faculty faculty1 = new Faculty(1, "Ingegneria Informatica", 100);
		Faculty faculty2 = new Faculty(2, "Ingegneria Gestionale e dell'Automazione", 50);

		List<Faculty> first = new LinkedList<Faculty>();
		List<Faculty> second = new LinkedList<Faculty>();		
		
		first.add(faculty1);
		second.add(faculty2);
		faculties.add(faculty1);
		faculties.add(faculty2);
		
		CourseClass course1 = new CourseClass(1,"IRC Infrastrutture delle reti di calcolatori", prof1, 4, first);
		CourseClass course2 = new CourseClass(2,"TWL Telecomunicazioni Wireless", prof2, 2, first);
		CourseClass course3 = new CourseClass(3,"IT Informatica Teorica", prof1, 5, first);
		CourseClass course4 = new CourseClass(4,"RO2 Ricerca Operativa II", prof3, 2, first);
		CourseClass course5 = new CourseClass(5,"LSI Logica e Sistemi Informatici", prof4, 2, first);
		CourseClass course6 = new CourseClass(6,"CF Controllo Fuzzy", prof5, 2, second);
		CourseClass course7 = new CourseClass(7,"AI Automazione Industriale", prof6, 4, second);
		CourseClass course8 = new CourseClass(8,"TSC Teoria dei Sistemi e del Controllo", prof7, 4, second);
		CourseClass course9 = new CourseClass(9,"EO Elementi di Organizzazione", prof8, 2, second);
		CourseClass course10 = new CourseClass(10,"OC Ottimizzazione Combinatoria", prof3, 2, second);
		
		courseClasses.add(course1);
		courseClasses.add(course2);
		courseClasses.add(course3);
		courseClasses.add(course4);
		courseClasses.add(course5);
		courseClasses.add(course6);
		courseClasses.add(course7);
		courseClasses.add(course8);
		courseClasses.add(course9);
		courseClasses.add(course10);
	}

	@Override
	public void generateFirstPopulation() {
		for(int i = 0; i<config.getDimensionOfPopulation(); i++){
			this.chromosomes[i] = new Chromosome(config.getDimensionOfChromosome());
			
			for(int j = 0; j<config.getDimensionOfChromosome(); j++)
				this.chromosomes[i].setGene("0", j);
			
			for(CourseClass course : courseClasses){
				for(int j = 0;j<course.getDuration();j++)
				{
					int x = (int)(Math.random()*config.getDimensionOfChromosome() );
					while(!this.chromosomes[i].getGenes(x).equals("0")){
						x = (int)(Math.random()*config.getDimensionOfChromosome() );
					}
					
					this.chromosomes[i].setGene(String.valueOf(course.getId()), x);
				}
			}	
		}			
	}
	
	@Override
	public double computeFitness(Chromosome chromosome) {
		String[] genes = chromosome.getGenes();
		
		int professorUniqueCourse = 0;
		int courseInSuitableRoom = 0;
		int classHasRightDuration = 0;
		int oneLessonInHourForFaculty = 0;
		
		for(CourseClass courseClass : courseClasses){
			int lessons = 0;
			for(int i = 0; i < genes.length; i++){
				int idClass = Integer.parseInt(genes[i]);
				if(courseClass.getId()==idClass)
					lessons++;
			}
			
			if(lessons==courseClass.getDuration())
				classHasRightDuration++;
		}
		
		for(Faculty faculty : faculties){
			for(int j = 0; j<hours.length; j++){
					for(int x = 0; x<numberOfLessonDays; x++){	
						int numberOfLessons = 0;
						for(Classroom room : classRooms){							
						int index = (room.getId()-1)*hours.length + hours.length*classRooms.size()*x + j;
						int courseClass = Integer.parseInt(chromosome.getGenes(index));	
						if(courseClass!=0){
							CourseClass course = courseClasses.get(courseClass-1);
							if(course.getFaculties().contains(faculty))
								numberOfLessons++;
						}
						
						if(numberOfLessons==1)
							oneLessonInHourForFaculty++;
						
						/*if(index>0){
							int prevCourseClass = Integer.parseInt(chromosome.getGenes(index-1));	
							if(prevCourseClass!=0)
								contiguosLessons++;
						}
						if(index<genes.length-1)
						{
							int nextCourseClass = Integer.parseInt(chromosome.getGenes(index+1));	
							if(nextCourseClass!=0)
								contiguosLessons++;
						}*/
					}
				}			
			}
		}
		
		for(int i = 0; i < genes.length; i++){
			int idClass = Integer.parseInt(genes[i]);
			if(idClass!=0)
			{
				//String shortName = courseClasses.get(idClass-1).getShortName();
				int idRoom = (i%(hours.length*classRooms.size())/6);
				if( classRooms.get(idRoom).getSize()>=courseClasses.get(idClass-1).getSize() )
					courseInSuitableRoom++;
			}
		}
		
		return classHasRightDuration*1000+oneLessonInHourForFaculty*10+courseInSuitableRoom*10+professorUniqueCourse;
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
	
	@Override
	public String bestChromosomeDecode(){
		String s = Calendar.printCalendar(this.bestChromosome(), faculties, classRooms, courseClasses, hours, numberOfLessonDays);
		return s;
	}
}
