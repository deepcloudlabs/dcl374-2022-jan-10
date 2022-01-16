package com.example.hr.dto.response;

import com.example.hr.document.Department;

public class EmployeeResponse {
	private String identity;
	private String fullname;
	private Double salary;
	private String iban;
	private Boolean fulltime;
	private int birthYear;
	private String photo;
	private Department department;

	public EmployeeResponse() {
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

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public Boolean getFulltime() {
		return fulltime;
	}

	public void setFulltime(Boolean fulltime) {
		this.fulltime = fulltime;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "EmployeeResponse [identity=" + identity + ", fullname=" + fullname + ", salary=" + salary + ", iban="
				+ iban + ", fulltime=" + fulltime + ", birthYear=" + birthYear + ", photo=" + photo + ", department="
				+ department + "]";
	}
}
