package it.uniroma3.dia.examples.coursetimetabling;

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
	public void setId(int id) {
		this.id = id;
	}
	public String getShortName() {
		return name.substring(0,name.indexOf(" "));
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public List<Faculty> getFaculties() {
		return faculties;
	}
	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
	}
}
