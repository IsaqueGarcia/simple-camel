package com.isaque.simplecamel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isaque.simplecamel.beans.OutboundNameAddress;
import com.isaque.simplecamel.model.NameAddressDTO;

public class InboudMessageProcessor implements Processor {

	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
			NameAddressDTO nameAddress = exchange.getIn().getBody(NameAddressDTO.class);
			exchange.getIn().setBody(new OutboundNameAddress(nameAddress.getName(), returnOutboundAddress(nameAddress)));
			exchange.getIn().setHeader("consumeId", nameAddress.getId());
			
	}
	
	private String returnOutboundAddress(NameAddressDTO address) {
		
		StringBuilder concatAddress = new StringBuilder(200)
		.append(address.getHouseNumber())
		.append(" " + address.getCity() + ",")
		.append(" " + address.getProvince())
		.append(" " + address.getPostalCode());
		
		return concatAddress.toString();
		
	}

}
