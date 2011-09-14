package it.uniroma3.dia;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
	private int dimensionOfPopulation;
	private int probabilityOfCrossover;
	private int probabilityOfMutation;
	private int typeOfCrossover;
	private int numberOfGenerations;
	private int numberOfPopulations;

	public Config() {
		
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("config.properties"));
		} catch (Exception e) { throw new RuntimeException("Error when loading config file"); }
		
		this.dimensionOfPopulation = Integer.parseInt(properties.get("dimensionOfPopulation").toString());
		this.probabilityOfCrossover = Integer.parseInt(properties.get("probabilityOfCrossover").toString());
		this.probabilityOfMutation = Integer.parseInt(properties.get("probabilityOfMutation").toString());
		this.typeOfCrossover = Integer.parseInt(properties.get("typeOfCrossover").toString());
		this.numberOfGenerations = Integer.parseInt(properties.get("numberOfGenerations").toString());
		this.numberOfPopulations = Integer.parseInt(properties.get("numberOfPopulations").toString());
	}

	public int getDimensionOfPopulation() {
		return dimensionOfPopulation;
	}

	public double getProbabilityOfCrossover() {
		return probabilityOfCrossover;
	}

	public double getProbabilityOfMutation() {
		return probabilityOfMutation;
	}

	public int getTypeOfCrossover() {
		return typeOfCrossover;
	}

	public int getNumberOfGenerations() {
		return numberOfGenerations;
	}

	public int getNumberOfPopulations() {
		return numberOfPopulations;
	}
}
