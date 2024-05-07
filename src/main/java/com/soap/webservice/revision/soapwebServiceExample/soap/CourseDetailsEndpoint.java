package com.soap.webservice.revision.soapwebServiceExample.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.learn.courses.CourseDetails;
import com.learn.courses.DeleteCourseDetailsRequest;
import com.learn.courses.DeleteCourseDetailsResponse;
import com.learn.courses.GetCourseDetailsRequest;
import com.learn.courses.GetCourseDetailsResponse;
import com.soap.webservice.revision.soapwebServiceExample.beans.Course;
import com.soap.webservice.revision.soapwebServiceExample.exception.CourseNotFoundException;
import com.soap.webservice.revision.soapwebServiceExample.services.CourseServices;
import com.soap.webservice.revision.soapwebServiceExample.services.CourseServices.Status;

@Endpoint
public class CourseDetailsEndpoint {

	@Autowired
	private CourseServices courseServices;
	// input - request
	// output - response

	// how to map request to endpoint

	@PayloadRoot(namespace = "http://learn.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse 
	   processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		
		
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		Course a = courseServices.findById(request.getId());
		
		if(a == null)
			throw new CourseNotFoundException("Invalid Course Id   "+request.getId());
		
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(a.getId());
		courseDetails.setName(a.getName());
		courseDetails.setDescription(a.getDescription());
		
		response.setCourseDetails(courseDetails);
		
		return response;
		
		
	}
	
	
	@PayloadRoot(namespace = "http://learn.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse 
	   processCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
		
		
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		Status a = courseServices.deleteById(request.getId());
		
		
	
		response.setStatus(mapStatus(a));
		
		return response;
		
		
	}


	private com.learn.courses.Status mapStatus(Status status) {
		// TODO Auto-generated method stub
		if(status== Status.FAILURE) {
			
			return com.learn.courses.Status.FAILURE;
			}
		else if(status== Status.SUCCESS) {
			
		return com.learn.courses.Status.SUCCESS;
		}
		return null;
	}


	
	}

