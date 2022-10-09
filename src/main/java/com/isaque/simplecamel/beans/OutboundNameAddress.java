package com.isaque.simplecamel.beans;

public class OutboundNameAddress {

	private String name;
	private String address;
	
	public OutboundNameAddress(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "OutboundNameAddress [name=" + name + ", address=" + address + "]";
	}
	
	
	
}
