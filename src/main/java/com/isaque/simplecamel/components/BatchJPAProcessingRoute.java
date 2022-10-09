package com.isaque.simplecamel.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.isaque.simplecamel.model.NameAddressDTO;
import com.isaque.simplecamel.processors.InboudMessageProcessor;

@Component
public class BatchJPAProcessingRoute extends RouteBuilder {
	
	private static final String FILE_OUTPUT = "file:src/data/output?fileName=outputFile.csv&fileExist=append&appendChars=\\n";
	
	@Override
	public void configure() throws Exception {
	
		
		from("timer:readDB?period=10000")
			.routeId("readDBId")
			.to("jpa:"+ NameAddressDTO.class.getName()+"?namedQuery=fetchAllRows")
			.split(body())
				.process(new InboudMessageProcessor())
				.log(LoggingLevel.INFO, "Transformed Body: ${body}")
				.convertBodyTo(String.class)
				.to(FILE_OUTPUT)
				.toD("jpa:"+NameAddressDTO.class.getName()+"?nativeQuery=DELETE FROM NAME_ADDRESS WHERE id = ${header.consumeId}&useExecuteUpdate=true")
			.end();
	}

}
