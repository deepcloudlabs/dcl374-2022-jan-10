package com.example.crm.dto.request;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.crm.document.Address;
import com.example.crm.entity.CustomerType;

public class UpdateCustomerRequest {
	@NotBlank
	private String fullname;
	@Email
	private String email;
	private String phone;
	private String photo;
	private CustomerType type;
	private List<Address> addresses;

	public UpdateCustomerRequest() {
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

}
