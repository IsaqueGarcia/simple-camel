package com.isaque.simplecamel.beans;

import org.apache.camel.Exchange;

import com.isaque.simplecamel.model.NameAddressDTO;

public class InboundRestProcessingBean {

	
	public void validate(Exchange exchange) {
		NameAddressDTO addressDTO = exchange.getIn().getBody(NameAddressDTO.class);
		exchange.getIn().setHeader("userCity", addressDTO.getCity());
	}
	
	
}
