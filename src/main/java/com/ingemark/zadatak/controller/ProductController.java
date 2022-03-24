package com.ingemark.zadatak.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ingemark.zadatak.client.Hnb;
import com.ingemark.zadatak.db.ProductRepository;
import com.ingemark.zadatak.model.Product;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private final ProductRepository repository;
	
	ProductController(ProductRepository repository){
		this.repository = repository;
	}
	
	@GetMapping("/get")
	public List<Product> getProducts(@RequestParam(value="code", required=false) String code){
		if(code == null) 
			return (List<Product>) repository.findAll();
		else
			return (List<Product>) repository.findByCode(code);
	}
	
	@PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String>  createProduct(@RequestBody Product product) {
		
		if(isProductPriceValid(product))
			return new ResponseEntity<>("Uvalid values in price columns",HttpStatus.BAD_REQUEST);
		
		calculateEURPrice(product);
		
		try {
			Product newProduct = repository.save(product);
			if(newProduct.getId() == 0) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
			}
		}catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String>  updateProduct(@RequestBody Product product) {
		
		if(product.getId() == 0) {
			return new ResponseEntity<>("ID can't be 0",HttpStatus.BAD_REQUEST);			
		}
		
		if(isProductPriceValid(product))
			return new ResponseEntity<>("Uvalid values in price columns",HttpStatus.BAD_REQUEST);
		
		return createProduct(product);
	}
	
	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public ResponseEntity<String>  deleteProduct(@PathVariable("id") Integer id) {
		try {
			repository.delete(new Product(id));
		}
		catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private boolean isProductPriceValid(Product product) {
		if(product.getPrice_eur() < 0 || product.getPrice_hrk() < 0 || (product.getPrice_eur() == 0 && product.getPrice_eur() == 0)) {
			return false;
		}
		return true;
	}
	
	private void calculateEURPrice (Product product) {
		double tecaj = 0;
		try {
			tecaj = Double.parseDouble((new Hnb().getTecaj().getSrednji_za_devize().replace(",", ".")));
		}catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		double price = (double)product.getPrice_hrk() / 100;
		product.setPrice_eur( Double.valueOf((price / tecaj) * 100).longValue() );
		
	}
}
