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
	private int numberOfGenerationsOfMergedPopulations;
	
	public Config() {
		Properties properties = new Properties();
		try { 
			properties.load(new FileInputStream("config.properties"));
		} catch (Exception e) { throw new RuntimeException("Error when loading config file"); }
		
		this.dimensionOfPopulation = Integer.parseInt(properties.get("DimensionOfPopulation").toString());
		this.probabilityOfCrossover = Integer.parseInt(properties.get("ProbabilityOfCrossover").toString());
		this.probabilityOfMutation = Integer.parseInt(properties.get("ProbabilityOfMutation").toString());
		this.typeOfCrossover = Integer.parseInt(properties.get("NPointCrossover").toString());
		this.numberOfGenerations = Integer.parseInt(properties.get("NumberOfGenerations").toString());
		this.numberOfPopulations = Integer.parseInt(properties.get("NumberOfPopulations").toString());
		this.numberOfGenerationsOfMergedPopulations = Integer.parseInt(properties.get("NumberOfGenerationsOfMergedPopulations").toString());
	}

	public int getDimensionOfPopulation() {
		return this.dimensionOfPopulation;
	}

	public double getProbabilityOfCrossover() {
		return this.probabilityOfCrossover;
	}

	public double getProbabilityOfMutation() {
		return this.probabilityOfMutation;
	}

	public int getTypeOfCrossover() {
		return this.typeOfCrossover;
	}

	public int getNumberOfGenerations() {
		return this.numberOfGenerations;
	}

	public int getNumberOfPopulations() {
		return this.numberOfPopulations;
	}
	
	public void setNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
	}
	
	public int getNumberOfGenerationsOfMergedPopulations() {
		return numberOfGenerationsOfMergedPopulations;
	}
}
