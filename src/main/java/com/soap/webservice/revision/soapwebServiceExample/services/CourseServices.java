package com.soap.webservice.revision.soapwebServiceExample.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.soap.webservice.revision.soapwebServiceExample.beans.Course;

@Component
public class CourseServices {

	
	public enum Status{
		
		SUCCESS, FAILURE;
		
	}
	private static List<Course> coursList = new ArrayList();

	static {

		Course course1 = new Course(1, "Spring", "10 Steps");
		coursList.add(course1);

		Course course2 = new Course(2, "Spring2", "20 Steps");
		coursList.add(course2);

	}
	
	
	public Course findById(int id) {
		
		for(Course a:coursList) {
			
			
			if(a.getId() == id) {
				
				
				return a;
				
				
				
			}
			
		}
		
		return null;
	}
	
	

	public Status deleteById(int id) {
		
		for(Course a:coursList) {
			
			
			if(a.getId() == id) {
				
				//coursList.remove(a);
				return Status.SUCCESS;
				
				
				
			}
			
		}
		
		return Status.FAILURE;
	}

}
