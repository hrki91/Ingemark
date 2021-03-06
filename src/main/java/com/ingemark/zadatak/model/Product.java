package com.ingemark.zadatak.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Product {

	public Product(int id){
		this.id = id;
	}
	
	public Product() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(unique=true,length = 10)
	@Size(min = 10, max = 10, message="Unvalid code length. Code length must be exactly 10")
	private String code;
	private String name;
	@Min(value = 0,message="price_hrk must be greater or equal to 0")
	private long price_hrk;
	@Min(value = 0,message="price_eur must be greater or equal to 0")
	private long price_eur;
	private String description;
	private boolean is_available;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice_hrk() {
		return price_hrk;
	}
	public void setPrice_hrk(long price_hrk) {
		this.price_hrk = price_hrk;
	}
	public long getPrice_eur() {
		return price_eur;
	}
	public void setPrice_eur(long price_eur) {
		this.price_eur = price_eur;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isIs_available() {
		return is_available;
	}
	public void setIs_available(boolean is_available) {
		this.is_available = is_available;
	}
}
