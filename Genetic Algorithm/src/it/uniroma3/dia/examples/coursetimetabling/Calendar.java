package it.uniroma3.dia.examples.coursetimetabling;

import it.uniroma3.dia.Chromosome;

import java.util.List;

public class Calendar {
	
	public static String printCalendar(Chromosome c, List<Faculty> faculties, List<Classroom> classRooms, List<CourseClass> courseClasses, String[] hours, int dayOfLesson)
	{
		String calendar = "";
		for(Faculty faculty : faculties){
			calendar+=("\nOrario Facoltà: "+faculty.getName()+":\n\n");			
			calendar+=("\t\t| Lun\t\t\t| Mar\t\t\t| Mer\t\t\t| Gio\t\t\t| Ven\n");
			String sep = "";
			for(int i = 0;i<=130;i++)
				sep+="-";
			calendar+=(sep+"\n");
			
			for(int j = 0; j<hours.length; j++){
				calendar+=(hours[j]);
				
					for(int x = 0; x<dayOfLesson; x++){	
						String courses = "";
						for(Classroom room : classRooms){							
						int index = (room.getId()-1)*hours.length + hours.length*classRooms.size()*x + j;
						int courseClass = Integer.parseInt(c.getGenes(index));					
						if(courseClass!=0){
							CourseClass course = courseClasses.get(courseClass-1);
							if(course.getFaculties().contains(faculty))
								courses+=course.getShortName()+"("+room.getId()+")-";
						}
					}
					if(courses.length()>0){
						courses=courses.substring(0,courses.length()-1);
						for(int i=0;i<=10-courses.length();i++)
							courses+=" ";
					}
					else
						courses = "\t";
					calendar+=("\t| "+courses+"\t");
				}			
					calendar+=("\n"+sep+"\n");
			}
		}
		return calendar;
	}
}
