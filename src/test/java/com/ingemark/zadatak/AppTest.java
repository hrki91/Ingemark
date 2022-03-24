package com.ingemark.zadatak;

import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.ingemark.zadatak.model.Product;

import net.minidev.json.JSONObject;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppTest {

	private final String url = "http://localhost:";
	private final String testCode = "0000000019";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	void insert() {

		JSONObject productJsonObject = new JSONObject();
		productJsonObject.put("code", testCode);
		productJsonObject.put("description", "testni artikal");
		productJsonObject.put("is_available", 1);
		productJsonObject.put("name", "Test");
		productJsonObject.put("price_hrk", 780);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(productJsonObject.toString(), headers);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(this.url + port + "/product/create", request,
				String.class);
		Assertions.assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);

	}

	@Test
	@Order(2)
	void checkInserted() {

		Product p = getProductByCode(testCode);
		Assertions.assertThat(p.getId()).isNotEqualTo(0L);

	}

	@Test
	@Order(3)
	void update() {

		Product p = getProductByCode(testCode);

		JSONObject productJsonObject = new JSONObject();
		productJsonObject.put("code", p.getCode());
		productJsonObject.put("description", "testni artikal -izmjena");
		productJsonObject.put("is_available", p.isIs_available());
		productJsonObject.put("name", p.getName());
		productJsonObject.put("price_hrk", 1580);
		productJsonObject.put("id", p.getId());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(productJsonObject.toString(), headers);
		ResponseEntity<Product> responseEntity = restTemplate.exchange(this.url + port + "/product/update",
				HttpMethod.PUT, request, Product.class);
		Assertions.assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	@Order(4)
	void delete() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(
				this.url + port + "/product/delete/" + getProductByCode(testCode).getId(), HttpMethod.DELETE, request,
				String.class);
		Assertions.assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
	}

	private Product getProductByCode(String code) {
		URI targetUrl = UriComponentsBuilder.fromUriString(this.url + port).path("/product/get")
				.queryParam("code", code).build().toUri();

		Product[] products = restTemplate.getForObject(targetUrl, Product[].class);
		if (products == null)
			fail("Missing product 019, insert method don't work correctly");
		Product p = products[0];
		return p;
	}
}
