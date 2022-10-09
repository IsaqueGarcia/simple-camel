package com.isaque.simplecamel.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.isaque.simplecamel.beans.NameAddress;
import com.isaque.simplecamel.processors.InboudMessageProcessor;

@Component
public class LegacyFileRoute extends RouteBuilder {

	private static final String FILE_INPUT = "file:src/data/input?fileName=inputFile.csv"; 
	private static final String FILE_OUTPUT = "file:src/data/output?fileName=outputFile.csv&fileExist=append&appendChars=\\n";
	
	Logger logger = LoggerFactory.getLogger(getClass());
	BeanIODataFormat inboundDataFormat = new BeanIODataFormat("InboundMessageBeanIOMapping.xml", "inputMessageStream");
	
	@Override
	public void configure() throws Exception {
		
		from(FILE_INPUT)
			.routeId("legacyFileMoveRouteId")
			.split(body().tokenize("\n", 1 , true))
			.unmarshal(inboundDataFormat)
			.process(new InboudMessageProcessor())
			.log(LoggingLevel.INFO, "Transformed Body: ${body}")
			.convertBodyTo(String.class)
			.to(FILE_OUTPUT)
		.end();
		
	}

}
