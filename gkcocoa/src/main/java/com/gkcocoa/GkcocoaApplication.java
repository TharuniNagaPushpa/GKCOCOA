package com.gkcocoa;

import com.gkcocoa.model.Product;
import com.gkcocoa.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GkcocoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GkcocoaApplication.class, args);
	}

	@Bean
	CommandLineRunner loadProducts(ProductRepository productRepository) {
		return args -> {

			if (productRepository.count() == 0) {

				productRepository.save(
						new Product(
								"Premium Cocoa Beans",
								299,
								"/images/premium-cocoa.jpg"
						)
				);

				productRepository.save(
						new Product(
								"Fermented Cocoa Beans",
								399,
								"/images/fermented-cocoa.jpg"
						)
				);

				productRepository.save(
						new Product(
								"Dried Cocoa Beans",
								249,
								"/images/dried-cocoa.jpg"
						)
				);
			}
		};
	}
}