package com.clazannotated.samples.wrkspace;

import com.annotations.samples.wrkspace.ClassAnnote1;
import com.annotations.samples.wrkspace.ClassAnnoteX;
import com.annotations.samples.wrkspace.MethodAnnotate1;
import com.annotations.samples.wrkspace.RepeatAnnoationsMethods.OrderAnnotion;

@ClassAnnote1(valueOfClassAnnote1="ClassPerson-ClassAnnote1")
@ClassAnnoteX(valueOfClassAnnoteX="xX xx Xx")
public class Person {
	private String firstName;
	private String lastName;
	private String alias;
	private int age;
	private boolean isMale;
	private Address address;

	public Person(String firstName, String lastName, String alias, int age, boolean isMale, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.alias = alias;
		this.age = age;
		this.isMale = isMale;
		this.address = address;
	}

	public Person() {
	}

	public String getFirstName() {
		return firstName;
	}

	@MethodAnnotate1(valueOfMethodAnnotate1="First Name-MethodAnnotate1")
	
	@OrderAnnotion(orderItem="item11",orderType="Newtype",orderQuantity=101) 
	@OrderAnnotion(orderItem="item21",orderType="ClassType",orderQuantity=102) 
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@MethodAnnotate1(valueOfMethodAnnotate1="Last Name-MethodAnnotate1")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", alias=" + alias + ", age=" + age
				+ ", isMale=" + isMale + ", address=" + address + "]";
	}
}
