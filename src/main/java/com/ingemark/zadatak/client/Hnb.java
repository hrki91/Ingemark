package com.ingemark.zadatak.client;

import org.springframework.web.client.RestTemplate;

import com.ingemark.zadatak.model.Tecaj;

public class Hnb {

	private final RestTemplate restTemplate;

	public Hnb() {
        this.restTemplate = new RestTemplate();
    }

    public Tecaj getTecaj() {
        String url = "https://api.hnb.hr/tecajn/v1?valuta=EUR";
        Tecaj[] responseEntity = this.restTemplate.getForObject(url, Tecaj[].class);
        return responseEntity[0];
    }
}
