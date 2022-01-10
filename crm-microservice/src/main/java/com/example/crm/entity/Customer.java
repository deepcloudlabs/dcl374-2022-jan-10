package com.example.crm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.validation.TcKimlikNo;

@Entity
@Table(name="customers")
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
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] photo;
	@Enumerated(EnumType.STRING)
	private CustomerType type;
	private List<Address> addresses;
}
