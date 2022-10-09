package com.isaque.simplecamel.components;

import java.net.ConnectException;

import javax.jms.JMSException;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.isaque.simplecamel.beans.InboundRestProcessingBean;
import com.isaque.simplecamel.model.NameAddressDTO;

@Component
public class NewRestRoute extends RouteBuilder {

	//private static final String FILE_OUTPUT = "file:src/data/output?fileName=outputFile.csv&fileExist=append&appendChars=\\n";
	
	@SuppressWarnings("unchecked")
	@Override
	public void configure() throws Exception {
		
		Predicate isCitySaoPaulo = header("userCity").isEqualTo("Sao Paulo");
		
		onException(JMSException.class, ConnectException.class)
				.routeId("jmsExceptionRouteId")
				.handled(true)
				.log(LoggingLevel.INFO, "JMS Exception has occurred; handling gracefully");
		
		restConfiguration()
			.component("jetty")
			.host("localhost")
			.port(8080)
			.bindingMode(RestBindingMode.json)
			.enableCORS(true);
			
		
		rest("masterClass")
		.produces("application/json")
		.post("nameAddress")
		.type(NameAddressDTO.class)
		.route()
		.routeId("newRestRouteId")
		.log(LoggingLevel.INFO, "${body}")
//		.process(new InboudMessageProcessor())
//		.log("Transformed Body: ${body}")
//		.convertBodyTo(String.class)
//		.to(FILE_OUTPUT);
		//.multicast() --> faz a chamada para diversas rotas simultaneas e espera o retorno de todas para proseguir o fluxo principal
			//.to("jpa:"+NameAddressDTO.class.getName())
			//.to("activemq:queue:nameaddressqueue?exchangePattern=InOnly"); --> retorna um response implicito
		.bean(new InboundRestProcessingBean())
		
		
		//Setup Rule
		//if city = Sao Paulo -> sent to MQ
		//else send to both DB and MQ
		.choice()
		.when(isCitySaoPaulo) //simple("${headers.userCity} == 'Sao paulo'")
			.to("direct:toActiveMQ") // --> Retorna um response //--> direct funciona de forma sincrona
		.otherwise()
			.to("direct:toDB") //--> wiretap funciona de forma asincrona porém n obtem nenhum retorno no processamento principal
			.to("direct:toActiveMQ") // --> Retorna um response //--> direct funciona de forma sincrona
		.end()
		
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
		.transform().simple("Message processed and result generated with Body: ${body}")
		.endRest(); 
		
		from("direct:toDB")
			.routeId("toDBId")
			.to("jpa:"+NameAddressDTO.class.getName());
		
		from("direct:toActiveMQ")
			.routeId("toActiveMQId")
			.to("activemq:queue:nameaddressqueue?exchangePattern=InOnly");
			
	}

}
