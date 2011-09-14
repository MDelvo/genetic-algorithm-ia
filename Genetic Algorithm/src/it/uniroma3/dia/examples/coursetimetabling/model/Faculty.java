package it.uniroma3.dia.examples.coursetimetabling.model;

public class Faculty {
	private int id;
	private String name;
	private int size;
	
	public Faculty(int id, String name, int size) {
		this.id = id;
		this.name = name;
		this.size = size;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getSize() {
		return size;
	}	
}
