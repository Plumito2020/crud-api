package com.stage.stage.ressource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stage.stage.document.Product;
import com.stage.stage.exception.ApiRequestException;
import com.stage.stage.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepository prodRepo;

	@GetMapping("/all")
	public List<Product> getAll() {
		
		//throw new ApiRequestException("3lach nbk mabitich tkhdem ??");
		return prodRepo.findAll();

	}

	@GetMapping("/get/{id}")
	public Optional<Product> getProduct(@PathVariable String id) {
		return prodRepo.findById(id);
		
	}

	@PostMapping(path = "/add", consumes = "application/json")
	public void addProduct(@RequestBody Product product) {
		prodRepo.save(product);

	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable String id) {

	    prodRepo.deleteById(id);

	}

	
	@PutMapping("/update/{id}" )
	public void updateTutorial(@PathVariable String id, @RequestBody Product prod) {
		
		  prod.setId(id);
		  prodRepo.save(prod);

	}
	

}
