package com.soap.webservice.revision.soapwebServiceExample.conf;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs // Enable Web Services
@Configuration // Spring Configuration
public class WebServiceConfig {

	
	
	@Bean
	ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		
		MessageDispatcherServlet messageDispatcherServlet =
				 new MessageDispatcherServlet();
		
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageDispatcherServlet,"/ws/*");
		
		
	}
	
	@Bean(name = "courses1")
	public DefaultWsdl11Definition DefaultWsdl11Defination(XsdSchema courseSchema) {

		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();

		// PortType - CoursePort
		defaultWsdl11Definition.setPortTypeName("CoursePort");

		// NameSpace- http://learn.com/courses
		defaultWsdl11Definition.setTargetNamespace("http://learn.com/courses");

		defaultWsdl11Definition.setLocationUri("/ws");

		defaultWsdl11Definition.setSchema(courseSchema);

		return defaultWsdl11Definition;

	}

	@Bean
	public XsdSchema courseSchema() {

		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}
}
