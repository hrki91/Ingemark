package com.ingemark.zadatak.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tecaj {
	@JsonProperty(value = "Kupovni za devize")
	private String Kupovni_za_devize;
	@JsonProperty(value = "Srednji za devize")
	private String Srednji_za_devize;
	@JsonProperty(value = "Prodajni za devize")
	private String Prodajni_za_devize;

	public String getSrednji_za_devize() {
		return Srednji_za_devize;
	}

	public void setSrednji_za_devize(String srednji_za_devize) {
		Srednji_za_devize = srednji_za_devize;
	}

	public String getProdajni_za_devize() {
		return Prodajni_za_devize;
	}

	public void setProdajni_za_devize(String prodajni_za_devize) {
		Prodajni_za_devize = prodajni_za_devize;
	}

	public String getKupovni_za_devize() {
		return Kupovni_za_devize;
	}

	public void setKupovni_za_devize(String kupovni_za_devize) {
		Kupovni_za_devize = kupovni_za_devize;
	}
}
