package com.example.crm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
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
	private String email;
	private String phone;
	private byte[] photo;
	private CustomerType type;
}
