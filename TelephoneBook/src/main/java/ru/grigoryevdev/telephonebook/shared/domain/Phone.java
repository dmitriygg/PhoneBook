package ru.grigoryevdev.telephonebook.shared.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PHONELIST")
public class Phone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;

	@Column(name = "LASTNAME")
	private String lastName;
	
	@Column(name = "FIRSTNAME")
	private String firstName;

	@Column(name = "PATRONYMIC")
	private String patronymic;	

	@Column(name = "TELEPHONE")
	private String telephone;
	
	@ManyToOne
	@JoinColumn(name="street_id")
	private Street street;
    
	@Column(name = "HOUSE_NUMBER")
	private Integer houseNumber;

	@Column(name = "FLAT_NUMBER")
	private Integer flatNumber;	
	
	public Street getStreet() {
		return street;
	}
	
	public void setStreet(Street street) {
		this.street = street;
	}

	public Phone() {		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastname) {
		this.lastName = lastname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstname) {
		this.firstName = firstname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}	

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Integer getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(Integer flatNumber) {
		this.flatNumber = flatNumber;
	}

}
