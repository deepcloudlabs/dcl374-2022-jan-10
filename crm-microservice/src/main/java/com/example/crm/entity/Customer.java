package com.example.crm.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;

import com.example.validation.TcKimlikNo;

@Entity
@Table(name = "customers")
@DynamicUpdate
public class Customer {
	@Id
	@TcKimlikNo
	private String identity;
	@NotBlank
	private String fullname;
	@Email
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String phone;
	@Max(2004)
	private int birthYear;
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] photo;
	@Enumerated(EnumType.STRING)
	private CustomerType type;
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Address> addresses;

	public Customer() {
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
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
		this.addresses = new ArrayList<>(addresses);
	}

	@Override
	public int hashCode() {
		return Objects.hash(identity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(identity, other.identity);
	}

	@Override
	public String toString() {
		return "Customer [identity=" + identity + ", fullname=" + fullname + ", email=" + email + ", phone=" + phone
				+ ", type=" + type + "]";
	}

}
