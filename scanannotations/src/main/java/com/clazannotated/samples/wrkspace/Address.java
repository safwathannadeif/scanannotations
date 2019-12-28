package com.clazannotated.samples.wrkspace;

import com.annotations.samples.wrkspace.ClassAnnote1;
import com.annotations.samples.wrkspace.MethodAnnotate1;

@ClassAnnote1(valueOfClassAnnote1="ClassAddress-ClassAnnote1")
public class Address {
	private String street;
	private String city;
	private String state;
	private String zipCode;

	public Address(String street, String city, String state, String zipCode) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public Address() {
	}

	public String getStreet() {
		return street;
	}

	@MethodAnnotate1(valueOfMethodAnnotate1="Street Name-MethodAnnotate1")
	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	@MethodAnnotate1(valueOfMethodAnnotate1="City Name-MethodAnnotate1")
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + "]";
	}
}
