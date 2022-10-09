package com.isaque.simplecamel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "NAME_ADDRESS")
@NamedQuery(name="fetchAllRows", query="Select x from NameAddressDTO x")
public class NameAddressDTO implements Serializable{
	
	private static final long serialVersionUID = -3909720600190456409L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "NAME")
	private String name;
	@Column(name = "HOUSE_NUMBER")
	private String houseNumber;
	@Column(name = "CITY")
	private String city;
	@Column(name = "PROVINCE")
	private String province;
	@Column(name = "POSTAL_CODE")
	private String postalCode;
	
//	@PrePersist
//	public void modifyField() {
//		this.name = this.name.toLowerCase();
//	}
	
	public NameAddressDTO() {
	}
	
	public NameAddressDTO(String name, String houseNumber, String city, String province, String postalCode) {
		super();
		this.name = name;
		this.houseNumber = houseNumber;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "NameAddressDTO [id=" + id + ", name=" + name + ", houseNumber=" + houseNumber + ", city=" + city
				+ ", province=" + province + ", postalCode=" + postalCode + "]";
	}
	
}
