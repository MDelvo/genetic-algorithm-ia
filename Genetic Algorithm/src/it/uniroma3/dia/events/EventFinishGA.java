package it.uniroma3.dia.events;

import it.uniroma3.dia.Population;

import java.util.EventObject;

public class EventFinishGA extends EventObject {
	private static final long serialVersionUID = 1L;
	private Population population;
	private int numberOfGenerations;
	
	public EventFinishGA(Object source, Population population, int numberOfGenerations) {
        super(source);
        this.population = population;
        this.numberOfGenerations = numberOfGenerations;
    }
	
	public Population getPopulation() {
		return population;
	}

	public int getNumberOfGenerations() {
		return numberOfGenerations;
	}
}