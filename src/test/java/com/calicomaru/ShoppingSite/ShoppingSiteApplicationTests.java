package com.calicomaru.ShoppingSite;

import com.calicomaru.ShoppingSite.model.Product;
import com.calicomaru.ShoppingSite.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingSiteApplicationTests {

	@Autowired
	ProductRepository productRepository;



	@Test
	public void contextLoads() {
	}

	@Test
	public void testDb(){

		Product productTest = new Product(1,"Apple", "Fuji Apple", 9.99, 100, true);
		Product productTest1 = new Product(2,"Orange", "Mandarin", 9.99, 100, false);
		productRepository.save(productTest);
		productRepository.save(productTest1);

	}

	@Test
	public void testDbRecord(){

		List<Product> list = productRepository.findByName("Apple");
		Product p = list.get(0);

		System.out.println(p.getId());
		System.out.println(p.getName());
		System.out.println(p.getProductDescription());
		System.out.println(p.getPrice());
		System.out.println(p.getQuantity());

	}

	@Test
	public void boolDbTest(){

		productRepository.findInStock();
	}
}
