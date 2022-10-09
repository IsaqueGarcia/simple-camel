package com.isaque.simplecamel;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CamelSpringBootTest
@SpringBootTest
@UseAdviceWith
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class LegacyFileRouteTests {

	
	@Autowired
	CamelContext context;
	
	@EndpointInject("mock:result")
	protected MockEndpoint mockEndpoint;
	
	@Autowired
	ProducerTemplate producerTemplate;
	
//	@org.junit.jupiter.api.Test
//	@Order(1)
//	public void testFileMove() throws Exception{
//		
//		//Setup the Mock
//		String expectedBody = "this is an input file that will be processed and moved to output directory";
//		mockEndpoint.expectedBodiesReceived(expectedBody);
//		mockEndpoint.expectedMinimumMessageCount(1);
//		
//		//Tweak the route definition
//		AdviceWith.adviceWith(context,"legacyFileMoveRouteId", routeBuilder -> {
//			routeBuilder.weaveByToUri("file:*").replace().to(mockEndpoint);
//		});
//		
//		
//		//Start the context and validate is mock is asserted
//		context.start();
//		mockEndpoint.assertIsSatisfied();
//	}
	
//	@Test
//	public void testFileMoveByMockingFromEndpoint() throws Exception {
//		
//		String expectedBody = "this is an input file that will be processed and moved to output directory";
//		mockEndpoint.expectedBodiesReceived(expectedBody);
//		mockEndpoint.expectedMinimumMessageCount(1);
//		
//		AdviceWith.adviceWith(context, "legacyFileMoveRouteId", routeBuilder ->{
//			routeBuilder.replaceFromWith("direct:mockStart");
//			routeBuilder.weaveByToUri("file:*").replace().to(mockEndpoint);
//		});
//		
//		context.start();
//		producerTemplate.sendBody("direct:mockStart", expectedBody);
//		
//		mockEndpoint.assertIsSatisfied();
//	}
	
}
