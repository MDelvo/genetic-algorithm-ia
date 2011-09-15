package it.uniroma3.dia.examples.coursetimetabling.model;


import java.util.List;

public class CourseClass {
	private int id;
	private String name;
	private Professor professor;
	private int duration;
	private List<Faculty> faculties;
	
	public CourseClass(int id, String name, Professor professor,
			int duration, List<Faculty> faculties) {
		super();
		this.id = id;
		this.name = name;
		this.professor = professor;
		this.duration = duration;
		this.faculties = faculties;
	}
	public int getId() {
		return id;
	}
	public String getShortName() {
		return name.substring(0,name.indexOf(" "));
	}
	public String getName() {
		return name;
	}
	public Professor getProfessor() {
		return professor;
	}
	public int getDuration() {
		return duration;
	}
	public List<Faculty> getFaculties() {
		return faculties;
	}
	
	public int getSize(){
		int size = 0;
		for(Faculty faculty : faculties)
			size+=faculty.getSize();
		return size;
	}
}
