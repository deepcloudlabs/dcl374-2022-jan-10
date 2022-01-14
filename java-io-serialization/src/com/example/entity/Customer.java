package com.example.entity;

import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private String kimlik;
	private String fullname;
	private String email;
	private String phone;
	private int birthYear;
	transient private String photo;
	private CustomerType type;
	private List<Address> addresses;

	public Customer() {
		super();
	}

	public String getKimlik() {
		return kimlik;
	}

	public void setKimlik(String kimlik) {
		this.kimlik = kimlik;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Customer [identity=" + kimlik + ", fullname=" + fullname + ", email=" + email + ", phone=" + phone
				+ ", birthYear=" + birthYear + ", photo=" + photo + ", type=" + type + ", addresses=" + addresses + "]";
	}

}
