package com.calicomaru.ShoppingSite.controller;

import com.calicomaru.ShoppingSite.model.Product;
import com.calicomaru.ShoppingSite.model.RequestParamDTO;
import com.calicomaru.ShoppingSite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/products")
    public List<Product> getProducts() {

        List<Product> p = productRepository.findAll();
        return p;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getOneProduct(@PathVariable Integer id) {

        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()) {
            return new ResponseEntity<>(foundProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("products/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product newProduct) {

       return productRepository.save(newProduct);

    }

    @GetMapping("products/instock")
    public List<Product> getInStockProducts() {

        List<Product> p = productRepository.findInStock();

        return p;

    }

    @GetMapping("products/outofstock")
    public List<Product> getOutOfStockProducts() {

        List<Product> p = productRepository.findOutOfStock();

        return p;

    }

    @PostMapping("products/delete")
    public void deleteProduct(@RequestBody RequestParamDTO requestParamDTO) {

        productRepository.deleteProductHide(requestParamDTO.getId());


    }

    @PostMapping("products/restore")
    public void restoreProduct(@RequestBody RequestParamDTO requestParamDTO) {

        productRepository.restoreProductHide(requestParamDTO.getId());

    }

    @PutMapping("/products/{id}")
    public Product replaceProduct(@RequestBody Product newProduct, @PathVariable Integer id) {


        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setProductDescription(newProduct.getProductDescription());
                    product.setQuantity(newProduct.getQuantity());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroyProduct(@PathVariable Integer id) {
        productRepository.deleteById(id);
    }


}
